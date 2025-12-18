package org.yearup.controllers.test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.yearup.controllers.CategoriesController;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.security.JwtAccessDeniedHandler;
import org.yearup.security.JwtAuthenticationEntryPoint;
import org.yearup.security.UserModelDetailsService;
import org.yearup.security.jwt.TokenProvider;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = CategoriesController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CategoriesControllerTest  {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryDao categoryDao;
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
    void getAll_returnsCategories() throws Exception {
        List<Category> categories = Arrays.asList(
                new Category(1, "Electronics", "Devices and gadgets"),
                new Category(2, "Books", "Printed and digital books")
        );
        when(categoryDao.getAllCategories()).thenReturn(categories);
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId").value(1))
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[0].description").value("Devices and gadgets"))
                .andExpect(jsonPath("$[1].categoryId").value(2))
                .andExpect(jsonPath("$[1].name").value("Books"))
                .andExpect(jsonPath("$[1].description").value("Printed and digital books"));
    }
    @Test
    @WithMockUser(roles = "USER")
    void getById_returnsCategory() throws Exception {
        Category category = new Category(1, "Electronics", "Devices and gadgets");
        when(categoryDao.getById(1)).thenReturn(category);

        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.name").value("Electronics"))
                .andExpect(jsonPath("$.description").value("Devices and gadgets"));
    }
    @Test
    @WithMockUser(roles = "USER")
    void getById_notFound() throws Exception {
        when(categoryDao.getById(99)).thenReturn(null);

        mockMvc.perform(get("/categories/99"))
                .andExpect(status().isNotFound());
    }
    @Test
    @WithMockUser(roles = "USER")
    void getProductsById_returnsProducts() throws Exception {
        List<Product> products = Arrays.asList(
                new Product(1, "Phone", new BigDecimal("499.99"), 1,
                        "Smartphone", "Black", 50, false, "phone.jpg"),
                new Product(2, "Laptop", new BigDecimal("999.99"), 1,
                        "Gaming laptop", "Silver", 20, true, "laptop.jpg")
        );
        when(productDao.listByCategoryId(1)).thenReturn(products);
        mockMvc.perform(get("/categories/1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].name").value("Phone"))
                .andExpect(jsonPath("$[0].price").value(499.99))
                .andExpect(jsonPath("$[0].description").value("Smartphone"))
                .andExpect(jsonPath("$[0].subCategory").value("Black"))
                .andExpect(jsonPath("$[0].stock").value(50))
                .andExpect(jsonPath("$[0].featured").value(false))
                .andExpect(jsonPath("$[0].imageUrl").value("phone.jpg"))
                .andExpect(jsonPath("$[1].productId").value(2))
                .andExpect(jsonPath("$[1].name").value("Laptop"))
                .andExpect(jsonPath("$[1].price").value(999.99))
                .andExpect(jsonPath("$[1].description").value("Gaming laptop"))
                .andExpect(jsonPath("$[1].subCategory").value("Silver"))
                .andExpect(jsonPath("$[1].stock").value(20))
                .andExpect(jsonPath("$[1].featured").value(true))
                .andExpect(jsonPath("$[1].imageUrl").value("laptop.jpg"));
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void addCategory_returnsCreatedCategory() throws Exception {
        Category category = new Category(3, "Clothing", "Apparel and accessories");
        when(categoryDao.create(any(Category.class)))
                .thenReturn(new Category(3, "Clothing", "Apparel and accessories"));
        String body = "{ \"categoryId\": 3, \"name\": \"Clothing\", \"description\": \"Apparel and accessories\" }";
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(3))
                .andExpect(jsonPath("$.name").value("Clothing"))
                .andExpect(jsonPath("$.description").value("Apparel and accessories"));
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void updateCategory_returnsOk() throws Exception {
        String body = "{ \"categoryId\": 1, \"name\": \"Updated Electronics\", \"description\": \"Updated description\" }";
        mockMvc.perform(put("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteCategory_returnsOk() throws Exception {
        mockMvc.perform(delete("/categories/1"))
                .andExpect(status().isOk());
    }
}
