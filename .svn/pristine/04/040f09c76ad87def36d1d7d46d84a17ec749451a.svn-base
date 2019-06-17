package com.rabbit.fitness.admin.service;

import com.rabbit.fitness.pojo.Ad;

import java.util.List;

public interface AdService {
    /*分页显示*/
    List<Ad> getAll(Integer currentPage, Integer pageSize, String adName);

    /*检查重复*/
    Ad findAd(String adName);

    /*添加*/
    Integer addAd(Ad ad);

    /*修改*/
    int updateAd(Ad ad);

    /*批量删除*/
    int deleteAd(Integer[] adsId);
}
