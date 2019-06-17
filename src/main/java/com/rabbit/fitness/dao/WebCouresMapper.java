package com.rabbit.fitness.dao;

import com.rabbit.fitness.pojo.WebCourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WebCouresMapper {
    int deleteByPrimaryKey(Integer webcId);

    int insert(WebCourse record);

    int insertSelective(WebCourse record);

    WebCourse selectByPrimaryKey(Integer webcId);

    int updateByPrimaryKeySelective(WebCourse record);

    int updateByPrimaryKey(WebCourse record);

    /*分页显示*/
    List<WebCourse> selCourseBypage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /*检测重复*/
    @Select("select *from webcourse where webc_title=#{courseTitle}")
    WebCourse selCourseByTitle(String courseTitle);

    /*批量删除*/
    int deleteCourses(Integer[] coursesId);

    WebCourse findCourseById(@Param("webcId") Integer webcId);
}