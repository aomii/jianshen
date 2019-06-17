package com.rabbit.fitness.admin.controller;

import com.rabbit.fitness.admin.service.DiscountService;
import com.rabbit.fitness.pojo.Discount;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discount")
@RequiresRoles(value = "superadmin")
public class DiscountCotroller {
    @Autowired
    private DiscountService discountService;

    /*获取所有折扣*/
    @RequestMapping("/list")
    @ResponseBody
    public List<Discount> list(){
        return discountService.selAllDiscount();
    }

}
