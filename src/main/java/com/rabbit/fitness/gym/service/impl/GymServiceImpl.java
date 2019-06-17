package com.rabbit.fitness.gym.service.impl;

import com.rabbit.fitness.dao.GymMapper;
import com.rabbit.fitness.gym.service.GymService;
import com.rabbit.fitness.gym.vo.GymVo;
import com.rabbit.fitness.pojo.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymServiceImpl implements GymService {

    @Autowired
    private GymMapper gymMapper;

    @Override
    public List<Gym> findAll() {
        return gymMapper.findAll();
    }

    @Override
    public Gym info(int gid) {
        return gymMapper.selectByPrimaryKey(gid);
    }


    @Override
    public List<Gym> findGymByPage(Integer currentPage, Integer pageSize, GymVo vo) {
        return gymMapper.findGymByPage(currentPage,pageSize,vo);
    }

    @Override
    public Integer add(Gym gym) {
        return gymMapper.insertSelective(gym);
    }

    @Override
    public int updateGym(Gym gym) {
        return gymMapper.updateByPrimaryKeySelective(gym);
    }

    @Override
    public int deleteGym(Integer[] gids) {
        return gymMapper.deleteGym(gids);
    }

    @Override
    public Integer findGidByUid(Integer userId) {
        return gymMapper.findGidByUid(userId);
    }

    @Override
    public Gym findGymByGid(int gymId) {
        return gymMapper.selectByPrimaryKey(gymId);
    }


}

