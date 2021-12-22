package lesson5;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import lesson5.dto.Product;
import lesson5.utils.PrettyLogger;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Тесты на продукт:")
public class ProductTests extends BaseTest{
    int id;
    int idProduct = 1;
    Product createProduct;

    @BeforeEach
    @Test
    @DisplayName("Создание товара")
    void postProductTest() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        assert response.body() != null;
        id =  response.body().getId();
        createProduct = response.body();
        PrettyLogger.DEFAULT.log(response.body().toString());
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }

    @Order(2)
    @AfterEach
    @Test
    @DisplayName("Поиск товара по id")
    void getIdProduct() throws IOException {
        Response<Product> response = productService.getProduct(idProduct).execute();
        assert response.body() != null;
        assertThat(response.body().getId(), equalTo(idProduct));
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @AfterEach
    @Test
    @DisplayName("Обновление товара")
    void updateProduct() throws IOException{
        Response<Product> response = productService.updateProduct(createProduct).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
