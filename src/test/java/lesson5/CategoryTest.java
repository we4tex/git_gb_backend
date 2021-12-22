package lesson5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import lesson5.dto.Category;
import lesson5.enums.CategoryType;
import lesson5.utils.PrettyLogger;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Тест на категорию:")
public class CategoryTest extends BaseTest {

    @DisplayName("Поиск по id категории")
    @Test
    void getCategoryByIdTest() throws IOException {
        Integer id = CategoryType.FOOD.getId();
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();

        assert response.body() != null;
        PrettyLogger.DEFAULT.log(response.body().toString());
        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
    }
}
