package com.rabbit.fitness.dao;

import com.rabbit.fitness.pojo.Equipment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EquipmentMapper {
    int deleteByPrimaryKey(Integer equipId);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Integer equipId);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

    /*分页显示*/
    List<Equipment> selEquipByPage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize, @Param("equipName") String equipName);

    /*检查重复*/
    @Select("select *from equipment where equip_name=#{equipName}")
    Equipment selEquipByName(String equipName);

    /*批量删除*/
    int deleteEquips(Integer[] equipsId);
}