package usuarios;

import client.UsuarioClient;
import data.factory.UsuarioDataFactory;
import model.usuario.UsuarioRequest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utils.constants.UsuarioConstants;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Cadastro de Usuários")
public class CadastrarUsuariosTest {

    private final UsuarioClient usuariosClient = new UsuarioClient();
    UsuarioRequest usuarioResquest = UsuarioDataFactory.usuarioValido();
    UsuarioRequest usuario = UsuarioDataFactory.usuarioValido();
    UsuarioRequest usuarioEmail = UsuarioDataFactory.usuarioComEmailExistente();
    UsuarioRequest usuarioFalho = UsuarioDataFactory.usuarioValido();

    @Test(groups = "Contract")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Cadastrar um usuário com sucesso e validar contrato")
    @Description("Testa se é possível cadastrar um usuário e validar o schema do contrato")
    public void testDeveCadastrarUsuarioComSucessoSchema() {

        usuariosClient.cadastrarUsuarios(usuarioResquest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)//sc_created
                .body(UsuarioConstants.MESSAGE, equalTo(UsuarioConstants.MSG_CADASTRO_COM_SUCESSO))
                .body(UsuarioConstants.ID, notNullValue())
                .body(matchesJsonSchemaInClasspath("schemas/cadastro_usuario.json"));
    }

    @Test(groups = "Functional")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Cadastrar um usuário com sucesso")
    @Description("Testa se um usuário válido pode ser cadastrado com sucesso")
    public void testDeveCadastrarUsuarioComSucesso() {

        usuariosClient.cadastrarUsuarios(usuario)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body(UsuarioConstants.MESSAGE, equalTo(UsuarioConstants.MSG_CADASTRO_COM_SUCESSO))
                .body(UsuarioConstants.ID, notNullValue());
    }

    @Test(groups = "HealthCheck")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tentar cadastrar um usuário com email já cadastrado")
    @Description("Testa se o sistema impede o cadastro de um usuário com email já existente")
    public void testTentarCadastrarUsuarioComEmailJaCadastrado() {

        usuariosClient.cadastrarUsuarios(usuarioEmail)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(UsuarioConstants.MESSAGE, equalTo(UsuarioConstants.MSG_EMAIL_JA_CADASTRADO));
    }

    @Test(groups = "Functional")
    @Severity(SeverityLevel.MINOR)
    @Story("Teste falho proposital de cadatrar usuario")
    @Description("Este teste falha propositalmente")
    public void testFalhaProposital() {
        usuariosClient.cadastrarUsuarios(usuarioFalho)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(UsuarioConstants.MESSAGE, equalTo("Mensagem inexistente"));
    }
}
