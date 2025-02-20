package model.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse extends UsuarioRequest{

    private String message;
    @JsonProperty("_id")
    private String id;

}