package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.TradeService;
import com.rabbit.fitness.admin.vo.TradeVo;
import com.rabbit.fitness.dao.GymMapper;
import com.rabbit.fitness.dao.TradeMapper;
import com.rabbit.fitness.pojo.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeMapper tradeMapper;
    @Autowired
    private GymMapper gymMapper;

    /*查看签约*/
    @Override
    public List<Trade> findAll(Integer currentPage, Integer pageSize, TradeVo vo) {
        int userId=1;
        Integer gid=gymMapper.findGidByUid(userId);
        /*根据uid查找gid 添加查询条件*/
        if(gid!=null){
            vo.setGid(gid);
        }
        return tradeMapper.selAll(currentPage,pageSize,vo);
    }

    /*后台删除签约or解约*/
    @Override
    public int deleteTradeByStuId(Integer stuid) {
        return tradeMapper.deleteByStuId(stuid);
    }
    /*购买（办卡）, trade
    * 1.根据stuid查询签阅表是否有记录，如果有，修改对应记录（续约）
    * 2.如果没有,修改student表中的gid
    * 3.添加trade记录
    * */
    /*签私教（修改）
    *1.修改student表中的cid
    * 2.修改trade表记录
    * */

    /*通过stuid查询trade记录*/
    @Override
    public Trade selTradByStudentId(Integer stuid) {
        return tradeMapper.queryTradeByStudentId(stuid);
    }

    @Override
    public int updateTrade(Trade selTrade) {
        return tradeMapper.updateByPrimaryKeySelective(selTrade);
    }

    @Override
    public int addTrade(Trade selTrade) {
        return tradeMapper.insertSelective(selTrade);
    }

    @Override
    public int breakCoachTrade(Integer tradeId) {
        return tradeMapper.setCoachIsNull(tradeId);
    }


}
