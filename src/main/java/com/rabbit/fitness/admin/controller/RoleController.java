package com.rabbit.fitness.admin.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.fitness.admin.service.AuthService;
import com.rabbit.fitness.admin.service.RoleService;
import com.rabbit.fitness.gym.common.TreeNode;
import com.rabbit.fitness.pojo.Auth;
import com.rabbit.fitness.pojo.Role;
import com.rabbit.fitness.utils.Result;
import com.rabbit.fitness.utils.TreeUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/role")
@RequiresRoles(value = "superadmin")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;

    /*获取所有角色名*/
    @RequestMapping("/list")
    public List<Role> list(){
        return roleService.selAllRole();
    }

    /*分页获取角色*/
    @RequestMapping("/page")
    public Map<String,Object> page(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage,
                                   @RequestParam(value="rows",defaultValue = "5",required = false)Integer pageSize, String roleName){
        /*获取所有角色*/
        List<Role> list=roleService.selRoleByPage(currentPage, pageSize, roleName);
        /*放入pageinfo*/
        PageInfo<Role> pageInfo=new PageInfo<>(list);
        /*放入map*/
        Map result=new HashMap();
        result.put("total",pageInfo.getTotal());
        result.put("rows",pageInfo.getList());
        return result;
    }

    /*添加角色*/
    @RequestMapping("/add")
    public Result add(Role role){
        /*检测角色名是否存在*/
        Role role1=roleService.checkRole(role);
        if(role1!=null){
            return Result.error(500,"该角色名已经存在");
        }
        /*添加角色*/
        Integer result=roleService.addRole(role);
        /*判断是否添加成功*/
        if(result>0){
            return Result.success();
        }else{
            return Result.error(500,"角色添加失败");
        }
    }

    /*修改角色*/
    @RequestMapping("update")
    public Result updateRole(Role role){
        /*检查角色名是否存在，可以为自己本身角色名*/
        Role role1=roleService.checkRole(role);
        if(role1!=null){
            if(!role1.getRoleId().equals(role.getRoleId())){
                return  Result.error(500,"该角色名已存在，不允许修改");
            }
        }
        /*修改角色*/
        int result=roleService.updateRole(role);
        /*判断是否修改成功*/
        if(result>0){
            return Result.success();
        }else {
            return Result.error(500,"角色修改失败");
        }
    }

    /*删除角色*/
    @RequestMapping("/delete")
    public Result deleteRole(Integer roleId){
        /*删除角色*/
        int result=roleService.deleteRole(roleId);
        /*判断是否删除成功*/
        if(result>0){
            return Result.success(0,"成功删除"+result+"条角色数据");
        }else {
            return Result.error(500,"删除角色失败");
        }
    }

    /*加载权限树*/
    @RequestMapping("/loadTree/{roleId}")
    public List<TreeNode> loadTree(@PathVariable("roleId")Integer roleId){
        List<Auth> list = authService.findAll();
        String authIds = roleService.selRoleByRid(roleId).getAuthIds();
        return TreeUtils.bulidCheckedTree(list,authIds,-1);
    }

    /*授权*/
    @RequestMapping("/givePower")
    @ResponseBody
    public  Result givePower(Role role){
        System.out.println(role);
        int result=roleService.updateRole(role);
        if(result>0){
            return Result.success(0,"授权成功");
        }
        else {
            return Result.error(500,"授权失败");
        }
    }
}
