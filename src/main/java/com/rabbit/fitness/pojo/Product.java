package com.rabbit.fitness.pojo;

public class Product {
    private Integer productId;

    private String productName;

    private Double productPrice;

    private Integer productSalecount;

    private String productDesc;

    private Discount discount;

    private int productPrescription;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductSalecount() {
        return productSalecount;
    }

    public void setProductSalecount(Integer productSalecount) {
        this.productSalecount = productSalecount;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public int getProductPrescription() {
        return productPrescription;
    }

    public void setProductPrescription(int productPrescription) {
        this.productPrescription = productPrescription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productSalecount=" + productSalecount +
                ", productDesc='" + productDesc + '\'' +
                ", discount=" + discount +
                ", productPrescription=" + productPrescription +
                '}';
    }
}