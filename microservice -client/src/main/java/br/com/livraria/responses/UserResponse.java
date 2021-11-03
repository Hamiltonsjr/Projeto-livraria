package br.com.livraria.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String nickname;
    private String profile;

}
