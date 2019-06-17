package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.DiscountService;
import com.rabbit.fitness.dao.DiscountMapper;
import com.rabbit.fitness.pojo.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountMapper discountMapper;

    @Override
    public List<Discount> selAllDiscount() {
        return discountMapper.selAll();
    }
}
