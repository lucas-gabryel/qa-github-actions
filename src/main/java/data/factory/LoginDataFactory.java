package data.factory;

import model.LoginRequest;
import net.datafaker.Faker;
import utils.Credenciais;

import java.util.Locale;

public class LoginDataFactory {

    private static Faker faker = new Faker(new Locale("PT-BR"));

    public static LoginRequest loginValido() {

        return novoLogin();
    }

    private static LoginRequest novoLogin() {

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setEmail(Credenciais.getEmail());
        loginRequest.setPassword(Credenciais.getPassword());

        return loginRequest;
    }

    public static LoginRequest loginInvalido() {
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setEmail(faker.internet().emailAddress());
        loginRequest.setPassword(faker.internet().password());

        return loginRequest;
    }

    public static LoginRequest emailInvalido() {

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setEmail(faker.name().username());
        loginRequest.setPassword(Credenciais.getPassword());

        return loginRequest;
    }
}