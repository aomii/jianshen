package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.AdService;
import com.rabbit.fitness.dao.AdMapper;
import com.rabbit.fitness.pojo.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdServiceImpl implements AdService {
    @Autowired
    private AdMapper adMapper;

    /*分页显示*/
    @Override
    public List<Ad> getAll(Integer currentPage, Integer pageSize, String adName) {
        return adMapper.selAdByPage(currentPage,pageSize,adName);
    }

    /*检查重复*/
    @Override
    public Ad findAd(String adName) {
        return adMapper.selAdByName(adName);
    }

    /*添加*/
    @Override
    public Integer addAd(Ad ad) {
        return adMapper.insertSelective(ad);
    }

    /*修改*/
    @Override
    public int updateAd(Ad ad) {
        return adMapper.updateByPrimaryKeySelective(ad);
    }

    /*批量删除*/
    @Override
    public int deleteAd(Integer[] adsId) {
        return adMapper.deleteAds(adsId);
    }
}
