package org.yearup.controllers.test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.yearup.controllers.ShoppingCartController;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;
import org.yearup.security.JwtAccessDeniedHandler;
import org.yearup.security.JwtAuthenticationEntryPoint;
import org.yearup.security.UserModelDetailsService;
import org.yearup.security.jwt.TokenProvider;
import java.util.HashMap;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = ShoppingCartController.class)
@AutoConfigureMockMvc(addFilters = false)
class ShoppingCartControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShoppingCartDao shoppingCartDao;
    @MockBean
    private UserDao userDao;
    @MockBean
    private ProductDao productDao;
    @MockBean
    private TokenProvider tokenProvider;
    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @MockBean
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @MockBean
    private UserModelDetailsService userModelDetailsService;
    @Test
    @WithMockUser(roles = "USER")
    void getCart_returnsCart() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        ShoppingCart cart = new ShoppingCart();
        cart.setItems(new HashMap<>());

        when(userDao.getByUserName("testuser")).thenReturn(user);
        when(shoppingCartDao.getByUserId(1)).thenReturn(cart);

        mockMvc.perform(get("/cart").principal(() -> "testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").exists());
    }
    @Test
    @WithMockUser(roles = "USER")
    void addProduct_returnsCart() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Product product = new Product();
        product.setProductId(15);

        ShoppingCart cart = new ShoppingCart();
        cart.setItems(new HashMap<>());

        when(userDao.getByUserName("testuser")).thenReturn(user);
        when(productDao.getById(15)).thenReturn(product);
        when(shoppingCartDao.getByUserId(1)).thenReturn(cart);

        mockMvc.perform(post("/cart/products/15").principal(() -> "testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").exists());
    }
    @Test
    @WithMockUser(roles = "USER")
    void updateProduct_returnsCart() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        ShoppingCart cart = new ShoppingCart();
        cart.setItems(new HashMap<>());

        when(userDao.getByUserName("testuser")).thenReturn(user);
        when(shoppingCartDao.getByUserId(1)).thenReturn(cart);

        String body = "{ \"quantity\": 3 }";

        mockMvc.perform(put("/cart/products/15")
                        .principal(() -> "testuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").exists());
    }
    @Test
    @WithMockUser(roles = "USER")
    void clearCart_returnsNoContent() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        when(userDao.getByUserName("testuser")).thenReturn(user);
        mockMvc.perform(delete("/cart").principal(() -> "testuser"))
                .andExpect(status().isNoContent());
    }
}