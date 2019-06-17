package com.rabbit.fitness.dao;

import com.rabbit.fitness.pojo.Discount;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DiscountMapper {
    int deleteByPrimaryKey(Integer discountId);

    int insert(Discount record);

    int insertSelective(Discount record);

    Discount selectByPrimaryKey(Integer discountId);

    int updateByPrimaryKeySelective(Discount record);

    int updateByPrimaryKey(Discount record);

    /*获取所有折扣*/
    @Select("select *from discount")
    List<Discount> selAll();
}