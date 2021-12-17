package lesson4;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@DisplayName("Image tests:")
public class ImageTests extends Specification {

    private static String CURRENT_IMAGE_ID = null;

    @DisplayName("200: Upload Image 1")
    @Test
    @Order(4)
    void uploadFileImage() {
        CURRENT_IMAGE_ID = given()
                .spec(requestSpecification)
                .multiPart("image", getFileResource("img1.jpg"))
                .formParam("name", "Image 1")
                .formParam("title", "This image 1 title")
                .formParam("description", "This image 1 description")
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.type", is("image/jpeg"))
                .body("data.id", is(notNullValue()))
                .body("data.name", Matchers.is("Image 1"))
                .body("data.title", Matchers.is("This image 1 title"))
                .body("data.description", Matchers.is("This image 1 description"))
                .log()
                .all()
                .when()
                .post("upload")
                .body()
                .jsonPath()
                .getString("data.id");
    }

    @DisplayName("200: Upload Image 1 Info")
    @Test
    @Order(5)
    void uploadImageInfo() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.name", equalTo("Image 1"))
                .body("data.title", equalTo("This image 1 title"))
                .body("data.description", equalTo("This image 1 description"))
                .when()
                .get("image/{CURRENT_IMAGE_ID}", CURRENT_IMAGE_ID)
                .prettyPeek();
    }

    @DisplayName("200: Delete Image 1")
    @Test
    @Order(6)
    void deleteImageInfo() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data", equalTo(true))
                .when()
                .delete("image/{CURRENT_IMAGE_ID}", CURRENT_IMAGE_ID)
                .prettyPeek();
    }

    @DisplayName("200: Upload Image 2")
    @Test
    @Order(7)
    void uploadFileImage2() {
        CURRENT_IMAGE_ID = given()
                .spec(requestSpecification)
                .multiPart("image", getFileResource("img2.jpg"))
                .formParam("name", "Image 2")
                .formParam("title", "This image 2 title")
                .formParam("description", "This image 2 description")
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.type", is("image/jpeg"))
                .body("data.id", is(notNullValue()))
                .body("data.name", Matchers.is("Image 2"))
                .body("data.title", Matchers.is("This image 2 title"))
                .body("data.description", Matchers.is("This image 2 description"))
                .log()
                .all()
                .when()
                .post("upload")
                .body()
                .jsonPath()
                .getString("data.id");
    }

    @DisplayName("200: Upload Image 2 Info")
    @Test
    @Order(8)
    void uploadImageInfo2() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.name", equalTo("Image 2"))
                .body("data.title", equalTo("This image 2 title"))
                .body("data.description", equalTo("This image 2 description"))
                .when()
                .get("image/{CURRENT_IMAGE_ID}", CURRENT_IMAGE_ID)
                .prettyPeek();
    }

    @DisplayName("200: Update Image 2 Info")
    @Test
    @Order(9)
    void updateImage2Info() {
        given()
                .spec(requestSpecification)
                .multiPart("title", "titleForUpdate")
                .multiPart("description", "descriptionForUpdate")
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data", equalTo(true))
                .when()
                .post("image/{CURRENT_IMAGE_ID}", CURRENT_IMAGE_ID)
                .prettyPeek();
    }

    @DisplayName("400: Upload File")
    @Test
    @Order(10)
    void uploadFileTxt() {
        given()
                .spec(requestSpecification)
                .multiPart("image", getFileResource("test.txt"))
                .log()
                .all()
                .expect()
                .body("success", equalTo(false))
                .body("status", equalTo(400))
                .when()
                .post("image")
                .prettyPeek();
    }

}
