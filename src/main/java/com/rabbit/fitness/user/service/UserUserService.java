package com.rabbit.fitness.user.service;

import com.rabbit.fitness.pojo.User;

public interface UserUserService {
    User checkUser(String name);
     //注册
    boolean insertUser(User user);
    //修改密码
    boolean insertUser1(User user);

    Integer findRidByUid(Integer uid);

    int updateAddress(User user1);
}
