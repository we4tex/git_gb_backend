package lesson4;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("Account Tests:")
public class AccountTests extends Specification {

    private static final String username = "sunask";

    @DisplayName("Account info with assertions in given")
    @Test
    @Order(1)
    void getAccountInfoWithAssertionsInGivenTest() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .body("data.url", equalTo(username))
                .spec(responseSpecification)
                .when()
                .get("account/{username}", username)
                .prettyPeek();

    }

    @DisplayName("Account info with logging")
    @Test
    @Order(2)
    void getAccountInfoWithLoggingTest() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .when()
                .get("account/{username}", username)
                .prettyPeek();
    }

    @DisplayName("Account info with assertions after")
    @Test
    @Order(3)
    void getAccountInfoWithAssertionsAfterTest() {
        Response response = given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .when()
                .get("account/{username}", username)
                .prettyPeek();
        assertThat(response.jsonPath().get("data.url"), equalTo(username));
    }

}
