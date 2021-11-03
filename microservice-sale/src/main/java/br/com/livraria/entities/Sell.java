package br.com.livraria.entities;

import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;


@Builder
@Entity
@Table(name = "vendas")
public class Sell implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendedor")
    private Long vendedor;

    @Column(name = "cliente")
    private Long cliente;

    @Column(name = "loja")
    private Long loja;

    @Column(name = "livro")
    private Long livro;

    public Sell(){

    }

    public Sell(Long id, Long vendedor, Long cliente, Long loja, Long livro) {
        this.id = id;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.loja = loja;
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendedor() {
        return vendedor;
    }

    public void setVendedor(Long vendedor) {
        this.vendedor = vendedor;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public Long getLoja() {
        return loja;
    }

    public void setLoja(Long loja) {
        this.loja = loja;
    }

    public Long getLivro() {
        return livro;
    }

    public void setLivro(Long livro) {
        this.livro = livro;
    }
}
