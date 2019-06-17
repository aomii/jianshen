package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.KnowledgeService;
import com.rabbit.fitness.dao.KnowledgeMapper;
import com.rabbit.fitness.pojo.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeMapper knowledgeMapper;


    /*分页显示*/
    @Override
    public List<Knowledge> getAll(Integer currentpage,Integer pageSize) {
        return knowledgeMapper.selKnowBypage(currentpage,pageSize);
    }

    /*检查重复*/
    @Override
    public Knowledge findKnowledge(String knlgTitle) {
        return knowledgeMapper.selKnowByTitle(knlgTitle);
    }

    /*添加*/
    @Override
    public Integer addKnowledge(Knowledge knowledge) {
        return knowledgeMapper.insertSelective(knowledge);
    }

    /*修改*/
    @Override
    public int updateKnowledge(Knowledge knowledge) {
        return knowledgeMapper.updateByPrimaryKeySelective(knowledge);
    }

    /*批量删除*/
    @Override
    public int deleteKnowledge(Integer[] knowledgesId) {
        return knowledgeMapper.deleteKnowledges(knowledgesId);
    }

    /*拿到健身知识的详情介绍*/
    @Override
    public Knowledge getKnlgInfoByKnlgId(Integer knlgId) {
        return knowledgeMapper.selectByPrimaryKey(knlgId);
    }

    /*次数加1*/
    @Override
    public Integer updateKnowLedgeCount(Knowledge knowledge) {
        knowledge.setKnlgViewcount(knowledge.getKnlgViewcount() + 1);
        return knowledgeMapper.updateByPrimaryKeySelective(knowledge);
    }
}
