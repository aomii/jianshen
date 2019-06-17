package com.rabbit.fitness.gym.service.impl;

import com.rabbit.fitness.dao.CoachMapper;
import com.rabbit.fitness.dao.GrecruitCoachMapper;
import com.rabbit.fitness.dao.GymMapper;
import com.rabbit.fitness.gym.service.RecruitCoachService;
import com.rabbit.fitness.gym.vo.RecruitCoachVo;

import com.rabbit.fitness.pojo.Coach;
import com.rabbit.fitness.pojo.GrecruitCoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecruitCoachServiceImpl  implements RecruitCoachService {

    @Autowired
    private GrecruitCoachMapper grecruitCoachMapper;

    @Autowired
    private GymMapper gymMapper;

    @Autowired
    private CoachMapper coachMapper;

    @Override
    public List<GrecruitCoach> list(Integer grid) {
        return grecruitCoachMapper.findAllCoach(grid);
    }

    @Override
    public Integer findGidByUid(Integer uid) {
        return gymMapper.findGidByUid(uid);
    }

    @Override
    public List<GrecruitCoach> findRecordByPage(Integer currentPage, Integer pageSize, RecruitCoachVo vo) {
        return grecruitCoachMapper.findRecordByPage(currentPage,pageSize,vo);
    }

    @Override
    public GrecruitCoach findRecuitCoachByGcid(Integer gcId) {
        GrecruitCoach grecruitCoach=grecruitCoachMapper.findRecuitCoachByGcid(gcId);
        //1查看是否为未查看状态
        if ("未查看".equals(grecruitCoach.getIsDispose())){
            //2修改申请记录表，将isDispose设置已查看

            GrecruitCoach grecruitCoach2=new GrecruitCoach();
            Coach coach=new Coach();
            grecruitCoach2.setCoach(coach);
            grecruitCoach2.setGcId(gcId);
            grecruitCoach2.setIsDispose("已查看");
            grecruitCoachMapper.updateByPrimaryKeySelective(grecruitCoach2);
        }
        return grecruitCoach;

    }

    @Override
    public Integer update(GrecruitCoach grecruitCoach,Integer gid) {
        //1.修改申请记录表，将isDispose设置已接受
        grecruitCoach.setIsDispose("已接受");
        Integer result1=grecruitCoachMapper.updateByPrimaryKeySelective(grecruitCoach);

        //2.修改教练表的gid为当前场馆;
        Integer result2=coachMapper.updateBack(grecruitCoach.getCoach().getCid(),gid);

        if (result1>0 && result2>0){
            return 1;
        }
        return 0;

    }
}
