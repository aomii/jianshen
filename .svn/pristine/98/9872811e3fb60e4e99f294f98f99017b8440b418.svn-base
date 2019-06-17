package com.rabbit.fitness.dao;

import com.rabbit.fitness.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /*查询所有角色*/
    @Select("select *from t_role")
    List<Role> selAllRole();

    /*分页获取所有角色*/
    List<Role> selRoleByPage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize, @Param("roleName") String roleName);

    /*根据角色名查找角色*/
    @Select("select *from t_role where role_name=#{roleName}")
    Role selRoleByRoleName(String roleName);

    /*删除角色*/
    int deleteRole(Integer[] rolesId);

    /*根据角色id查找这个角色*/
    Role findRoleByRoleId(Integer roleId);

    //查询权限
    @Select("select auth_ids from t_role where role_id=#{id}")
    List<String> queryAuthIds(Integer id);
}