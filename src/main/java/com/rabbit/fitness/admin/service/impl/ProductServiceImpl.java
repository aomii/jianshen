package com.rabbit.fitness.admin.service.impl;

import com.rabbit.fitness.admin.service.ProductService;
import com.rabbit.fitness.admin.vo.ProductVo;
import com.rabbit.fitness.dao.ProductMapper;
import com.rabbit.fitness.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    /*分页展示*/
    @Override
    public List<Product> selProductByPage(Integer currentPage, Integer pageSize, ProductVo vo) {
        return productMapper.selProductByPage(currentPage,pageSize,vo);
    }

    /*检查重复*/
    @Override
    public Product findProduct(String productName) {
        return productMapper.selProductByName(productName);
    }

    /*添加*/
    @Override
    public Integer addProduct(Product product) {
        return productMapper.insertSelective(product);
    }

    /*修改*/
    @Override
    public int updateProduct(Product product) {
        return productMapper.updateByPrimaryKeySelective(product);
    }

    /*批量删除*/
    @Override
    public int deleteProduct(Integer[] productsId) {
        return productMapper.deleteProducts(productsId);
    }


    //通过pid查找产品
    @Override
    public Product findProductByPid(int productId) {
        return productMapper.selectByPrimaryKey(productId);
    }



    /*===========前端使用================*/
    /*产品带折扣详情,*/
    @Override
    public Product findDetailsByProductId(Integer productId) {
        return productMapper.findDetailsByProductId(productId);
    }
}
