package lesson5;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import retrofit2.Retrofit;
import lesson5.dto.Product;
import lesson5.enums.CategoryType;
import lesson5.service.CategoryService;
import lesson5.service.ProductService;
import lesson5.utils.RetrofitUtils;

public class BaseTest {
    static Retrofit client;
    Faker faker = new Faker();
    static ProductService productService;
    static CategoryService categoryService;
    Product product;
    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().dish())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

}
