package com.rabbit.fitness.gym.service;

import com.rabbit.fitness.gym.vo.RecruitCoachVo;
import com.rabbit.fitness.pojo.GrecruitCoach;

import java.util.List;

public interface RecruitCoachService {
    List<GrecruitCoach> list(Integer grid);

    Integer findGidByUid(Integer uid);

    List<GrecruitCoach> findRecordByPage(Integer currentPage, Integer pageSize, RecruitCoachVo vo);

    GrecruitCoach findRecuitCoachByGcid(Integer gcId);

    Integer update(GrecruitCoach grecruitCoach,Integer gid);
}
