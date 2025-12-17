package org.yearup.data;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
/**
 * Data access interface for shopping cart operations.
 * Defines the contract implemented by MySqlShoppingCartDao.
 *
 * @author Ravi Spigner
 */
public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    void addItem(int userId, ShoppingCartItem item);
    void updateItem(int userId, int productId, int quantity);
    void clearCart(int userId);
}