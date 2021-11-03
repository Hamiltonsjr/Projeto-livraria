package com.livraria.usermicroservicepag;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestApplication {

   /* int id;

    @Test
    public void UsersTest(){
        given().contentType("application/json")
                .when().get("http://localhost:8080/user/")
                .then().statusCode(200).log().all();
    }
    @Test
    public void UserAddTest(){
        id = given().contentType("Application/json")
                        .body("{\"name\":\"Karina\"," +
                                " \"nickname\" :\"Karina\", " +
                                "\"password\" :\"123\", " +
                                "\"userProfile\" :\"3\"}")
                        .when()
                        .post("http://localhost:8080/user/")
                        .then().statusCode(201).log().all().extract().path("id");
    }

    @Test
    public void updateUserTest(){
        UserAddTest();
        given()
                .contentType("application/json")
                .body("{\"name\":\"Karina Maria\"," +
                        " \"nickname\" :\"Karininha\", " +
                        "\"password\" :\"1236\", " +
                        "\"userProfile\" :\"2\"}")
                .pathParam("id",id)
                .when().put("http://localhost:8080/user/{id}")
                .then().statusCode(204).log();
    }
    @Test
    public void deleteUserTest(){
        UserAddTest();
        given()
                .contentType("application/json")
                .pathParam("id",id)
                .when().delete("http://localhost:8080/user/{id}")
                .then().statusCode(200).log().all();
    }*/

}
