import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestClientJava {
public static List<UsersGoRest> getMethodListObjects(){
    List<UsersGoRest> usergo = given()
            .when()
            .contentType(ContentType.JSON)
            .get(Сonstant.url)
            .then().log().all()
            .extract().body().jsonPath().getList("", UsersGoRest.class);
    return usergo;
}

    public void getMethod(String basepath) {
        given()
                .log().all()
                .baseUri(Сonstant.url)
                .basePath(basepath)
                .when().get()
                .then()
                .log().all()
                .statusCode(200);
           }

    public static UsersGoRest getMethodReturn(String basepath) {
        UsersGoRest obektid = given()
               // .log().all()
                .baseUri(Сonstant.url)
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
                        "Bearer " + Сonstant.token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(user)
                .when()
                .post(Сonstant.url)
                .then()
                .log().all()
                .extract().body().as(SuccsesReg.class);
        return succsesReg;
    }

    public void deleteMethod(String idObekt) {
        given()
                .headers(
                        "Authorization",
                        "Bearer " + Сonstant.token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .delete(Сonstant.url + "/" + idObekt)
                .then().log().all().statusCode(204);
    }
}
