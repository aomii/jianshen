package com.rabbit.fitness.gym.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.fitness.constant.SysConstant;
import com.rabbit.fitness.gym.service.GymService;
import com.rabbit.fitness.gym.vo.GymVo;
import com.rabbit.fitness.pojo.Gym;
import com.rabbit.fitness.pojo.User;
import com.rabbit.fitness.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gym")
public class GymController {
    @Autowired
    private GymService gymService;

    //==================以下:前端使用==================================
    /*前端渲染器材列表*/
    @RequestMapping("/listPrevious")
    public ModelAndView listPrevious(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "6",required = false)Integer pageSize, GymVo vo){
        List<Gym> list=gymService.findGymByPage(currentPage,pageSize,vo);
        PageInfo<Gym> info=new PageInfo<>(list);
        ModelAndView modelAndView=new ModelAndView("fitness_gym");
        modelAndView.addObject("info",info);
        //设置分页page.html中请求路径
        modelAndView.addObject("property","/gym/listPrevious");
        return modelAndView;
    }

    //详情
    @RequestMapping("/gymInfo")
    public ModelAndView info(Integer gid){
        Gym gym = gymService.info(gid);
        ModelAndView modelAndView=new ModelAndView("gym_details");
        modelAndView.addObject("details",gym);
        return  modelAndView;
    }
    //================前端使用结束========================================

    //1.展示所有场地
    @RequestMapping("/list")
    @ResponseBody
    public List<Gym> list(){
        List<Gym> list=gymService.findAll();
        return  list;
    }

    //2.查看当前场馆信息
    @RequestMapping("/infor")
    @ResponseBody
    @RequiresRoles(value = "gymadmin")
    public  Gym  infor(HttpSession session){
        //在session中得到user，再得到gid;
        //模拟从session中获取用户id
        User user=(User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer gid=gymService.findGidByUid(user.getUserId());
        return  gymService.info(gid);
    }




/*场地管理——admin 操作*/
    @RequestMapping("/listBack")
    @ResponseBody
    @RequiresPermissions(value = "sys:gym:list")
    public Map<String,Object> list(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage,
                                   @RequestParam(value = "rows",defaultValue = "5",required = false)Integer pageSize, GymVo vo){
        List<Gym> list=gymService.findGymByPage(currentPage,pageSize,vo);
        PageInfo<Gym> pageInfo=new PageInfo<>(list);
        Map<String,Object> result=new HashMap<>();
        result.put("total",pageInfo.getTotal());
        result.put("rows",pageInfo.getList());
        return  result;
    }

    @Value("${image-path}")
    public String path;

    @RequestMapping("/add")
    @ResponseBody
    @RequiresPermissions(value = "sys:gym:add")
    public Result add(MultipartFile img,Gym gym) throws IOException {
        String fileName = img.getOriginalFilename();
                if(fileName!=null&&!"".equals(fileName)){
                    System.out.println(fileName);
                    System.out.println(path);
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            path=path+File.separator+fileName;
            file = new File(path);
            img.transferTo(file);   //自动创建文件、写入数据
            gym.setGpic("/upload/"+fileName);
        }
        Integer result=gymService.add(gym);
        if (result>0){
            return  Result.success();
        }else {
            return Result.error(500,"添加失败");
        }
    }



    //修改
    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions(value = "sys:gym:update")
    public Result update(Gym gym){
        //修改
        int result = gymService.updateGym(gym);
        if(result>0){
            return Result.success();
        }else{
            return Result.error(500,"更新失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(value = "sys:gym:delete")
    public Result delete(String gids){
        System.out.println(gids);
        String [] arr=gids.split(",");
        Integer[] gIds=new Integer[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            gIds[i]=Integer.parseInt(arr[i]);
        }
        int result=gymService.deleteGym(gIds);
        if (result>0){
            return Result.success(0,"删除"+result+"条数据成功");
        }else{
            return Result.error(500,"删除数据失败");
        }
    }
}
