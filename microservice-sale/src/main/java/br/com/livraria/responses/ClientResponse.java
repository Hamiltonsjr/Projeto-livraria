package br.com.livraria.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientResponse {

    private Long id;
    private String name;
    private String CPF;
    private String address;
    private String city;
    private String zipCode;
}
