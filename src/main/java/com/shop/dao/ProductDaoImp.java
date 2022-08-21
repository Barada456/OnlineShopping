package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shop.dbUtility.DBUtility;
import com.shop.pojo.Product;

public class ProductDaoImp implements ProductDao
{

	@Override
	public boolean addProduct(Product product)
	{
		int row=0;
    	Connection con=DBUtility.getConnection();
    	String query="insert into Product(productTitle,productDescription,productPhoto,productPrice,productDiscount,productQuantity,typeId,brandId) values(?,?,?,?,?,?,?,?)";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setString(1,product.getProductTitle());
    	    stmt.setString(2,product.getProductDescription());
    	    stmt.setBytes(3,product.getProductPhoto());
    	    stmt.setInt(4,product.getProductPrice());
    	    stmt.setInt(5,product.getProductDiscount());
    	    stmt.setInt(6,product.getProductQuantity());
    	    //stmt.setString(7,product.getProductFor());
    	    stmt.setInt(7,product.getTypeId());
    	    stmt.setInt(8,product.getBrandId());
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
	public List<Product> getAllProduct()
	{
		Connection con=DBUtility.getConnection();
		String query="select * from product";
		ResultSet rs;
		Statement stmt;
		Product product;
		List<Product> li=new ArrayList<>();
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId") ,
    	    			rs.getString("productTitle"), rs.getString("productDescription") , rs.getBytes("productPhoto") ); 
    	        product.setTypeId(rs.getInt("typeId"));
//    	        product.setProductFor(rs.getString("productFor"));
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
	}

	@Override
	public List<Product> getProductByBrand(int brandId)
	{
		Connection con=DBUtility.getConnection();
		String query="select * from product where brandId="+brandId;
		ResultSet rs;
		Statement stmt;
		Product product;
		List<Product> li=new ArrayList<>();
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId") ,
    	    			rs.getString("productTitle"), rs.getString("productDescription") , rs.getBytes("productPhoto") ); 
    	        product.setTypeId(rs.getInt("typeId"));
    	       // product.setProductFor(rs.getString("productFor"));
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
	}
	
	@Override
	public List<Product> getProductByType(int typeId)
	{
		Connection con=DBUtility.getConnection();
		String query="select * from product where typeId="+typeId;
		ResultSet rs;
		Statement stmt;
		Product product;
		List<Product> li=new ArrayList<>();
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId") ,
    	    			rs.getString("productTitle"), rs.getString("productDescription") , rs.getBytes("productPhoto") ); 
    	        product.setTypeId(rs.getInt("typeId"));
    	        //product.setProductFor(rs.getString("productFor"));
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
	}

	@Override
	public List<Product> getSearchedProduct(String productName)
	{
		Connection con=DBUtility.getConnection();
		ResultSet rs;
		Statement stmt;
		Product product;
		List<Product> li=new ArrayList<>();
		String query="select * from product where productTitle LIKE '%"+productName+"%' ";
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId"),
    	                         rs.getString("productTitle"), rs.getString("productDescription") , rs.getBytes("productPhoto") ); 
    	        product.setTypeId(rs.getInt("typeId"));
    	      //  product.setProductFor(rs.getString("productFor"));
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
		
	}

	@Override
	public Product getProductById(int productId) 
	{
		Connection con=DBUtility.getConnection();
		ResultSet rs;
		Statement stmt;
		Product product = null;
		System.out.println("hello");
		
		String query="select * from product where productId="+productId;
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId"),
    	                         rs.getString("productTitle"), rs.getString("productDescription") , rs.getBytes("productPhoto") ); 
    	        product.setTypeId(rs.getInt("typeId"));
    	        //product.setProductFor(rs.getString("productFor"));
    	        System.out.println(rs.getString("productTitle"));
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return product;
	}

	@Override
	public int getTotalProduct()
	{
		   int totalProduct=0;
		   ResultSet rs;
	 	   
	 	   Connection con=DBUtility.getConnection();
	        String query="select count(productId) from product";
	        
	 	   try 
	 	   {
	 		Statement stmt=con.createStatement();
	 	    rs=stmt.executeQuery(query);
	 	    while(rs.next())
	 		{
	 	    	  totalProduct=rs.getInt("count(productId)");
	 		}
	 	   }
	 	   catch (SQLException e) 
	 	   {
	 		e.printStackTrace();
	 	   }
	 	  
		   return totalProduct;  
	}

	@Override
	public boolean deleteProductById(int productId)
	{
		int row=0;
    	Connection con=DBUtility.getConnection();
    	String query="delete from product where productId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setInt(1,productId);
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
	public boolean updateProduct(Product product)
	{
		int row=0;
    	Connection con=DBUtility.getConnection();
    	String query="update product set productTitle=?,productDescription=?,productPhoto=?,productPrice=?, productDiscount=?,productQuantity=? where productId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setString(1,product.getProductTitle());
    	    stmt.setString(2,product.getProductDescription());
    	    stmt.setBytes(3,product.getProductPhoto());
    	    stmt.setInt(4,product.getProductPrice());
    	    stmt.setInt(5,product.getProductDiscount());
    	    stmt.setInt(6,product.getProductQuantity());
    	    stmt.setInt(7,product.getProductId());
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
	public int getQuantityByProductId(int productId)
	{
		   int quantity=0;
		   ResultSet rs;
	 	   
	 	   Connection con=DBUtility.getConnection();
	        String query="select quantity from product where productId="+productId;
	        
	 	   try 
	 	   {
	 		Statement stmt=con.createStatement();
	 	    rs=stmt.executeQuery(query);
	 	    while(rs.next())
	 		{
	 	    	  quantity=rs.getInt("quantity");
	 		}
	 	   }
	 	   catch (SQLException e) 
	 	   {
	 		e.printStackTrace();
	 	   }
	 	  
		   return quantity;  
	}

	@Override
	public void updateQuantityAfterOrder(int productId,int productQuantity)
	{
		
    	Connection con=DBUtility.getConnection();
    	String query="update product set productQuantity=? where productId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setInt(1,productQuantity);
    	    stmt.setInt(2, productId);
    	    stmt.executeUpdate();
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
	}

	@Override
	public boolean deleteProductByBrandId(int brandId)
	{
		int row=0;
    	Connection con=DBUtility.getConnection();
    	String query="delete from product where brandId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setInt(1,brandId);
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
	public boolean deleteProductByTypeId(int typeId)
	{
		int row=0;
    	Connection con=DBUtility.getConnection();
    	String query="delete from product where typeId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setInt(1,typeId);
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
	public int getTotalProductOfBrandById(int brandId)  // Brand=brand
	{	
		   int totalProduct=0;
		   ResultSet rs;
	 	   
	 	   Connection con=DBUtility.getConnection();
	        String query="select count(productId) from product where brandId="+brandId;
	        
	 	   try 
	 	   {
	 		Statement stmt=con.createStatement();
	 	    rs=stmt.executeQuery(query);
	 	    while(rs.next())
	 		{
	 	    	  totalProduct=rs.getInt("count(productId)");
	 		}
	 	   }
	 	   catch (SQLException e) 
	 	   {
	 		e.printStackTrace();
	 	   }
	 	  
		   return totalProduct;  	
	}
	
	@Override
	public int getTotalProductOfTypeById(int typeId)
	{	
		   int totalProduct=0;
		   ResultSet rs;
	 	   
	 	   Connection con=DBUtility.getConnection();
	        String query="select count(productId) from product where typeId="+typeId;
	        
	 	   try 
	 	   {
	 		Statement stmt=con.createStatement();
	 	    rs=stmt.executeQuery(query);
	 	    while(rs.next())
	 		{
	 	    	  totalProduct=rs.getInt("count(productId)");
	 		}
	 	   }
	 	   catch (SQLException e) 
	 	   {
	 		e.printStackTrace();
	 	   }
	 	  
		   return totalProduct;  	
	}

	@Override
	public List<Product> getSearchedProduct( int brandId, int typeId, int productRange)
	{
		Connection con=DBUtility.getConnection();
		ResultSet rs;
		Statement stmt;
		Product product;
		List<Product> li=new ArrayList<>();
		String query=null;
		
		if(productRange==500)
		{
		    query="select * from product where brandId="+brandId+" and typeId="+typeId+" and productPrice<="+500;
		}
		
		else if(productRange==1000)
		{
		   query="select * from product where brandId="+brandId+" and typeId="+typeId+" and productPrice<="+1000;
		}
		
		else if(productRange==2000)
		{
		  query="select * from product where  brandId="+brandId+" and typeId="+typeId+" and productPrice<="+2000;
		}
		
		else if(productRange==2001)
		{
		  query="select * from product where  brandId="+brandId+" and typeId="+typeId+" and productPrice>="+2000;
		}
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId"),
    	                         rs.getString("productTitle"), rs.getString("productDescription") , rs.getBytes("productPhoto") ); 
    	        product.setTypeId(rs.getInt("typeId"));
    	      //  product.setProductFor(rs.getString("productFor"));
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
	}

	@Override
	public List<Product> getProductByBrandAndFor( int typeId)
	{
		Connection con=DBUtility.getConnection();
		ResultSet rs;
		Statement stmt;
		Product product = null;
		List<Product> li=new ArrayList<>();
		
		String query="select * from product where  typeId="+typeId;
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId"),
    	                         rs.getString("productTitle"), rs.getString("productDescription") , rs.getBytes("productPhoto") ); 
    	        product.setTypeId(rs.getInt("typeId"));
    	      //  product.setProductFor(rs.getString("productFor"));
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
	}





}
