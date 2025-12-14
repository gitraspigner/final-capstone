package org.yearup.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;
import java.util.List;
// TODO: only logged in users should have access to these actions
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    // a shopping cart requires:
    private final ShoppingCartDao shoppingCartDao;
    private final UserDao userDao;
    private final ProductDao productDao;
    //Principal/principal represents the currently logged in user
    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    @GetMapping("")
    public ShoppingCart getCart(Principal principal) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            return shoppingCartDao.getByUserId(user.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PostMapping("/products/{productId}")
    public void addProduct(Principal principal, @PathVariable int productId) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            Product product = productDao.getById(productId);

            ShoppingCartItem item = new ShoppingCartItem();
            item.setProduct(product);
            item.setQuantity(1);

            shoppingCartDao.addItem(user.getId(), item);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PutMapping("/products/{productId}")
    public void updateProduct(Principal principal, @PathVariable int productId, @RequestBody int quantity) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            shoppingCartDao.updateItem(user.getId(), productId, quantity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @DeleteMapping("")
    public void clearCart(Principal principal) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            shoppingCartDao.clearCart(user.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
