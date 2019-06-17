package com.rabbit.fitness.admin.service;

import com.rabbit.fitness.pojo.Auth;
import com.rabbit.fitness.pojo.User;

import java.util.List;

public interface AuthService {
    /*根据用户id和父节点查询权限*/
    List<Auth> selAuthByParentId(Integer parentId, User user);

    /*查询所有权限*/
    List<Auth> selAllAuth(Integer parentId);

    /*添加权限*/
    int addAuth(Auth auth);

    /*根据权限名查询权限*/
    Auth checkAuth(Auth auth);

    /*修改权限*/
    int updateAuth(Auth auth);

    /*查询所有权限*/
    List<Auth> findAll();

    /*根据父id查询子节点*/
    List<Auth> selChildByParent(Integer parentId);

    /*删除节点*/
    int deleteAuth(Integer authId);

    /*根据用户名查找权限*/
    List<String> findPermByUserName(String userName);
}