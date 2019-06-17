package com.rabbit.fitness.dao;

import com.rabbit.fitness.admin.vo.TradeVo;
import com.rabbit.fitness.pojo.Trade;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeMapper {
    int deleteByPrimaryKey(Integer tradeId);

    int insert(Trade record);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(Integer tradeId);

    int updateByPrimaryKeySelective(Trade trade);

    int updateByPrimaryKey(Trade trade);

    List<Trade> selAll(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize, @Param("vo")TradeVo vo);

    @Delete("delete from trade where trade_student = #{stuid}")
    int deleteByStuId(Integer stuid);

    Trade queryTradeByStudentId(@Param("stuid") Integer stuid);

    int setCoachIsNull(@Param("tradeId") Integer tradeId);

    int setGymIsNull(@Param("tradeId") Integer tradeId);
}