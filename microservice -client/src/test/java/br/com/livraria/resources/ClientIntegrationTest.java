package br.com.livraria.resources;

import br.com.livraria.entities.Client;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;

@QuarkusTest
class ClientIntegrationTest {

    @Test
    void testCreateClient() {
        try {
            Client client = new Client();
            client.setId(3L);
            client.setName("José Carlos");
            client.setCity("Rio de Janeiro");
            client.setAddress("Rua Viela Test nº 69");
            client.setCPF("197.246.359.00");
            client.setZipCode("20230010");

            given().contentType("application/json").body(client).log().all()
                    .when().post("/clients/3").then().statusCode(200);
        }catch (Exception e){
            fail("Error occurred while testing ");
        }
    }
    @Test
    void getAll(){
       try{
           given()
                   .when()
                   .get("/clients/")
                   .then()
                   .statusCode(Response.Status.OK.getStatusCode());
       }catch (Exception e){
           fail("Error occurred while testing");
       }
    }
    @Test
    void testFindById(){
       try {
           given()
                   .when()
                   .get("/clients/1")
                   .then()
                   .statusCode(Response.Status.OK.getStatusCode());
       }catch (Exception e){
           fail("Error occurred while testing ");
       }
    }
    @Test
    void testFindByIdNotFound(){
       try{
           given()
                   .when()
                   .get("/clients/1000")
                   .then()
                   .statusCode(Response.Status.NOT_FOUND.getStatusCode());
       }catch (Exception e){
           fail("Error occurred while testing ");
       }
    }
    @Test
    void testDeleteClient() {
        try {
            given()
                    .contentType("application/json")
                    .pathParam("id", 2)
                    .when()
                    .delete("/clients/{id}")
                    .then()
                    .statusCode(204)
                    .log().all();
        } catch (Exception e) {
            fail("Error occurred while testing ");
        }
    }
    @Test
    void testDeleteClientIdNotFound(){
        try{
            given()
                    .contentType("application/json")
                    .pathParam("id",1000)
                    .when()
                    .delete("/clients/{id}")
                    .then()
                    .statusCode(404)
                    .log().all();
        }catch (Exception e){
            fail("Error occurred while testing ");
        }
    }
}