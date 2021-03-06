package com.rabbit.fitness.admin.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.fitness.admin.service.EquipService;
import com.rabbit.fitness.pojo.Equipment;
import com.rabbit.fitness.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/equip")
@Controller
public class EquipController {
    @Autowired
    private EquipService equipService;

    //==================以下:前端使用==================================
    /*前端渲染器材列表*/
    @RequestMapping("/listPrevious")
    public ModelAndView listPrevious(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "6",required = false)Integer pageSize,String equipName){
        List<Equipment> equipments=equipService.getAll(currentPage,pageSize,equipName);
        /*分页*/
        PageInfo<Equipment> info=new PageInfo<>(equipments);
        ModelAndView modelAndView=new ModelAndView("fitness_equip");
        modelAndView.addObject("info",info);

        //设置分页page.html中请求路径
        modelAndView.addObject("property","/equip/listPrevious");
        return modelAndView;
    }
    //详情
    @RequestMapping("/info")
    public ModelAndView info(Integer equipId){
        Equipment equip=equipService.findEquipByEquipId(equipId);
        Integer index = equipService.updateEquipCount(equip);
        ModelAndView modelAndView=new ModelAndView("equip_details");
        modelAndView.addObject("details",equip);
        return  modelAndView;
    }

    //==================以上========================================

    /*分页显示*/
    @RequestMapping("/list")
    @ResponseBody
    @RequiresRoles(value = "superadmin")
    public Map<String,Object> list(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "5",required = false)Integer pageSize,String equipName){
        List<Equipment> equipments=equipService.getAll(currentPage,pageSize,equipName);
        /*分页*/
        PageInfo<Equipment> Info=new PageInfo<>(equipments);
        /*返回前端数据*/
        Map map=new HashMap();
        map.put("total",Info.getTotal());
        map.put("rows",Info.getList());
        return map;
    }

    @Value("${image-path}")
    public String path;


    /*增加*/
    @RequestMapping("/add")
    @ResponseBody
    @RequiresRoles(value = "superadmin")
    public Result addEquipment(MultipartFile equipPhoto, Equipment equipment) throws IOException {
        /*检查是否存在*/
        Equipment equipment1=equipService.findEquip(equipment.getEquipName());
        if(equipment1!=null){
            return Result.error(500,"该器材已经存在");
        }
        String fileName = equipPhoto.getOriginalFilename();
        if(fileName!=null&&!"".equals(fileName)){
            System.out.println(fileName);

            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            fileName = UUID.randomUUID().toString() + fileName;
            String filePath=path+File.separator+fileName;
            file = new File(filePath);
            equipPhoto.transferTo(file);   //自动创建文件、写入数据
            equipment.setEquipImg("/upload/"+fileName);
        }
        /*添加*/
        Integer result=equipService.addEquip(equipment);
        /*判断是否添加成功*/
        if(result>0){
            return  Result.success(0,"添加成功");
        }else{
            return  Result.error(500,"添加失败");
        }

    }

    /*修改*/
    @RequestMapping("/update")
    @ResponseBody
    @RequiresRoles(value = "superadmin")
    public Result updateEquipment(MultipartFile equipPhoto,Equipment equipment) throws IOException {
        /*检查是否重复，可以为自己本身标题*/
        Equipment equipment1=equipService.findEquip(equipment.getEquipName());
        if(equipment1!=null){
            if(!equipment1.getEquipId().equals(equipment.getEquipId())){
                return  Result.error(500,"该器材已存在，不允许修改");
            }
        }
        String fileName = equipPhoto.getOriginalFilename();
        if(fileName!=null&&!"".equals(fileName)){
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            fileName = UUID.randomUUID().toString() + fileName;
            String filePath=path+File.separator+fileName;
            file = new File(filePath);
            equipPhoto.transferTo(file);   //自动创建文件、写入数据
            equipment.setEquipImg("/upload/"+fileName);
        }

        /*修改*/
        int result=equipService.updateEquip(equipment);
        /*判断是否修改成功*/
        if(result>0){
            return Result.success();
        }else {
            return Result.error(500,"修改失败");
        }
    }

    /*删除*/
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresRoles(value = "superadmin")
    public Result deleteEquipment(String equipIds){
        /*分割数组，获取所有要删除的id */
        String[] arr=equipIds.split(",");
        /*转换为整数数组*/
        Integer[] equipsId=new Integer[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            equipsId[i]=Integer.parseInt(arr[i]);
        }
        /*删除*/
        int result=equipService.deleteEquip(equipsId);
        /*判断是否成功*/
        if(result>0){
            return Result.success(0,"删除"+result+"条数据成功");
        }else {
            return Result.error(500,"删除失败");
        }
    }
}
