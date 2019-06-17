package com.rabbit.fitness.dao;

import com.rabbit.fitness.pojo.Ad;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdMapper {
    int deleteByPrimaryKey(Integer adId);

    int insert(Ad record);

    int insertSelective(Ad record);

    Ad selectByPrimaryKey(Integer adId);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKey(Ad record);

    /*分页显示*/
    List<Ad> selAdByPage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize, @Param("adName") String adName);

    /*检查重复*/
    @Select("select *from ad where ad_name=#{adName}")
    Ad selAdByName(String adName);

    /*批量删除*/
    int deleteAds(Integer[] adsId);
}