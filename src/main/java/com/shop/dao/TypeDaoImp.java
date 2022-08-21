package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.shop.dbUtility.DBUtility;
import com.shop.pojo.Type;

public class TypeDaoImp implements TypeDao
{
	@Override
	public boolean addType(Type type)
	{
		int row=0;
		System.out.println("Addtype method called"+type.toString());
    	Connection con=DBUtility.getConnection();
    	String query="insert into Type(typeTitle,typeDescription) values(?,?)";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
			System.out.println("type"+type);
    	    stmt.setString(1, type.getTypeTitle());
    	    stmt.setString(2, type.getTypeDescription());
    	    row=stmt.executeUpdate();
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	if(row>0)
    	   return true;
    	else
    	   return false;
	}

	@Override
	public List<Type> getAllType()
	{
		Connection con=DBUtility.getConnection();
		String query="select * from Type";
		ResultSet rs;
		Statement stmt;
		Type type;
		List<Type> li=new ArrayList<>();
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	    	type =new Type(rs.getString("typeTitle"), rs.getString("typeDescription"));
    	    	type.setTypeId(rs.getInt("typeId"));
    	       li.add(type);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
		
	}

	@Override
	public int getTypeIdByProductId(int productId)
	{
		Connection con=DBUtility.getConnection();
		String query="select typeId from product where productId="+productId;
		ResultSet rs;
		Statement stmt;
		int typeId=0;
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	    	typeId=rs.getInt("typeId");
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return typeId;
	}

	@Override
	public int getTotalType() {
		int totalType=0;
		   ResultSet rs;
	 	   
	 	   Connection con=DBUtility.getConnection();
	        String query="select count(typeId) from Type";
	        
	 	   try 
	 	   {
	 		Statement stmt=con.createStatement();
	 	    rs=stmt.executeQuery(query);
	 	    while(rs.next())
	 		{
	 	    	  totalType=rs.getInt("count(typeId)");
	 		}
	 	   }
	 	   catch (SQLException e) 
	 	   {
	 		e.printStackTrace();
	 	   }
	 	  
		 
		return totalType;
	}

	@Override
	public String getTypeNameById(int typeId)
	{
		   String typeName=null;
		   ResultSet rs;
	 	   
	 	   Connection con=DBUtility.getConnection();
	        String query="select typeTitle from Type where typeId="+typeId;
	        
	 	   try 
	 	   {
	 		Statement stmt=con.createStatement();
	 	    rs=stmt.executeQuery(query);
	 	    while(rs.next())
	 		{
	 	    	typeName=rs.getString("typeTitle");
	 		}
	 	   }
	 	   catch (SQLException e) 
	 	   {
	 		e.printStackTrace();
	 	   }
	 	  
		 
		return typeName; 
	}

	@Override
	public boolean deleteTypeById(int typeId)
	{
		int row=0;
    	Connection con=DBUtility.getConnection();
    	String query="delete from type where typeId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setInt(1, typeId);
    	    row=stmt.executeUpdate();
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	if(row>0)
    	   return true;
    	else
    	   return false;
	}
}
