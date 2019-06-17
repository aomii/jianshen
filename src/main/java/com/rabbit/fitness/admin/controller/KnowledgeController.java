package com.rabbit.fitness.admin.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.fitness.admin.service.KnowledgeService;
import com.rabbit.fitness.pojo.Knowledge;
import com.rabbit.fitness.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;

    //==================以下:前端使用==================================
    /*前端渲染器材列表*/
    @RequestMapping("/listPrevious")
    public ModelAndView listPrevious(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "6",required = false)Integer pageSize){
        List<Knowledge> knowledges=knowledgeService.getAll(currentPage,pageSize);
        /*分页*/
        PageInfo<Knowledge> info=new PageInfo<>(knowledges);
        ModelAndView modelAndView=new ModelAndView("fitness_knowledge");
        modelAndView.addObject("info",info);
        //设置分页page.html中请求路径
        modelAndView.addObject("property","/knowledge/listPrevious");
        return modelAndView;
    }

    //详情
    @RequestMapping("/info")
    public ModelAndView info(Integer knlgId){
        Knowledge knowledge = knowledgeService.getKnlgInfoByKnlgId(knlgId);
        Integer index = knowledgeService.updateKnowLedgeCount(knowledge);
        ModelAndView modelAndView=new ModelAndView("knowledge_details");
        modelAndView.addObject("details",knowledge);
        return  modelAndView;
    }

    //==================以上========================================

    /*分页显示*/
    @RequestMapping("/list")
    @RequiresRoles(value = "superadmin")
    public Map<String,Object> list(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "5",required = false)Integer pageSize){
        List<Knowledge> knowledges=knowledgeService.getAll(currentPage,pageSize);
        /*分页*/
        PageInfo<Knowledge> Info=new PageInfo<>(knowledges);
        /*返回前端数据*/
        Map map=new HashMap();
        map.put("total",Info.getTotal());
        map.put("rows",Info.getList());
        map.put("info",Info);
        return map;
    }

    /*增加*/
    @RequestMapping("/add")
    @RequiresRoles(value = "superadmin")
    public Result addKnowledge(Knowledge knowledge){
        /*检查是否存在*/
        Knowledge knowledge1=knowledgeService.findKnowledge(knowledge.getKnlgTitle());
        if(knowledge1!=null){
            return Result.error(500,"该条知识已经存在");
        }
        /*添加*/
        Integer result=knowledgeService.addKnowledge(knowledge);
        /*判断是否添加成功*/
        if(result>0){
            return  Result.success(0,"添加成功");
        }else{
            return  Result.error(500,"添加失败");
        }

    }

    /*修改*/
    @RequestMapping("/update")
    @RequiresRoles(value = "superadmin")
    public Result updateKnowledge(Knowledge knowledge){
        /*检查是否重复，可以为自己本身标题*/
        Knowledge knowledge1=knowledgeService.findKnowledge(knowledge.getKnlgTitle());
        if(knowledge1!=null){
            if(!knowledge1.getKnlgTitle().equals(knowledge.getKnlgTitle())){
                return  Result.error(500,"该知识已存在，不允许修改");
            }
        }
        /*修改*/
        int result=knowledgeService.updateKnowledge(knowledge);
        /*判断是否修改成功*/
        if(result>0){
            return Result.success();
        }else {
            return Result.error(500,"修改失败");
        }
    }

    /*删除*/
    @RequestMapping("/delete")
    @RequiresRoles(value = "superadmin")
    public Result deleteKnowledge(String knowledgeIds){
        /*分割数组，获取所有要删除的id */
        String[] arr=knowledgeIds.split(",");
        /*转换为整数数组*/
        Integer[] knowledgesId=new Integer[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            knowledgesId[i]=Integer.parseInt(arr[i]);
        }
        /*删除*/
        int result=knowledgeService.deleteKnowledge(knowledgesId);
        /*判断是否成功*/
        if(result>0){
            return Result.success(0,"删除"+result+"条数据成功");
        }else {
            return Result.error(500,"删除失败");
        }
    }
}
