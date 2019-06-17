package com.rabbit.fitness.admin.service;

import com.rabbit.fitness.admin.vo.UserVo;
import com.rabbit.fitness.pojo.User;

import java.util.List;

public interface UserService {
    /*根据用户名查找用户并比较密码*/
    User findUser(User user);

    /*根据用户名查找用户*/
    User findUser(String userName);

    /*分页查找所有的用户*/
    List<User> selUserByPage(Integer currentPage, Integer pageSize, UserVo vo);

    /*添加用户*/
    int addUser(User user);

    /*修改用户*/
    int updateUser(User user);

    /*删除用户*/
    int deleteUser(Integer[] usersId);

}
