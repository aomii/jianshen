package com.rabbit.fitness.coach.vo;

import com.rabbit.fitness.coach.coachService.CoachService;
import com.rabbit.fitness.pojo.Coach;

import java.util.List;
import java.util.Random;


public class SelCoachVO {

    private Integer maxIndex;

    public static   Integer COUNT=6;




    public List<Coach> randomSelCoach(CoachService coachService){
        maxIndex = coachService.selMaxIndex()-COUNT;
        int startIndex = (int)new Random().nextInt(maxIndex);
        List<Coach> list = coachService.roundSelCoach(startIndex,COUNT);
        return list;
    }
}
