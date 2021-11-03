package br.com.livraria.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponse {

    private Long id;
    private String name;
    private String ibsn;
    private String author;
    private String quantity;
    private String price;
    private String store;

}
