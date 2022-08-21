package com.shop.dao;

import java.util.List;

import com.shop.pojo.Product;

public interface ProductDao
{
   boolean addProduct(Product product);
   boolean updateProduct(Product product);
   List<Product> getAllProduct();
   List<Product> getProductByBrand(int brandId);
   List<Product> getProductByType(int typeId);
   List<Product> getSearchedProduct(String productName);
   List<Product> getSearchedProduct(int brandId,int typeId,int productRange);
   Product getProductById(int productId);
   int getTotalProduct();
   boolean deleteProductById(int productId);
   boolean deleteProductByBrandId(int brandId);
   boolean deleteProductByTypeId(int typeId);
   int getQuantityByProductId(int productId);
   void updateQuantityAfterOrder(int productId,int productQuantity);
   int getTotalProductOfBrandById(int brandId);
   int getTotalProductOfTypeById(int typeId);
   List<Product> getProductByBrandAndFor(int typeId);
}




