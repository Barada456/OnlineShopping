package com.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart
{
     private int cartId,userId,productId,quantity,price;
     private String productSize;

     public Cart(int cartId, int userId, int productId, int quantity, int price) {
     }

     public Cart(int userId, int productId, int quantity, int totalPrice) {
     }
}
