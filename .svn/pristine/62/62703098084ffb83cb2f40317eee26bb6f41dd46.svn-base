package com.rabbit.fitness.gym.service;

import com.rabbit.fitness.gym.vo.RecruitVo;
import com.rabbit.fitness.pojo.Grecruit;

import java.util.List;
import java.util.Map;

public interface RecruitService {


    List<Grecruit> findGrecruitByPage(Integer currentPage, Integer pageSize, RecruitVo vo);

    //添加
    Integer add(Grecruit grecruit);

    //删除
    int deleteRecruit(Integer[] grIds);

    //修改
    int updateRecruit(Grecruit grecruit);

    //通过uid查询gid
    Integer findGidByUid(Integer uid);

    //申请记录的搜索下拉框
    List<Grecruit> findAllGrecruit(Map map);
}
