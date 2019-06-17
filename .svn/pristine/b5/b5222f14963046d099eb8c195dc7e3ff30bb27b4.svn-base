package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.UserService;
import com.rabbit.fitness.admin.vo.UserVo;
import com.rabbit.fitness.dao.UserMapper;
import com.rabbit.fitness.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /*根据用户名查找用户并比较密码*/
    @Override
    public User findUser(User user) {
        User dbUser = userMapper.findUserByName(user.getUserName());
        //加密比较
        if(dbUser!=null){
            if(user.getPassword().equals(dbUser.getPassword())){
                return dbUser;
            }
        }
        return null;
    }

    /*根据用户名查找用户*/
    @Override
    public User findUser(String userName) {
        return userMapper.findUserByName(userName);
    }

    /*分页查找所有用户*/
    @Override
    public List<User> selUserByPage(Integer currentPage, Integer pageSize, UserVo vo) {
        return userMapper.selUserByPage(currentPage,pageSize,vo);
    }

    /*添加用户*/
    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    /*修改用户*/
    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /*删除用户*/
    @Override
    public int deleteUser(Integer[] usersId) {
        return userMapper.deleteUsers(usersId);
    }




}
