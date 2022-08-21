package com.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart
{
     private int cartId,userId,productId,quantity,price;


     public Cart(int cartId, int userId, int productId, int quantity, int price) {
          this.cartId = cartId;
          this.userId = userId;
          this.productId = productId;
          this.quantity = quantity;
          this.price = price;
     }

     public Cart(int userId, int productId, int quantity, int totalPrice) {
          this.userId = userId;
          this.productId = productId;
          this.quantity = quantity;
          this.price = totalPrice;
     }
}
