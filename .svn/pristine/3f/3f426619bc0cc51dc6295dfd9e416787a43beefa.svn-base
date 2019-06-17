package com.rabbit.fitness.admin.service;

import com.rabbit.fitness.admin.vo.ProductVo;
import com.rabbit.fitness.pojo.Product;

import java.util.List;

public interface ProductService {
    /*分页展示*/
    List<Product> selProductByPage(Integer currentPage, Integer pageSize, ProductVo vo);

    /*检查重复*/
    Product findProduct(String productName);

    /*添加*/
    Integer addProduct(Product product);

    /*修改*/
    int updateProduct(Product product);

    /*批量删除*/
    int deleteProduct(Integer[] productsId);

    Product findProductByPid(int productId);


    /*==================前端使用====================*/
    /*产品带折扣详情,*/
    Product findDetailsByProductId(Integer productId);
}
