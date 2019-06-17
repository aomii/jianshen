package com.rabbit.fitness.student.service.impl;

import com.rabbit.fitness.dao.StudentOrderMapper;
import com.rabbit.fitness.pojo.StudentOrder;
import com.rabbit.fitness.student.service.StudentOrderService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: fitness
 * @description: ${description}
 * @author: ning4256
 * @create: 2019-04-29 14:34
 **/
@Service
@Transactional
public class StudentOrderServiceImpl implements StudentOrderService {
    @Autowired
    private StudentOrderMapper studentOrderMapper;


    @Override
    public StudentOrder findStudentOrderById(Integer stuid) {
        StudentOrder studentOrderById = studentOrderMapper.findStudentOrderById(stuid);
        return studentOrderById;
    }
}
