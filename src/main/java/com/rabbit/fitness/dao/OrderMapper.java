package com.rabbit.fitness.dao;

import com.rabbit.fitness.pojo.Order;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OrderMapper {

    @Select("select * from order where oid=#{oid}")
    Order queryOrderById(Integer oid);

    @Update("update order set ostate=#{ostate} where oid=#{oid}")
    void updateOrder(Order order);
}
