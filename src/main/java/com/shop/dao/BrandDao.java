package com.shop.dao;

import java.util.List;
import com.shop.pojo.*;

public interface BrandDao
{
	boolean addBrand(Brand brand);
    List<Brand> getAllBrand(); 
    int getBrandIdByProductId(int productId);
    int getTotalBrand();
    String getBrandNameById(int brandId);
    boolean deleteBrandById(int brandId);
}
