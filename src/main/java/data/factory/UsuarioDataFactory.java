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

    public static String cadastrarUsuarioERetornarID() {

        return usuarioClient.cadastrarUsuarios(novoUsuario())
                .path(UsuarioConstants.ID);
    }

    public static Object[] atualizarUsuario() {

        return new Object[] {novoUsuario(), cadastrarUsuarioERetornarID()};
    }

    public static UsuarioRequest atualizarNomeVazio() {

        UsuarioRequest usuario = new UsuarioRequest();
        usuario.setNome(StringUtils.EMPTY);
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBolean.nextBoolean()));

        return usuario;
    }

    public static Object[] atualizarUsuarioComEmailCadastrado() {

        UsuarioRequest usuarioResquest = novoUsuario();
        usuarioResquest.setEmail(retornarUsuarioExistente().getEmail());

        return new Object[] {usuarioResquest, cadastrarUsuarioERetornarID()};
    }

    public static UsuarioResponse retornarUsuarioExistente() {

        UsuarioResponse usuario =
                objectMapper.convertValue(usuarioClient.listarUsuarios().path("usuarios[0]"), UsuarioResponse.class);

        return usuario;
    }

    public static String retornarIDInvalido() {

        return faker.idNumber().invalid();
    }

    public static String retornarIDNome() {

        return faker.name().fullName();
    }

}
