package data.factory;

import model.LoginRequest;
import utils.Credenciais;

public class LoginDataFactory {

    public static LoginRequest loginValido() {

        return novoLogin();
    }

    private static LoginRequest novoLogin() {

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setEmail(Credenciais.getEmail());
        loginRequest.setPassword(Credenciais.getPassword());

        return loginRequest;
    }
}
