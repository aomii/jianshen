package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.AuthService;
import com.rabbit.fitness.dao.AuthMapper;
import com.rabbit.fitness.pojo.Auth;
import com.rabbit.fitness.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    /*根据用户id和父节点id查询权限*/
    @Override
    public List<Auth> selAuthByParentId(Integer parentId, User user) {
        return authMapper.selAuthByParentID(parentId,user.getUserId());
    }

    /*查询所有权限*/
    @Override
    public List<Auth> selAllAuth(Integer parentId) {
        return authMapper.selAllAuth(parentId);
    }

    /*添加权限*/
    @Override
    public int addAuth(Auth auth) {
        try {
            Integer parentId=auth.getParentId();
            Auth parentAuth=authMapper.selectByPrimaryKey(parentId);
            parentAuth.setState("closed");
            authMapper.updateByPrimaryKeySelective(parentAuth);
            auth.setState("open");
            authMapper.insertSelective(auth);
            return 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*检查重复名*/
    @Override
    public Auth checkAuth(Auth auth) {
        return authMapper.selAuthByAuth(auth);
    }

    /*修改权限*/
    @Override
    public int updateAuth(Auth auth) {
        return authMapper.updateByPrimaryKeySelective(auth);
    }

    /*所有权限*/
    @Override
    public List<Auth> findAll() {
        return authMapper.selAll();
    }

    /*根据父id查询子节点*/
    @Override
    public List<Auth> selChildByParent(Integer parentId) {
        return authMapper.selChildByParent(parentId);
    }

    /*删除节点*/
    @Override
    public int deleteAuth(Integer authId) {
        try {
            /*获取该节点的父节点id*/
            Integer parentId=authMapper.selectByPrimaryKey(authId).getParentId();
            /*获取该节点的父节点对象*/
            Auth parentAuth=authMapper.selectByPrimaryKey(parentId);
            //删除该节点
            authMapper.deleteByPrimaryKey(authId);
            /*获取父节点的所有子节点*/
            List<Auth> authList=authMapper.selChildByParent(parentId);
            /*判断是否还有子节点，如果没有，将父节点的状态改为open*/
            if(authList.size()==0){
                parentAuth.setState("open");
                authMapper.updateByPrimaryKeySelective(parentAuth);
            }
            return 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*根据用户名查找权限*/
    @Override
    public List<String> findPermByUserName(String userName) {
        return authMapper.findPerByUserName(userName);
    }
}
