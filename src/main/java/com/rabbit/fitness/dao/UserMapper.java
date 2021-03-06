package com.rabbit.fitness.dao;

import com.rabbit.fitness.admin.vo.UserVo;
import com.rabbit.fitness.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /*根据用户名查找用户*/
    User findUserByName(String userName);

    /*分页查询所有用户*/
    List<User> selUserByPage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize, @Param("vo")UserVo vo);

    /*批量删除用户*/
    int deleteUsers(Integer[] usersId);
    //修改密码
    @Update("update t_user set password=#{password} where user_name=#{userName}")
    int insertSelective1(User record);

    //通过uid查rid
    @Select("select role_id from t_user where user_id=#{uid}")
    Integer queryRidByUid(Integer uid);
//更新地址
    @Update("update t_user set address=#{address},lastaddress=#{lastaddress} where user_id=#{userId}")
    int updateAddress(User user1);
}