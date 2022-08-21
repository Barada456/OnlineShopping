package com.shop.dao;

import java.util.List;
import com.shop.pojo.Type;

public interface TypeDao
{
	boolean addType(Type type);
    List<Type> getAllType();
    int getTypeIdByProductId(int productId);
    int getTotalType();
    String getTypeNameById(int typeId);
    boolean deleteTypeById(int typeId);
}
