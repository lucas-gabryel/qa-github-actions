package client;

import io.restassured.response.Response;
import model.LoginRequest;
import utils.constants.LoginConstants;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    public Response loginUsuarios(LoginRequest login) {

        return
                given()
                        .spec(super.set())
                        .body(login)
                        .when()
                        .post(LoginConstants.ENDPOINT_LOGIN)
                ;
    }
}