package com.rabbit.fitness.user.usercontroller;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.fitness.coach.coachService.CoachService;
import com.rabbit.fitness.constant.SysConstant;
import com.rabbit.fitness.pojo.Coach;
import com.rabbit.fitness.pojo.Role;
import com.rabbit.fitness.pojo.Student;
import com.rabbit.fitness.pojo.User;
import com.rabbit.fitness.student.service.StudentService;
import com.rabbit.fitness.user.service.UserUserService;
import com.rabbit.fitness.user.util.IDUtil;
import com.zhenzi.sms.ZhenziSmsClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
@RequestMapping("/users")
public class UserUserController {
    @Autowired
    private UserUserService userService;

    @Autowired
    private CoachService coachService;

   @Resource(name = "studentService")
   private StudentService studentService;

    @RequestMapping("/sendSms")
    @ResponseBody
    public Object sendSms(HttpServletRequest request, String userName) {
        try {
            System.out.println(userName);
            JSONObject json = null;
            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            //发送短信                                            http://sms.zhenzikj.com/zhenzisms_user
            ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "101324","21604f85-d0b2-4d2f-983b-90ff4ec6eda2");
            String result = client.send(userName,"您的验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次！");
            json =  JSONObject.parseObject(result);
            System.out.println(json);
            if(json.getIntValue("code") != 0)//发送短信失败
                return "fail";
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson
            HttpSession session = request.getSession();
            json = new JSONObject();
            json.put("verifyCode", verifyCode);
            json.put("createTime", System.currentTimeMillis());
            // 将认证码存入SESSION
            request.getSession().setAttribute("verifyCode", json);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @RequestMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request,String userName,String password,Integer roleId,
                           String verifyCode) {
        JSONObject json = (JSONObject)request.getSession().getAttribute("verifyCode");
        if(!json.getString("verifyCode").equals(verifyCode)){
            return "验证码错误";
        }
        if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
            return "验证码过期";
        }
        //将用户信息存入数据库

        User user=userService.checkUser(userName);
         if (user!=null){
             return "该手机号已经注册";
         }

         String salt=userName;
         String hashAlgorithmName="md5";
         Object object=new SimpleHash(hashAlgorithmName,password,salt,2);
         String upwd2=  object.toString();
        Role role=new Role();
        role.setRoleId(roleId);

         User user1=new User(userName,upwd2,roleId);
        user1.setRole(role);

         boolean result=userService.insertUser(user1);

        System.out.println("result:"+result);

        if (result) {
            user = userService.checkUser(userName);
            if (user.getRoleId() == 4) {
                String stuname = IDUtil.getItemID();
                String stupic = "/fitness/1.jpg";
                Student student = new Student(stuname, stupic, user.getUserId());
                System.out.println(studentService);
                // StudentService studentService=new StudentService();
                boolean flag = studentService.insertStu(student);
                System.out.println(flag);
                if (flag) {
                    return "success";
                }
                return "success";
            }
        }
        return "fail";
    }
   @RequestMapping("/login")
   @ResponseBody
    public String doLogin(User user,HttpServletRequest request){
       UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
       Subject subject = SecurityUtils.getSubject();
       try {
           subject.login(token);
           //subject.getPrincipal()
           //如果信息未完善跳到完善信息页面
           //获得uid
           User user1 = (User) request.getSession().getAttribute(SysConstant.CURRENT_USER);
           Integer uid = user1.getUserId();
           //通过uid获得rid判断为什么角色
           Integer rid = userService.findRidByUid(uid);
           if (rid == 3) {
               //用uid查教练判断是否有教练
               Coach coach = coachService.findCoachByUid(uid);
               if (coachService.findCoachByUid(uid) == null) {
                    return "add";
               }else {
                   return "success";
               }
           }
           //通过uid获得coach
           return "success";
       } catch (AuthenticationException e) {
           e.printStackTrace();
           return "fail";
       }
    }


    @RequestMapping("/addMessage")
    @ResponseBody
    public String addMessage(Student student){
        System.out.println(student);
        User user= (User) SecurityUtils.getSubject().getSession().getAttribute(SysConstant.CURRENT_USER);
        student.setStuid(user.getUserId());
        System.out.println(student);
        boolean flag=studentService.addMessage(student);
        System.out.println(flag);
        if (flag){
            return "success";
        }
        return "fail";
    }


    @RequestMapping("/retrieve")
    @ResponseBody
    public String retrieve(HttpServletRequest request,String userName,String password,
                           String verifyCode) {
        JSONObject json = (JSONObject)request.getSession().getAttribute("verifyCode");
        if(!json.getString("verifyCode").equals(verifyCode)){
            return "验证码错误";
        }
        if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
            return "验证码过期";
        }
        //将用户信息存入数据库
        User user=userService.checkUser(userName);
        if (user==null){
            return "该手机号没有注册过";
        }
        String salt=userName;
        String hashAlgorithmName="md5";
        Object object=new SimpleHash(hashAlgorithmName,password,salt,2);
        String upwd2=  object.toString();
        User user1=new User(userName,upwd2);
        System.out.println(user1);
        boolean result=userService.insertUser1(user1);
        System.out.println("result:"+result);
        if (result){
            return "成功";
        }
        return "fail";
    }

    @RequestMapping("/address")
    @ResponseBody
    public String getAddress(String cityName,HttpServletRequest request){
        System.out.println(cityName);
        User user1= (User) request.getSession().getAttribute(SysConstant.CURRENT_USER);
        String lastAddress=user1.getAddress();
        System.out.println(user1);
        System.out.println(lastAddress);
        user1.setAddress(cityName);
        user1.setLastaddress(lastAddress);
        System.out.println(user1);
        int a=userService.updateAddress(user1);
        if (a>0){return "success";}
        return "fail";
    }
}