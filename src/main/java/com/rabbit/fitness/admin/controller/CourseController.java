package com.rabbit.fitness.admin.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.fitness.admin.service.CourseService;
import com.rabbit.fitness.pojo.WebCourse;
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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //==================以下:前端使用==================================
    /*前端渲染器材列表*/
    @RequestMapping("/listPrevious")
    public ModelAndView listPrevious(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "6",required = false)Integer pageSize){
        List<WebCourse> webCourses=courseService.getAll(currentPage,pageSize);
        /*分页*/
        PageInfo<WebCourse> info=new PageInfo<>(webCourses);
        ModelAndView modelAndView=new ModelAndView("fitness_course");
        modelAndView.addObject("info",info);

        //设置分页page.html中请求路径
        modelAndView.addObject("property","/course/listPrevious");
        return modelAndView;
    }
    //详情
    @RequestMapping("/info")
    public ModelAndView info(Integer webcId){
        WebCourse course = courseService.finCourseById(webcId);
        ModelAndView modelAndView=new ModelAndView("course_details");
        modelAndView.addObject("details",course);
        return  modelAndView;
    }

    //==================以上========================================

    /*分页显示*/
    @RequestMapping("/list")
    @RequiresRoles(value = "superadmin")
    public Map<String,Object> list(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "5",required = false)Integer pageSize){
        List<WebCourse> webCourses=courseService.getAll(currentPage,pageSize);
        /*分页*/
        PageInfo<WebCourse> Info=new PageInfo<>(webCourses);
        /*返回前端数据*/
        Map map=new HashMap();
        map.put("total",Info.getTotal());
        map.put("rows",Info.getList());

        return map;
    }

    /*增加*/
    @RequestMapping("/add")
    @RequiresRoles(value = "superadmin")
    public Result addCourse(WebCourse webCourse){
        /*检查是否存在*/
        WebCourse webCourse1=courseService.findCourse(webCourse.getWebcTitle());
        if(webCourse1!=null){
            return Result.error(500,"该课程已经存在");
        }
        /*添加*/
        Integer result=courseService.addCourse(webCourse);
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
    public Result updateCourse(WebCourse webCourse){
        /*检查是否重复，可以为自己本身标题*/
        WebCourse webCourse1=courseService.findCourse(webCourse.getWebcTitle());
        if(webCourse1!=null){
            if(!webCourse1.getWebcTitle().equals(webCourse.getWebcTitle())){
                return  Result.error(500,"该课程已存在，不允许修改");
            }
        }
        /*修改*/
        int result=courseService.updateCourse(webCourse);
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
    public Result deleteCourse(String courseIds){
        /*分割数组，获取所有要删除的id */
        String[] arr=courseIds.split(",");
        /*转换为整数数组*/
        Integer[] coursesId=new Integer[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            coursesId[i]=Integer.parseInt(arr[i]);
        }
        /*删除*/
        int result=courseService.deleteCourse(coursesId);
        /*判断是否成功*/
        if(result>0){
            return Result.success(0,"删除"+result+"条数据成功");
        }else {
            return Result.error(500,"删除失败");
        }
    }
}
