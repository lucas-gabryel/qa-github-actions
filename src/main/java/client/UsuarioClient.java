package client;

import io.restassured.response.Response;
import model.usuario.UsuarioRequest;
import utils.constants.UsuarioConstants;

import static io.restassured.RestAssured.given;

public class UsuarioClient extends BaseClient{

    public Response cadastrarUsuarios(UsuarioRequest usuarioRequest) {

        return
                given()
                        .spec(super.set())
                        .body(usuarioRequest)
                        .when()
                        .post(UsuarioConstants.ENDPOINT_USUARIOS)
                ;
    }

    public Response buscarUsuario(String idUsuario) {

        return
                given()
                        .spec(set())
                        .pathParam(UsuarioConstants.ID, idUsuario)
                        .when()
                        .get(UsuarioConstants.ENDPOINT_USUARIOS_ID)
                ;
    }

    public Response listarUsuarios() {

        return
                given()
                        .spec(set())
                        .when()
                        .get(UsuarioConstants.ENDPOINT_USUARIOS)
                ;
    }

    public Response listarUsuarios(String chave, String valor) {

        return
                given()
                        .spec(set())
                        .queryParam(chave, valor)
                        .when()
                        .get(UsuarioConstants.ENDPOINT_USUARIOS)
                ;
    }

    public Response listarUsuariosEmailInvalido(String email) {

        return
                given()
                        .spec(set())
                        .queryParam(email)
                        .when()
                        .get(UsuarioConstants.ENDPOINT_USUARIOS)
                ;
    }

    public Response usuarioComCredenciaisNulas(String nome, String email, String password, String admin) {
        return
                given()
                        .spec(super.set())
                        .queryParam(UsuarioConstants.NOME, nome)
                        .queryParam(UsuarioConstants.EMAIL, email)
                        .queryParam(UsuarioConstants.PASSWORD, password)
                        .queryParam(UsuarioConstants.IS_ADMIN, admin)
                        .when()
                        .get(UsuarioConstants.ENDPOINT_USUARIOS)
                ;
    }

    public Response atualizarUsuario(UsuarioRequest usuarioResquest, String idUsuario) {

        return
                given()
                        .spec(super.set())
                        .pathParam(UsuarioConstants.ID, idUsuario)
                        .body(usuarioResquest)
                        .when()
                        .put(UsuarioConstants.ENDPOINT_USUARIOS_ID)
                ;
    }

    public Response excluirUsuarios(String idUsuario) {
        return
                given()
                        .spec(super.set())
                        .pathParam(UsuarioConstants.ID, idUsuario)
                        .when()
                        .delete(UsuarioConstants.ENDPOINT_USUARIOS_ID)
                ;
    }
}
