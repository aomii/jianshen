package com.rabbit.fitness.student.controller;

import com.rabbit.fitness.pojo.Student;
import com.rabbit.fitness.student.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/upload")
@RequiresRoles(value = "student")
public class FileUploadController {
    @Resource(name = "studentService")
    private StudentService studentService;
    @RequestMapping("/img")
    public String uploadImg(MultipartFile portrait, HttpServletRequest request, HttpSession session) throws Exception {
        System.out.println("文件对象："+portrait);
        //获取文件的名字
        String fileNameOriginal=portrait.getOriginalFilename();
        StringBuilder fileName = new StringBuilder(fileNameOriginal);
        fileName.insert(fileName.indexOf("."), new Date().getTime());
        System.out.println("文件名:"+fileName);

        //获取当前项目路径
        String allInOneProjectPath = System.getProperty("user.dir");
        System.out.println("当前项目路径" + allInOneProjectPath);
        File curAllInOneProjectFile = new File(allInOneProjectPath);
        String upperDir=curAllInOneProjectFile.getParent();
        System.out.println("上一级文件目录:"+upperDir);

        //判断操作系统
        String os = System.getProperty("os.name");
        String path;
        if (os.contains("Win")){
            //获取本地保存文件的路径
            path = "D:"+File.separator+"upload";
            System.out.println("保存文件路径:"+path);
        }else{
            path = "/home"+File.separator+"upload";
        }


        //创建路径
        File file = new File(path);
        //如果路径不存在
        if (!file.exists()) {
            file.mkdirs();
        }
        //将文件放到文件夹中
        path = path+File.separator+fileName;
        file = new File(path);

        //自动创建文件，写入数据
        try {
            portrait.transferTo(file);
        } catch (IOException e) {
            System.out.println("保存文件出错");
            e.printStackTrace();
        }


        Student student=(Student)session.getAttribute("selStu");
        Integer uid= student.getStuid();
        String picDir = File.separator+"upload"+File.separator+fileName;

        studentService.modifyStudentPicById(picDir,uid);

        return  "上传成功";
    }
}
