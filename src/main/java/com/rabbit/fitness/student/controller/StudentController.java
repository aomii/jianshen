package com.rabbit.fitness.student.controller;


import com.github.pagehelper.PageInfo;

import com.rabbit.fitness.admin.service.ProductService;
import com.rabbit.fitness.admin.service.TradeService;
import com.rabbit.fitness.constant.SysConstant;
import com.rabbit.fitness.gym.service.GymService;
import com.rabbit.fitness.pojo.*;

import com.rabbit.fitness.student.service.StudentOrderService;
import com.rabbit.fitness.student.service.StudentService;
import com.rabbit.fitness.student.vo.ContractInfoVo;
import com.rabbit.fitness.student.vo.StudentVo;
import com.rabbit.fitness.user.service.UserUserService;
import com.rabbit.fitness.utils.Result;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource(name = "studentService")
    private StudentService studentService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private GymService gymService;

    //==========后台=======================
    @Autowired
    private UserUserService userUserService;
    //=================================

    //==========前台===================
    @RequestMapping("/personInfo/{id}")
    @RequiresAuthentication
    public ModelAndView getStudentPersonInfo(@PathVariable("id") Integer uid, HttpSession session){
        Student selStu=studentService.getStudentInfoById(uid);
        ModelAndView mav = new ModelAndView();
        if (selStu!=null) {
            session.setAttribute("selStu", selStu);
            mav.setViewName("student_info");
        }
        return mav;
    }


    //学生购买产品年卡服务
    @RequestMapping("/getService")
    public Result getService(HttpSession session) {
        System.out.println("来到了购买服务端口");
        User currentUser = (User) session.getAttribute("currentUser");
        //判断该用户是否登录
        if (currentUser == null) {
            return Result.error(500, "请登录后，再购买该服务");
        }
        //登录成功后，查询该用户是否已经拥有这项服务，如果已经存在，则不能购买
        Integer currentUserId = currentUser.getUserId();
        Student currentStudent = studentService.getStudentInfoById(currentUserId);
        if(currentStudent == null){
            return Result.error(503, "您不是学员，不能购买该服务");
        }
        Integer currentStudentStuid = currentStudent.getStuid();

        StudentOrder studentOrderById = studentOrderService.findStudentOrderById(currentStudentStuid);
        if (studentOrderById != null) {
            return Result.error(501, "您已购买一项服务，不可再购买");
        }
        //没有购买则跳转到购买页面
        return Result.error(502, "即将进入购买平台，请稍等");
    }

    @RequiresRoles(value = "student")
    public List<StudentOrder> getService(@PathVariable("stuid") Integer stuid){
        List<StudentOrder> orders = studentService.getService(stuid);
        return  orders;
    }


    //==========前台结束=================











    //获取学员信息
    @RequestMapping("/info/{id}")
    @RequiresAuthentication
    public Student getStudentInfoById(@PathVariable("id") Integer uid, HttpSession session){
        Student selStu=studentService.getStudentInfoById(uid);
        if (selStu!=null) {
            session.setAttribute("selStu", selStu);
            return studentService.getStudentInfoById(uid);
        }
        return null;
    }

    @RequestMapping("/finfo/{id}")
    @RequiresAuthentication
    public Student getFriendInfoById(@PathVariable("id") Integer uid, HttpSession session){
        return studentService.getStudentInfoById(uid);
    }

    //完善学员信息
    @RequestMapping("/perfect")
    @RequiresRoles(value = "student")
    public int perfectStudentById(Student student){
        //System.out.println(student.getStugender());
        return studentService.perfectStudentById(student);
    }

    //查看学员所属场馆有哪些学员
    @RequestMapping("/find-other-student")
    @RequiresRoles(value = "student")
    public List<Student> findOtherStudentById(Student student){
        return studentService.findOtherStudentById(student);
    }


    //发送好友请求
    @RequestMapping("/add-friend")
    @RequiresRoles(value = "student")
    public int addFriend(Addfriend addfriend){
        return studentService.addFriend(addfriend);
    }
    //查看好友请求
    @RequestMapping("/friendrequests")
    @RequiresRoles(value = "student")
    public List<Addfriend> findFriendRequestsById(Integer uid){
        return  studentService.findFriendRequestsById(uid);
    }

    //同意好友请求
    //更新双方的friendids
    @RequestMapping("/update-fids")
    @RequiresRoles(value = "student")
    public void updateFids(Student student){
        studentService.updateFids(student);
    }

    //更新同意好友请求记录
    @RequestMapping("/update-agree-status")
    @RequiresRoles(value = "student")
    public int updateAgreeStatus(Integer agreeuid){
        return studentService.updateAgreeStatus(agreeuid);
    }
    //更新拒绝好友请求记录
    @RequestMapping("/update-reject-status")
    @RequiresRoles(value = "student")
    public int updateRejectStatus(Integer agreeuid){
       return studentService.updateRejectStatus(agreeuid);
    }
    //好友列表
    @RequestMapping("/friend-list")
    @RequiresRoles(value = "student")
    public List<Student> friendList(Integer uid){
        return studentService.friendList(uid);
    }
    //查看是否为好友
    @RequestMapping("/isFriend")
    @RequiresRoles(value = "student")
    public  int isFriend( Integer senduid, Integer agreeuid){
        return studentService.isFriend(senduid, agreeuid);
    }

    //通过uid查询t_user表中的user_name
    @RequestMapping("/getUserNameByUid")
    @RequiresAuthentication
    public User getUserNameByUid(Integer uid){
        return studentService.getUserNameByUid(uid);
    }

    //保存聊天记录
    @RequestMapping("/addChatRecord")
    @RequiresRoles(value = "student")
    public int addChatRecord(Communication communication){
        return studentService.addChatRecord(communication);
    }
    //获取向当前用户发送消息的用户的名字
    @RequestMapping("/getAnotherSideName")
    @RequiresRoles(value = "student")
    public List<String> getAnotherSideName(String userName){
        return studentService.getAnotherSideName(userName);
    }
    //获取未读消息数量
    @RequestMapping("/getUnreadMsgCountByName")
    @RequiresRoles(value = "student")
    public int getUnreadMsgCountByName(String userName){
        return studentService.getUnreadMsgCountByName(userName);
    }
    //更新聊天消息状态
    @RequestMapping("/updateChatRecordStatus")
    @RequiresRoles(value = "student")
    public int updateChatRecordStatus(String userName){
        return  studentService.updateChatRecordStatus(userName);
    }
    //获取学员的签约的教练信息
    @RequestMapping("/getCoachByCid")
    @RequiresRoles(value = "student")
    public Coach getCoachByCid(Integer cid){
        return studentService.getCoachByCid(cid);
    }
    /*=====================下面是后台使用===============================*/

    @RequestMapping("/listBack")
    @RequiresPermissions(value = {"sys:student:list"})
    public Map<String,Object> listBack(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage,
                                       @RequestParam(value = "rows",defaultValue = "5",required = false)Integer pageSize, StudentVo vo, HttpSession session){
        //在session中得到user，再得到gid;
        //模拟从session中获取用户id
        User user=(User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer gid=studentService.findGidByUid(user.getUserId());
        List<Student> list=studentService.findStudentByPage(currentPage,pageSize,vo,gid);
        PageInfo<Student> pageInfo=new PageInfo<>(list);
        Map<String,Object> result=new HashMap<>();
        result.put("total",pageInfo.getTotal());
        result.put("rows",pageInfo.getList());
        return  result;
    }


    @Value("${image-path}")
    public String path;

    @RequestMapping("/addBack")
    @RequiresPermissions(value = {"sys:student:add"})
    public Result addBack(MultipartFile img, Student student) throws IOException {
        //0.上传图片
        String fileName = img.getOriginalFilename();
        if(fileName!=null&&!"".equals(fileName)){
            System.out.println(fileName);

            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            path=path+File.separator+fileName;
            file = new File(path);
            img.transferTo(file);   //自动创建文件、写入数据
            student.setStupic("/upload/"+fileName);
        }

        //1、判断用户名是否已被注册

        System.out.println(student.getUser());
        System.out.println(student.getUser().getRole().getRoleId());
        User _user=userUserService.checkUser(student.getUser().getUserName());
        if (_user!=null){
            return Result.error(500,"用户名已存在");
        }

        //2、插入数据
        try {
            studentService.addBack(student);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500,"添加失败");
        }

    }


    @RequestMapping("/updateBack")
    @RequiresPermissions(value = {"sys:student:update"})
    public Result updateBack(Student student){
        Integer result = studentService.updateBack(student);
        if (result>0){
            return  Result.success();
        }else{
            return  Result.error(500,"更新失败");
        }
    }

    @RequestMapping("/deleteBack")
    @RequiresPermissions(value = {"sys:student:delete"})
    public Result deleteBack(String stuids,String userids){
        System.out.println(stuids+userids);
        try {
            int result=studentService.deleteBack(stuids,userids);
            if (result>0){
                return Result.success(0,"删除"+result+"条数据成功");
            }else{
                return Result.error(500,"删除数据失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
           return Result.error(500,"删除数据失败");
        }
    }
    /*=====================上面是后台使用===============================*/

    //查询学员办卡信息
    @RequestMapping("/selConInfo")
    public ContractInfoVo selConInfo(HttpSession session){
        ContractInfoVo contractInfoVo = new ContractInfoVo();

        //获取登录学生信息
        Student currentStu = (Student) session.getAttribute("selStu");

        //查询交易trade记录
        Trade selTrade = tradeService.selTradByStudentId(currentStu.getStuid());
        contractInfoVo.setBegin(selTrade.getTradeStarttime());//赋值开始时间
        contractInfoVo.setEnd(selTrade.getTradeOvertime());//赋值结束时间
        //查询产品的名称
        List<Product> selProducts = selTrade.getProducts();
        Product selPrd = selProducts.get(0);
        System.out.println(selPrd.toString());
        contractInfoVo.setProductName(selPrd.getProductName());//赋值产品名称

        //查询场馆名称
        Gym selGym = selTrade.getGym();
        System.out.println(selGym);
        contractInfoVo.setGymName(selGym.getGname());//赋值场馆名称
        System.out.println(">>>"+contractInfoVo.toString());
        return contractInfoVo;
    }

}
