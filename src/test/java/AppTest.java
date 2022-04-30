import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.System.out;

public class AppTest {
 //public static final Logger logger = LoggerFactory.getLogger(AppTest.class);

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
                .basePath("/2680")
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
                //.log()
                //.all()
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
       logger.info("user name is =" +obektid.getName());
       logger.warn(obektid.getName());
        DOMConfigurator.configure("log4j.xml");
        logger.debug("Sample debug message 1111111");
        logger.info("Sample info message");
        logger.warn("Sample warn message");
        logger.error("Sample error message");
        logger.fatal("Sample fatal message");
    }

    static Logger logger = Logger.getLogger(AppTest.class);


}
