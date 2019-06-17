package com.rabbit.fitness.admin.service;

import com.rabbit.fitness.pojo.WebCourse;

import java.util.List;

public interface CourseService {
    /*分页显示*/
    List<WebCourse> getAll(Integer currentPage, Integer pageSize);

    /*检查重复*/
    WebCourse findCourse(String courseTitle);

    /*增加*/
    Integer addCourse(WebCourse webCourse);

    /*修改*/
    int updateCourse(WebCourse webCourse);

    /*批量删除*/
    int deleteCourse(Integer[] coursesId);

    /*根据服务id找到这个服务*/
    WebCourse finCourseById(Integer webcid);
}
