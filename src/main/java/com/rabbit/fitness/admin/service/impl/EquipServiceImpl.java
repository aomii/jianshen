package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.EquipService;
import com.rabbit.fitness.dao.EquipmentMapper;
import com.rabbit.fitness.pojo.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class EquipServiceImpl implements EquipService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    /*分页显示*/
    @Override
    public List<Equipment> getAll(Integer currentPage, Integer pageSize, String equipName) {
        return equipmentMapper.selEquipByPage(currentPage,pageSize,equipName);
    }

    /*检查重复*/
    @Override
    public Equipment findEquip(String equipName) {
        return equipmentMapper.selEquipByName(equipName);
    }

    /*添加*/
    @Override
    public Integer addEquip(Equipment equipment) {
        return equipmentMapper.insertSelective(equipment);
    }

    /*修改*/
    @Override
    public int updateEquip(Equipment equipment) {
        return equipmentMapper.updateByPrimaryKeySelective(equipment);
    }

    /*批量删除*/
    @Override
    public int deleteEquip(Integer[] equipsId) {
        return equipmentMapper.deleteEquips(equipsId);
    }

    //=========前端使用=======================================
    /*详情*/
    @Override
    public Equipment findEquipByEquipId(Integer equipId) {
        return equipmentMapper.selectByPrimaryKey(equipId);
    }

    /*更新点击数*/
    @Override
    public Integer updateEquipCount(Equipment equip) {
        equip.setEquipCount(equip.getEquipCount() + 1);
        return equipmentMapper.updateByPrimaryKeySelective(equip);
    }
    //=================================================
}
