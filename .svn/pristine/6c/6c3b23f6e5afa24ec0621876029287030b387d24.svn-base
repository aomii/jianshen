package com.rabbit.fitness.dao;

import com.rabbit.fitness.admin.vo.ProductVo;
import com.rabbit.fitness.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    /*分页展示模糊查询*/
    List<Product> selProductByPage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize, @Param("vo")ProductVo vo);

    /*检查重复*/
    @Select("select *from product where product_name=#{productName}")
    Product selProductByName(String productName);

    /*批量删除*/
    int deleteProducts(Integer[] productsId);


    /*===========前端使用================*/
    /*产品带折扣详情,*/
    Product findDetailsByProductId(Integer productId);
}