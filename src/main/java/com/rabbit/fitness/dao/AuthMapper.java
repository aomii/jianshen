package com.rabbit.fitness.dao;

import com.rabbit.fitness.pojo.Auth;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AuthMapper {
    int deleteByPrimaryKey(Integer authId);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    /*根据用户id和父节点id查询权限*/
    List<Auth> selAuthByParentID(@Param("parentId")Integer parentId,@Param("userId")Integer userId);

    /*查询所有权限*/
    List<Auth> selAllAuth(@Param("parentId")Integer parentId);

    /*根据权限名查询权限*/
    @Select("select * from t_auth where (auth_name = #{authName} and perms is null)\n" +
            "      or (perms is not null and parent_id = #{parentId} and auth_name = #{authName}) ")
    Auth selAuthByAuth(Auth auth);

    /*所有权限*/
    @Select("select *from t_auth")
    List<Auth> selAll();

    /*根据父id查询子节点*/
    @Select("select *from t_auth where parent_id=#{parentId}")
    List<Auth> selChildByParent(Integer parentId);

    /*根据用户名查找权限*/
    @Select("select perms from t_auth where find_in_set(auth_id,(select auth_ids from t_role where " +
            "role_id=(select role_id from t_user where user_name=#{userName})))and perms is not null")
    List<String> findPerByUserName(String userName);
}