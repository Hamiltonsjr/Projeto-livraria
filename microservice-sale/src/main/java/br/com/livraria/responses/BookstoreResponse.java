package br.com.livraria.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookstoreResponse {

    private Long id;
    private String trade;
    private String company;
    private String CNPJ;
    private String CEP;
}
