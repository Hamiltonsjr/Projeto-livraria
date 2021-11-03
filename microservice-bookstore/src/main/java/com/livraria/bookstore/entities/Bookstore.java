package com.livraria.bookstore.entities;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bookstore")
@Builder
@AllArgsConstructor
public class Bookstore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    @NotNull
    private String trade;
    @Column(name = "company")
    @NotNull
    private String company;
    @Column(name = "CNPJ")
    @NotNull
    private String CNPJ;
    @Column(name = "cep")
    @NotNull
    private String CEP;
}