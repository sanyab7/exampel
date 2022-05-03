import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static java.lang.System.out;

public class AppTest {
    static Logger logger = Logger.getLogger(AppTest.class);

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
                .basePath("/3503")
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
//        System.out.println("user name = " + obektid.getName());
        logger.info("user name is =" + obektid.getName());
//       logger.warn(obektid.getName());
        // DOMConfigurator.configure("log4j.xml");
//        logger.debug("Sample debug message 1111111");
//        logger.info("Sample info message");
//        logger.warn("Sample warn message");
//        logger.error("Sample error message");
//        logger.fatal("Sample fatal message");
    }

    @Test
    public void test4() {
        String name = "Tenali Ramakrishna";
        String email = "tenali1.ramakri2shna@15ce.com";
        int aRamdomNumber = (int) (Math.random() * 100);
        Random r = new Random();
        String str = "abcdefghijklmnopqrstuvwxyz";
        char chRamdomText = str.charAt(r.nextInt(str.length()));
        email = chRamdomText + "t" + aRamdomNumber + email;
        logger.info(email);
        String gender = "male";
        String status = "active";
        String token = "1015e6bd143c52dabb0da55a2e9511503bb98ac4887427a3c3dede2d303e0d47";
        Register user = new Register("Tenali Ramakrishna", email, "male", "active");
        SuccsesReg succsesReg = given()
                .headers(
                        "Authorization",
                        "Bearer " + token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(user)
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .then()
                .log().all()
                .extract().body().as(SuccsesReg.class);
        Assert.assertNotNull(succsesReg.getId());
        Assert.assertEquals(name, succsesReg.getName());
        Assert.assertEquals(email, succsesReg.getEmail());
        Assert.assertEquals(gender, succsesReg.getGender());
        Assert.assertEquals(status, succsesReg.getStatus());
        int mmm = succsesReg.getId();
          logger.info(mmm);
given()
        .headers(
                "Authorization",
                "Bearer " + token,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
        .when()
        .delete("https://gorest.co.in/public/v2/users/" + mmm)
        .then().log().all().statusCode(204);
    }
}
