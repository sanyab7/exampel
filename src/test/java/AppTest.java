import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
//import org.testng.asserts;
//import org.testing.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static java.lang.System.*;

public class AppTest {


    @Test
    public void test1() {
        List<usersgorest> usergo = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://gorest.co.in/public/v2/users")
                .then().log().all()
                .extract().body().jsonPath().getList("", usersgorest.class);
             }


    @Test
    public void test2() {
        given()
                .log().all()
                .baseUri("https://gorest.co.in/public/v2/users")
                .basePath("/2903")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void test3() {

        List<usersgorest> usergo = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://gorest.co.in/public/v2/users")
                .then()
                .log()
                .all()
                .extract()
                .body().jsonPath()
                .getList("", usersgorest.class);
        Assert.assertTrue(usergo.size() > 0);
        int mm = usergo.get((0)).getId();
        out.println("user id =" + mm);
        usersgorest obektid = given()
                //  .log().all()
                .baseUri("https://gorest.co.in/public/v2/users")
                .basePath(String.valueOf(mm))
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                // .log().all()
                .statusCode(200)
                .extract()
                .body().as(usersgorest.class);
        System.out.println("user name = " + obektid.getName());
    }
}
