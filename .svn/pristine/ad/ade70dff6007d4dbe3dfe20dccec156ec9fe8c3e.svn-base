package com.rabbit.fitness.admin.service;

import com.rabbit.fitness.pojo.Role;

import java.util.List;

public interface RoleService {
    /*查询所有角色*/
    List<Role> selAllRole();

    /*分页查询角色*/
    List<Role> selRoleByPage(Integer currentPage,Integer pageSize,String roleName);

    /*检测角色是否存在*/
    Role checkRole(Role role);

    /*添加角色*/
    int addRole(Role role);

    /*修改角色*/
    int updateRole(Role role);

    /*删除角色*/
    int deleteRole(Integer roleId);

    /*根据roleId查询role*/
    Role selRoleByRid(Integer roleId);
}
