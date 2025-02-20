package data.factory;

import client.UsuarioClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.usuario.UsuarioRequest;
import model.usuario.UsuarioResponse;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import utils.constants.UsuarioConstants;

import java.util.Locale;
import java.util.Random;

public class UsuarioDataFactory {

    private static UsuarioClient usuarioClient = new UsuarioClient();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Faker faker = new Faker(new Locale("PT-BR"));
    private static Random geradorBolean = new Random();

    public static UsuarioRequest usuarioValido() {

        return novoUsuario();
    }

    public static UsuarioRequest usuarioComCredenciaisNulas() {

        UsuarioRequest usuarioResquest = novoUsuario();
        usuarioResquest.setNome(StringUtils.EMPTY);
        usuarioResquest.setEmail(StringUtils.EMPTY);
        usuarioResquest.setPassword(StringUtils.EMPTY);
        usuarioResquest.setAdministrador(StringUtils.EMPTY);

        return usuarioResquest;
    }

    public static UsuarioRequest novoUsuario() {

        UsuarioRequest usuario = new UsuarioRequest();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBolean.nextBoolean()));

        return usuario;
    }

    public static UsuarioRequest usuarioComEmailExistente() {

        UsuarioRequest usuario = new UsuarioRequest();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail("beltrano@qa.com.br");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBolean.nextBoolean()));

        return usuario;
    }

}
