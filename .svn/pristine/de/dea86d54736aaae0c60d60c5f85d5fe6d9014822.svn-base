package com.rabbit.fitness.gym.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.fitness.constant.SysConstant;
import com.rabbit.fitness.gym.service.RecruitCoachService;
import com.rabbit.fitness.gym.vo.RecruitCoachVo;
import com.rabbit.fitness.pojo.GrecruitCoach;
import com.rabbit.fitness.pojo.User;
import com.rabbit.fitness.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recruitCoach")
@RequiresRoles(value = "gymadmin")
public class RecruitCoachController {

    @Autowired
    private RecruitCoachService recruitCoachService;


    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage,
                                   @RequestParam(value = "rows",defaultValue = "5",required = false)Integer pageSize, RecruitCoachVo vo,HttpSession session){
        //在session中得到user，再得到gid;
        //模拟从session中获取用户id
        User user=(User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer gid=recruitCoachService.findGidByUid(user.getUserId());

        //gid存入vo中
        vo.setGid(gid);
        List<GrecruitCoach> list=recruitCoachService.findRecordByPage(currentPage,pageSize,vo);
        PageInfo<GrecruitCoach> pageInfo=new PageInfo<>(list);
        Map<String,Object> result=new HashMap<>();
        result.put("total",pageInfo.getTotal());
        result.put("rows",pageInfo.getList());
        return  result;
    }

    @RequestMapping("/info")
    public GrecruitCoach info(Integer gcId){
        GrecruitCoach grecruitCoach=recruitCoachService.findRecuitCoachByGcid(gcId);
        return  grecruitCoach;
    }

    //修改状态，场地接受教练，
    @RequestMapping("/update")
    public Result update(HttpSession session, GrecruitCoach grecruitCoach){
        User user=(User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer gid=recruitCoachService.findGidByUid(user.getUserId());
        //修改申请记录表的isDispose
        System.out.println(grecruitCoach);
        try {
            Integer result=recruitCoachService.update(grecruitCoach,gid);
            if (result>0){
                return Result.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error(500,"操作失败");
    }


}
