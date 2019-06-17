package com.rabbit.fitness.user.service;

import com.rabbit.fitness.dao.RoleMapper;
import com.rabbit.fitness.dao.UserMapper;
import com.rabbit.fitness.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> findPermsByUserName(Integer id) {
        return roleMapper.queryAuthIds(id);
    }

    //根据角色id查找这个角色
    @Override
    public Role findRoleByRoleId(Integer roleId) {
        return roleMapper.findRoleByRoleId(roleId);
    }
}
