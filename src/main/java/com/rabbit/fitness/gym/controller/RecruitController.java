package com.rabbit.fitness.gym.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.fitness.gym.service.RecruitService;
import com.rabbit.fitness.gym.vo.RecruitVo;
import com.rabbit.fitness.pojo.Grecruit;
import com.rabbit.fitness.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recruit")
@RequiresRoles(value = "gymadmin")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;




    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage,
                                   @RequestParam(value = "rows",defaultValue = "5",required = false)Integer pageSize, RecruitVo vo){
        List<Grecruit> list=recruitService.findGrecruitByPage(currentPage,pageSize,vo);
        PageInfo<Grecruit> pageInfo=new PageInfo<>(list);
        Map<String,Object> result=new HashMap<>();
        result.put("total",pageInfo.getTotal());
        result.put("rows",pageInfo.getList());
        return  result;
    }


    @RequestMapping("/add")
    @ResponseBody
    public Result add(Grecruit grecruit){
        System.out.println(grecruit);

        Integer result=recruitService.add(grecruit);
        if (result>0){
            return  Result.success();
        }else {
            return Result.error(500,"添加失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(String grids){
        System.out.println(grids);
        String [] arr=grids.split(",");
        Integer[] grIds=new Integer[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            grIds[i]=Integer.parseInt(arr[i]);
        }
        int result=recruitService.deleteRecruit(grIds);
        if (result>0){
            return Result.success(0,"删除"+result+"条数据成功");
        }else{
            return Result.error(500,"删除数据失败");
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Grecruit grecruit){
        System.out.println(grecruit);
        //修改
        int result = recruitService.updateRecruit(grecruit);
        if(result>0){
            return Result.success();
        }else{
            return Result.error(500,"更新失败");
        }
    }



    @RequestMapping("/listSelect")
    @ResponseBody
    public List<Grecruit> listSelect(){
        //在session中得到user，再得到gid;
        //模拟从session中获取用户id
        Integer uid=2;
        Integer gid=recruitService.findGidByUid(uid);
        System.out.println();
        System.out.println("gid:"+gid);

        //Integer 类型不能直接<if test="">条件，必须封装对象或map;
        Map<String,Integer> map=new HashMap<>();
        map.put("gid",gid);
        List<Grecruit> list=recruitService.findAllGrecruit(map);
        for (Grecruit grecruit : list) {
            grecruit.setGrname(grecruit.getGrname()+"  编号："+grecruit.getGrid());
        }
        System.out.println(list);
        return  list;
    }




















//    @RequestMapping("/test")
//    @ResponseBody
//    public Result test(){
//
//        Date grtime=new Date();
//
//        Integer result=grecruitMapper.test(grtime);
//        if (result>0){
//            return  Result.success();
//        }else {
//            return Result.error(500,"添加失败");
//        }
//    }
}

