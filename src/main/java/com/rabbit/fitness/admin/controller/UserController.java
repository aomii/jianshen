package com.rabbit.fitness.admin.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.fitness.admin.service.UserService;
import com.rabbit.fitness.admin.vo.UserVo;
import com.rabbit.fitness.pojo.User;
import com.rabbit.fitness.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
@RequiresRoles(value = "superadmin")
public class UserController {
    @Autowired
    private UserService userService;
    /*登录*/


    /*分页显示所有用户*/
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "5",required = false)Integer pageSize, UserVo vo){
        System.out.println("请求数据");
        /*获取所有用户*/
        List<User> users=userService.selUserByPage(currentPage,pageSize,vo);
        /*分页*/
        PageInfo<User> Info=new PageInfo<>(users);
        /*返回前端数据*/
        Map map=new HashMap();
        map.put("total",Info.getTotal());
        map.put("rows",Info.getList());
        return map;
    }

    /*增加用户*/
    @RequestMapping("/add")
    public Result addUser(User user){
        /*检查用户是否存在*/
        User user1=userService.findUser(user.getUserName());
        if(user1!=null){
            return Result.error(500,"该用户已经存在");
        }
        /*添加用户*/
        Integer result=userService.addUser(user);
        /*判断是否添加成功*/
        if(result>0){
            return  Result.success(0,"添加成功");
        }else{
            return  Result.error(500,"用户添加失败");
        }

    }

    /*修改用户*/
    @RequestMapping("/update")
    public Result updateUser(User user){
        /*检查用户名是否重复，可以为自己本身用户名*/
        User user1=userService.findUser(user.getUserName());
        if(user1!=null){
            if(!user1.getUserId().equals(user.getUserId())){
                return  Result.error(500,"该用户名已存在，不允许修改");
            }
        }
        /*修改用户*/
        int result=userService.updateUser(user);
        /*判断是否修改成功*/
        if(result>0){
            return Result.success();
        }else {
            return Result.error(500,"修改失败");
        }

    }

    /*删除用户*/
    @RequestMapping("/delete")
    public Result deleteUser(String userIds){
        /*分割数组，获取所有要删除的用户id */
        String[] arr=userIds.split(",");
        /*转换为整数数组*/
        Integer[] usersId=new Integer[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            usersId[i]=Integer.parseInt(arr[i]);
        }
        /*删除用户*/
        int result=userService.deleteUser(usersId);
        /*判断是否成功*/
        if(result>0){
            return Result.success(0,"删除"+result+"条数据成功");
        }else {
            return Result.error(500,"删除失败");
        }
    }

    /*修改密码*/
    @RequestMapping("/alterPwd")
    public Result alterPwd(String oldPwd,String newPwd,String newPwd1){
        /*获取当前用户*/
        User user=userService.findUser("admin");
        /*验证原密码是否正确*/
        if(!oldPwd.equals(user.getPassword())){
            return Result.error(500,"原密码错误,请重新输入");
        }
        /*验证两次新密码是否一致*/
        if(!newPwd.equals(newPwd1)){
            return Result.error(500,"两次输入不一致,请重新输入");
        }
        /*修改密码*/
        user.setPassword(newPwd);
        int result=userService.updateUser(user);
        /*判断是否修改成功*/
        if(result>0){
            return Result.success();
        }else {
            return Result.error(500,"修改失败");
        }
    }
}
