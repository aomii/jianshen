package com.rabbit.fitness.dao;

import com.rabbit.fitness.gym.vo.RecruitCoachVo;
import com.rabbit.fitness.pojo.Coach;
import com.rabbit.fitness.pojo.GrecruitCoach;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GrecruitCoachMapper {
    int deleteByPrimaryKey(Integer gcId);

    int insert(GrecruitCoach record);

    int insertSelective(GrecruitCoach record);

    GrecruitCoach selectByPrimaryKey(Integer gcId);

    int updateByPrimaryKeySelective(GrecruitCoach record);

    int updateByPrimaryKey(GrecruitCoach record);


    List<GrecruitCoach> findAllCoach(Integer grid);

    Coach selectCoach(Integer cid);

    List<GrecruitCoach> findRecordByPage(Integer currentPage, Integer pageSize, RecruitCoachVo vo);

    GrecruitCoach findRecuitCoachByGcid(Integer gcId);
}