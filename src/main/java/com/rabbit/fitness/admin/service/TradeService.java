package com.rabbit.fitness.admin.service;

import com.rabbit.fitness.admin.vo.TradeVo;
import com.rabbit.fitness.pojo.Trade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TradeService {
    /*查看签约*/
    List<Trade> findAll(Integer currentPage, Integer pageSize, TradeVo vo);

    /*后台删除签约or解约*/
    int deleteTradeByStuId(Integer stuid);
    /*签约（增加）*/
    /*续约or签私教（修改）*/
    /*通过stuid获取trade记录*/
    Trade selTradByStudentId(Integer stuid);

    //更新
    int updateTrade(Trade selTrade);

    int addTrade(Trade selTrade);


    int breakCoachTrade(Integer tradeId);
}
