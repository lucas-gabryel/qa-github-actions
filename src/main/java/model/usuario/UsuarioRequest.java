package model.usuario;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    private String nome;
    private String email;
    private String password;
    private String administrador;
}