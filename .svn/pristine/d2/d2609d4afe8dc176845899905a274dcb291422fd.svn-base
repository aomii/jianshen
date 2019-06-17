package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.RoleService;
import com.rabbit.fitness.dao.RoleMapper;
import com.rabbit.fitness.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /*查询所有角色*/
    @Override
    public List<Role> selAllRole() {
        return roleMapper.selAllRole();
    }

    /*分页查询角色*/
    @Override
    public List<Role> selRoleByPage(Integer currentPage, Integer pageSize, String roleName) {
        return roleMapper.selRoleByPage(currentPage,pageSize,roleName);
    }

    /*检测角色是否存在*/
    @Override
    public Role checkRole(Role role) {
        return roleMapper.selRoleByRoleName(role.getRoleName());
    }

    /*添加角色*/
    @Override
    public int addRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    /*修改角色*/
    @Override
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    /*删除角色*/
    @Override
    public int deleteRole(Integer roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    /*根据roleId查询Role*/
    @Override
    public Role selRoleByRid(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

}
