package com.onlinecloth.pojo;

import java.util.Base64;

public class Product
{
    private int productId,productPrice,productDiscount,productQuantity,categoryId,clothId;
    private String productTitle,productDescription,productFor;
    private byte[] productPhoto;
    
    public Product()
    {
    	
    }

	public Product(int productPrice, int productDiscount, int productQuantity, int categoryId, String productTitle,
			String productDescription, byte[] productPhoto) {
		super();
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productQuantity = productQuantity;
		this.categoryId = categoryId;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productPhoto = productPhoto;
	}
	
    

	public Product( int productPrice, int productDiscount, int productQuantity, String productTitle,
			String productDescription, byte[] productPhoto) {
		super();
	
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productQuantity = productQuantity;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productPhoto = productPhoto;
	}

	public Product(int productId, int productPrice, int productDiscount, int productQuantity, int categoryId,
			String productTitle, String productDescription, byte[] productPhoto) {
		super();
		this.productId = productId;
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productQuantity = productQuantity;
		this.categoryId = categoryId;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productPhoto = productPhoto;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(int productDiscount) {
		this.productDiscount = productDiscount;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public byte[] getProductPhoto() {
		return productPhoto;
	}
	
	public String getProductPicInBase() {
		return Base64.getEncoder().encodeToString(getProductPhoto());
	}

	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}

	public String getProductFor() {
		return productFor;
	}

	public void setProductFor(String productFor) {
		this.productFor = productFor;
	}

	public int getClothId() {
		return clothId;
	}

	public void setClothId(int clothId) {
		this.clothId = clothId;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productPrice=" + productPrice + ", productDiscount="
				+ productDiscount + ", productQuantity=" + productQuantity + ", categoryId=" + categoryId + ", clothId="
				+ clothId + ", productTitle=" + productTitle + ", productDescription=" + productDescription
				+ ", productPhoto=" + productPhoto + ", productFor=" + productFor + "]";
	}
	
	

    
	
    
}
