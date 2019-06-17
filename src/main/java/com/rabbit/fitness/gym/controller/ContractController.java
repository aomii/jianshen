package com.rabbit.fitness.gym.controller;


import com.rabbit.fitness.admin.service.ProductService;
import com.rabbit.fitness.admin.service.TradeService;
import com.rabbit.fitness.coach.coachService.CoachService;
import com.rabbit.fitness.gym.service.GymService;
import com.rabbit.fitness.pojo.*;
import com.rabbit.fitness.student.service.StudentService;
import com.rabbit.fitness.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/contract")
public class ContractController {

    @Resource(name = "studentService")
    private StudentService studentService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CoachService coachService;

    @Autowired
    private GymService gymService;




    //学员购买会员卡(非签约教练)
    @RequestMapping("/buyCard")
    public String buyCard(HttpSession session){
        /*
        * 参数：
        *       1. product_id   商品id，来自Product表
        *       2. uid    用户id，来自t_user表
        *       3. gymId,       场馆id，来自gym表
        * */

        //测试数据
        int productId = (int) session.getAttribute("banKaId");
        String result = "办会员卡失败";
        int gymId = (int) session.getAttribute("gid");
        System.out.println("****>"+productId+"gymId:"+gymId);
        //总的实现方式为：向trade表插入一条数据，需要参数：Trade trade
        Trade trade = new Trade();
        //为trade  赋值

            //1.获取student对象。

        Student student = (Student) session.getAttribute("selStu");
        trade.setStudent(student);
        System.out.println("student:"+student);


            //2.通过产品id查询产品
        Product selProduct = productService.findProductByPid(productId);

            //3.通过查到的studentId去trade表查询是否已经购买了产品，判断动作是否是续约
        Trade selTrade = tradeService.selTradByStudentId(student.getStuid());

        if(selTrade != null){//已有记录，为续约，则更改productlist,以及时间，金额
           //设置购买产品..
            String newPT = selTrade.getTradeProducts()+","+productId;
            selTrade.setTradeProducts(newPT);
            //设置时间
            Date date = selTrade.getTradeOvertime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,selProduct.getProductPrescription());
            Date overDate = calendar.getTime();
            selTrade.setTradeOvertime(overDate);
            //设置金额
            trade.setTradeMoney(trade.getTradeMoney()+selProduct.getProductPrice());
            //执行更新操作

            int code = tradeService.updateTrade(selTrade);
            if(code!=0){
                result = "续约成功！";
            }
            session.setAttribute("conResult",result);
            return "redirect:/contractResult.html";

        }else{//没有记录,插入新记录
            System.out.println("没有记录，插入新记录");
            Trade newTrade = new Trade();
            //价格
            newTrade.setTradeMoney(selProduct.getProductPrice());
            //产品id
            newTrade.setTradeProducts(""+productId);
            //开始时间
            newTrade.setTradeStarttime(new Date());
            //结束时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,selProduct.getProductPrescription());
            Date overDate = calendar.getTime();
            newTrade.setTradeOvertime(overDate);
            //学员
            newTrade.setStudent(student);
            //场馆
            //通过gymId查询Gym对象
            Gym selGym = gymService.findGymByGid(gymId);
            newTrade.setGym(selGym);
            System.out.println(">>>>>>>>>>>"+newTrade);
            //执行插入
            int code = tradeService.addTrade(newTrade);
            System.out.println("开始更新student表");
            //更新student表
            student.setGid(gymId);
           code =  studentService.updateStuBySel(student);
            if(code!=0){
                result = "办卡成功！";
            }
            session.setAttribute("conResult",result);
            return "redirect:/contractResult.html";
        }
    }
        //签约教练
    @RequestMapping("/dateCoach")
    public String dateCoach(HttpSession session){
        /*
            参数：
        *       uid
        *       coachId
        */

        //测试数据
            //获取session中存的学生信息selStu
        Student currentStu = (Student) session.getAttribute("selStu");
        System.out.println(">>>>>>"+currentStu);
        int cid = (int) session.getAttribute("qianYueCid");//测试教练的id
        String result = "签约失败";//返回给前台的信息

        //业务逻辑：判断是否签约场馆，若没有，则提示先去签约场馆；若已签约
        Trade selTrade = tradeService.selTradByStudentId(currentStu.getStuid());
        System.out.println(">>>>>>>>查询到的selTrade"+selTrade);
        if(selTrade == null){//没有记录，提示签约场馆
            System.out.println("签约教练需先签约场馆，您还未签约场馆，请先签约场馆！");
            result = "签约教练需先签约场馆，您还未签约场馆，请先签约场馆！";
            session.setAttribute("conResult",result);
            return "redirect:/contractResult.html";
        }else{//有记录，判断是否已经签约教练
            if(selTrade.getCoach().getCid() != null){//已有教练
                System.out.println("你已签约教练，若想重新签约新教练，请与原教练解约");
                result = "你已签约教练，若想重新签约新教练，请与原教练解约！";
                session.setAttribute("conResult",result);
                return "redirect:/contractResult.html";
            }else {//签约教练
                System.out.println("<<开始签约");
                //通过cid查找coach对象
               Coach selCoach = coachService.selCoachById(cid);
                System.out.println("1."+selTrade);
                System.out.println("2."+currentStu);
                //赋值
                selTrade.setCoach(selCoach);
                selTrade.setTradeMoney(selTrade.getTradeMoney()+selCoach.getCprice());
                //更新trade表
                System.out.println("selTrade+++"+selTrade);
                int code = tradeService.updateTrade(selTrade);
                System.out.println("更新trade表"+code);
                //更新学生表cid
                currentStu.setCid(cid);
                System.out.println("<<<<"+currentStu);
                code = studentService.updateStuBySel(currentStu);
                System.out.println("更新stu表"+code);
                //更新gym表，---不用，办卡的时候就有了...

                //进行支付，并向订单表添加记录

                //返回前台通知


                result = "签约教练成功！";
                session.setAttribute("conResult",result);
                return "redirect:/contractResult.html";
            }
        }
    }

    //解约教练
    @RequestMapping("/breakCoachConstract")
    @ResponseBody
    public Result breakCoachConstract(HttpSession session){
        /*
        * 参数：sid
        * */
        System.out.println("》》》》》开始解约");
        Student currentStu = (Student) session.getAttribute("selStu");
        //查找数据库中的记录
        Trade selTrade = tradeService.selTradByStudentId(currentStu.getStuid());
        System.out.println(">>>>>===  selTrade"+selTrade);
        //修改trade表
        int code = tradeService.breakCoachTrade(selTrade.getTradeId());
        //修改学生表
        code = studentService.breakCoachTrade(currentStu.getStuid());

        return Result.success(0,"解约教练成功");
    }

    //解约场馆
    @RequestMapping("/breakGymConstract")
    public Result breakGymConstract(HttpSession session){
        /*
        *   参数： stuid
        * */
        Student currentStu = (Student) session.getAttribute("selStu");
        //判断是否有签约教练

        Trade selTrade = tradeService.selTradByStudentId(currentStu.getStuid());
        if(selTrade.getCoach().getCid()!=null){
            System.out.println(">>>"+selTrade.getCoach().toString());
            return Result.error(500,"您签约了该场馆的教练，解约场馆必须先解约教练");
        }
        //没有签约教练，执行更新,删除trade表对应的记录
        tradeService.deleteTradeByStuId(currentStu.getStuid());
        //更新学生表的gymId
        int code = studentService.breakGymTrade(currentStu.getStuid());
        return Result.success(0,"解约场馆成功");

    }

}
