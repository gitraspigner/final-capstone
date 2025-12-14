package org.yearup.data;

import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartDao {
    ShoppingCart getByUserId(int userId);
    void addItem(int userId, ShoppingCartItem item);
    void updateItem(int userId, int productId, int quantity);
    void clearCart(int userId);
}
