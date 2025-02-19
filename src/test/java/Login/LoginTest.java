package Login;

import client.LoginClient;
import data.factory.LoginDataFactory;
import model.LoginRequest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.constants.LoginConstants;

import static org.hamcrest.Matchers.*;

public class LoginTest {

    LoginClient loginClient = new LoginClient();
    LoginRequest loginValido = LoginDataFactory.loginValido();

    @Test(groups = "Funcional")
    public void testFazerLoginComSucesso() {

        loginClient.loginUsuarios(loginValido)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(LoginConstants.MESSAGE, equalTo(LoginConstants.MSG_LOGIN_COM_SUCESSO))
                .body(LoginConstants.AUTHORIZATION, notNullValue())
        ;
    }
}
