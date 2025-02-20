package Login;

import client.LoginClient;
import data.factory.LoginDataFactory;
import model.LoginRequest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.constants.LoginConstants;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    LoginClient loginClient = new LoginClient();
    LoginRequest loginValido = LoginDataFactory.loginValido();
    LoginRequest loginInvalido = LoginDataFactory.loginInvalido();
    LoginRequest emailInvalido = LoginDataFactory.emailInvalido();

    @Test(groups = {"Functional", "Contract"})
    public void testDeveFazerLoginComSucessoSchema() {
        loginClient.loginUsuarios(loginValido)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(LoginConstants.MESSAGE, equalTo(LoginConstants.MSG_LOGIN_COM_SUCESSO))
                .body(LoginConstants.AUTHORIZATION, notNullValue())
                .body(matchesJsonSchemaInClasspath("schemas/login_valido.json"));
    }

    @Test(groups = {"HealthCheck", "Functional"})
    public void testDeveFazerLoginComSucesso() {
        loginClient.loginUsuarios(loginValido)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(LoginConstants.MESSAGE, equalTo(LoginConstants.MSG_LOGIN_COM_SUCESSO))
                .body(LoginConstants.AUTHORIZATION, notNullValue());
    }

    @Test(groups = "Functional")
    public void testTentarFazerLoginComCredenciaisInvalidas() {
        loginClient.loginUsuarios(loginInvalido)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(LoginConstants.MESSAGE, equalTo(LoginConstants.MSG_EMAIL_OU_SENHA_INVALIDOS));
    }

    @Test(groups = {"Functional", "Contract"})
    public void testTentarFazerLoginComEmailInvalido() {
        loginClient.loginUsuarios(emailInvalido)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(LoginConstants.EMAIL, equalTo(LoginConstants.MSG_EMAIL_INVALIDO));
    }
}
