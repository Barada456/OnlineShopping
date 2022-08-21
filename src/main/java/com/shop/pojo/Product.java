package com.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    private int productId,productPrice,productDiscount,productQuantity,brandId,typeId;
    private String productTitle,productDescription;
    private byte[] productPhoto;

	public Product(int productId, int productPrice, int productDiscount, int productQuantity, int brandId, String productTitle, String productDescription, byte[] productPhoto) {
		this.productId = productId;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productQuantity = productQuantity;
        this.productPhoto = productPhoto;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.brandId = brandId;
	}

	public Product(int productPrice, int productDiscount, int productQuantity, int brandId, String productTitle, String productDescription, byte[] data) {
		this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productQuantity = productQuantity;
        this.brandId = brandId;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.productPhoto = data;
	}

	public String getProductPicInBase() {
		return Base64.getEncoder().encodeToString(getProductPhoto());
	}

}
