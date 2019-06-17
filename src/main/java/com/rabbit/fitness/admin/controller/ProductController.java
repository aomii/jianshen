package com.rabbit.fitness.admin.controller;

import com.github.pagehelper.PageInfo;
import com.rabbit.fitness.admin.service.ProductService;
import com.rabbit.fitness.admin.vo.ProductVo;
import com.rabbit.fitness.gym.service.GymService;
import com.rabbit.fitness.pojo.Product;
import com.rabbit.fitness.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private GymService gymService;


    //==================以下:前端使用==================================
    /*前端渲染器材列表*/
    @RequestMapping("/listPrevious")
    public ModelAndView listPrevious(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "6",required = false)Integer pageSize, ProductVo vo){
        /*获取所有产品*/
        List<Product> products=productService.selProductByPage(currentPage,pageSize,vo);
        /*分页*/
        PageInfo<Product> info=new PageInfo<>(products);
        ModelAndView modelAndView=new ModelAndView("fitness_product");
        modelAndView.addObject("info",info);
        //设置分页page.html中请求路径
        modelAndView.addObject("property","/product/listPrevious");
        return modelAndView;
    }


    //详情
    @RequestMapping("/info")
    public ModelAndView details(Integer productId,Integer gid){
        //1.获取带折扣的产品详情
        Product product=productService.findDetailsByProductId(productId);
        //2.设置折扣的折率为折扣后的价格
            //2.1折扣discountDetails   string转为Double;
            Double d=Double.parseDouble(product.getDiscount().getDiscountDetails());
            //2.2计算优惠后的价格
            Double salePrice = product.getProductPrice()*d;
            //2.3.salePrice保留两位小数转为string,
            String s=String.format("%.2f",salePrice);
            product.getDiscount().setDiscountDetails(s);
        //3.将数据放入modelAndview
        ModelAndView modelAndView=new ModelAndView("product_details");
        modelAndView.addObject("details",product);

        //4.学生选择场馆信息
        List gyms=gymService.findAll();
        modelAndView.addObject("gyms",gyms);
        modelAndView.addObject("gid",gid);
        return modelAndView;
    }
    //==================以上========================================

    /*分页显示所有用户*/
    @RequestMapping("/list")
    @RequiresRoles(value = "superadmin")
    public Map<String,Object> list(@RequestParam(value = "page",defaultValue = "1",required = false)Integer currentPage, @RequestParam(
            value = "rows",defaultValue = "5",required = false)Integer pageSize, ProductVo vo){
        /*获取所有产品*/
        List<Product> products=productService.selProductByPage(currentPage,pageSize,vo);
        /*分页*/
        PageInfo<Product> Info=new PageInfo<>(products);
        /*返回前端数据*/
        Map map=new HashMap();
        map.put("total",Info.getTotal());
        map.put("rows",Info.getList());
        return map;
    }

    /*增加用户*/
    @RequestMapping("/add")
    @RequiresRoles(value = "superadmin")
    public Result addProduct(Product product){
        /*检查产品是否存在*/
        Product product1=productService.findProduct(product.getProductName());
        if(product1!=null){
            return Result.error(500,"该产品已经存在");
        }
        /*添加产品*/
        Integer result=productService.addProduct(product);
        /*判断是否添加成功*/
        if(result>0){
            return  Result.success(0,"添加成功");
        }else{
            return  Result.error(500,"产品添加失败");
        }

    }

    /*修改产品*/
    @RequestMapping("/update")
    @RequiresRoles(value = "superadmin")
    public Result updateProduct(Product product){
        /*检查产品名是否重复，可以为自己本身*/
        Product product1=productService.findProduct(product.getProductName());
        if(product1!=null){
            if(!product1.getProductId().equals(product.getProductId())){
                return  Result.error(500,"该产品已存在，不允许修改");
            }
        }
        /*修改产品*/
        int result=productService.updateProduct(product);
        /*判断是否修改成功*/
        if(result>0){
            return Result.success();
        }else {
            return Result.error(500,"修改失败");
        }

    }

    /*删除产品*/
    @RequestMapping("/delete")
    @RequiresRoles(value = "superadmin")
    public Result deleteProduct(String productIds){
        /*分割数组，获取所有要删除的产品id */
        String[] arr=productIds.split(",");
        /*转换为整数数组*/
        Integer[] productsId=new Integer[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            productsId[i]=Integer.parseInt(arr[i]);
        }
        /*删除产品*/
        int result=productService.deleteProduct(productsId);
        /*判断是否成功*/
        if(result>0){
            return Result.success(0,"删除"+result+"条数据成功");
        }else {
            return Result.error(500,"删除失败");
        }
    }
}
