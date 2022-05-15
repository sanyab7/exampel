package restApi;

import constants.Сonstant;
import io.restassured.http.ContentType;
import model.Register;
import model.SuccsesReg;
import model.UsersGoRest;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestClient {
    static Logger logger = Logger.getLogger(RestClient.class);

    public static List<UsersGoRest> getMethodListObjects(String url) {
        List<UsersGoRest> usergo = given()
                .when()
                .contentType(ContentType.JSON)
                .get(url)
                .then().log().all()
                .extract().body().jsonPath().getList("", UsersGoRest.class);
        return usergo;
    }

    // getMethodListObjects print 1 name for id
    public static List<UsersGoRest> getMethodListObjectsName() {
        List<UsersGoRest> usergo = given()
                .when()
                .contentType(ContentType.JSON)
                .get(Сonstant.URL)
                .then().log().all()
                .extract().body().jsonPath().getList("", UsersGoRest.class);


        Assert.assertTrue(usergo.size() > 0);
        int mm = usergo.get((0)).getId();
        logger.info("user id =" + mm);

        RestClient myGet = new RestClient();
        UsersGoRest obektid = myGet.getMethodReturn(String.valueOf(mm));
        logger.info("user name is =" + obektid.getName());

        return usergo;

    }


    public void getMethod(String url, String basepath) {
        given()
                .log().all()
                .baseUri(url)
                .basePath(basepath)
                .when().get()
                .then()
                .log().all()
                .statusCode(200);
    }

    public void getMethodStatusCode(String url, String basepath, int code) {
        given()
                .log().all()
                .baseUri(url)
                .basePath(basepath)
                .when().get()
                .then()
                .log().all()
                .statusCode(code);
    }

    public static UsersGoRest getMethodReturn(String basepath) {
        UsersGoRest obektid = given()
                // .log().all()
                .baseUri(Сonstant.URL)
                .basePath(basepath)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                //  .log().all()
                .statusCode(200)
                .extract()
                .body().as(UsersGoRest.class);
        return obektid;
    }

    public static SuccsesReg postMethod(Register user) {
        SuccsesReg succsesReg = given()
                .headers(
                        "Authorization",
                        "Bearer " + Сonstant.TOKEN,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(user)
                .when()
                .post(Сonstant.URL)
                .then()
                .log().all()
                .extract().body().as(SuccsesReg.class);
        return succsesReg;
    }

    public void deleteMethod(String idObekt) {
        given()
                .headers(
                        "Authorization",
                        "Bearer " + Сonstant.TOKEN,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .delete(Сonstant.URL + "/" + idObekt)
                .then().log().all().statusCode(204);
    }

}
