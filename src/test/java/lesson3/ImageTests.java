package lesson3;

import org.junit.jupiter.api.*;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@DisplayName("Image tests:")
public class ImageTests extends BaseTest {

    static String uploadedImageId = null; // image 1
    static String uploadedImageIdForUpdate = null; // image 2
    String fileName = "My cat";
    String fileName2 = "My dog";
    String fileTitle = "My new image cat";
    String fileTitle2 = "My new image dog";
    String fileDescription = "My description image";
    String fileDescription2 = "My description image 2";
    String fileType = "jpeg";
    String titleForUpdate = "Update Title image";
    String descriptionForUpdate = "Update description image";

    @DisplayName("200: Upload Image 1")
    @Test
    void uploadFileImage() {
        String PATH_TO_IMAGE = "src/test/resources/lesson3/hqdefault.jpg";
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(PATH_TO_IMAGE))
                .multiPart("name", fileName)
                .multiPart("title", fileTitle)
                .multiPart("description", fileDescription)
                .log()
                .method()
                .log()
                .uri()
                .expect()
                .contentType("application/json")
                .statusCode(200)
                .body("data.type", equalTo("image/" + fileType))
                .body("data.id", is(notNullValue()))
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");

        System.out.println(uploadedImageId);
    }

    @DisplayName("200: Upload Image 1 Info")
    @Test
    void uImageInfo() {
        given()
                .headers("Authorization", token)
                .log()
                .all()
                .expect()
                .statusCode(200)
                .body("data.title", equalTo(fileTitle))
                .body("data.description", equalTo(fileDescription))
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .when()
                .get("https://api.imgur.com/3/image/{uploadedImageId}", uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @DisplayName("200: Delete Image 1")
    @Test
    void deleteImageInfo() {
        given()
                .headers("Authorization", token)
                .log()
                .all()
                .expect()
                .body("data", equalTo(true))
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .when()
                .delete("https://api.imgur.com/3/image/{uploadedImageId}", uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @DisplayName("200: Upload Image 2")
    @Test
    void uploadFileImage2() {
        String PATH_TO_IMAGE_2 = "src/test/resources/lesson3/hqdefault2.jpg";
        uploadedImageIdForUpdate = given()
                .headers("Authorization", token)
                .multiPart("image", new File(PATH_TO_IMAGE_2))
                .multiPart("name", fileName2)
                .multiPart("title", fileTitle2)
                .multiPart("description", fileDescription2)
                .log()
                .method()
                .log()
                .uri()
                .expect()
                .contentType("application/json")
                .body("data.type", equalTo("image/" + fileType))
                .body("data.id", is(notNullValue()))
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");

        System.out.println(uploadedImageIdForUpdate);
    }

    @DisplayName("200: Upload Image 2 Info")
    @Test
    void uImageInfo2() {
        given()
                .headers("Authorization", token)
                .log()
                .all()
                .expect()
                .body("data.name", equalTo(fileName2))
                .body("data.title", equalTo(fileTitle2))
                .body("data.description", equalTo(fileDescription2))
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .when()
                .get("https://api.imgur.com/3/image/{uploadedImageIdForUpdate}", uploadedImageIdForUpdate)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @DisplayName("200: Update Image 2 Info")
    @Test
    void updateImage2Info() {
        given()
                .headers("Authorization", token)
                .multiPart("title", titleForUpdate)
                .multiPart("description", descriptionForUpdate)
                .log()
                .all()
                .expect()
                .contentType("application/json")
                .statusCode(200)
                .body("data", equalTo(true))
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .when()
                .post("https://api.imgur.com/3/image/{uploadedImageIdForUpdate}", uploadedImageIdForUpdate)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @DisplayName("400: Upload File")
    @Test
    void uploadFileTxt() {
        String PATH_TO_FILE = "src/test/resources/lesson3/test.txt";
        given()
                .headers("Authorization", token)
                .multiPart("image", new File(PATH_TO_FILE))
                .multiPart("name", "Text")
                .log()
                .method()
                .log()
                .uri()
                .expect()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("status", equalTo(400))
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
    }
}