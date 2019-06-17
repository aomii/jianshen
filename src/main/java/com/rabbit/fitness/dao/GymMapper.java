package com.rabbit.fitness.dao;

import com.rabbit.fitness.gym.vo.GymVo;
import com.rabbit.fitness.pojo.Gym;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GymMapper {
    int deleteByPrimaryKey(Integer gid);

    int insert(Gym record);

    int insertSelective(Gym record);

    Gym selectByPrimaryKey(Integer gid);

    int updateByPrimaryKeySelective(Gym record);

    int updateByPrimaryKey(Gym record);

    List<Gym> findAll();

    List<Gym> findGymByPage(@Param("currentPage") Integer currentPage,
                            @Param("pageSize")Integer pageSize,
                            @Param("vo") GymVo vo);

    int deleteGym(Integer[] gids);

    /*通过uid查询gid*/
    Integer findGidByUid(Integer uid);
}