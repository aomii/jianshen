package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.CourseService;
import com.rabbit.fitness.dao.WebCouresMapper;
import com.rabbit.fitness.pojo.WebCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private WebCouresMapper webCouresMapper;

    /*分页显示*/
    @Override
    public List<WebCourse> getAll(Integer currentpage, Integer pageSize) {
        return webCouresMapper.selCourseBypage(currentpage, pageSize);
    }

    /*检查重复*/
    @Override
    public WebCourse findCourse(String courseTitle) {
        return webCouresMapper.selCourseByTitle(courseTitle);
    }

    /*添加*/
    @Override
    public Integer addCourse(WebCourse webCourse) {
        return webCouresMapper.insertSelective(webCourse);
    }

    /*修改*/
    @Override
    public int updateCourse(WebCourse webCourse) {
        return webCouresMapper.updateByPrimaryKeySelective(webCourse);
    }

    /*批量删除*/
    @Override
    public int deleteCourse(Integer[] coursesId) {
        return webCouresMapper.deleteCourses(coursesId);
    }

    /*根据课程id找到这个课程*/
    @Override
    public WebCourse finCourseById(Integer webcId) {
        return webCouresMapper.findCourseById(webcId);
    }

}
