package br.com.livraria.entities;
import lombok.*;
import javax.persistence.*;

@Builder
@Table(name = "client")
@Entity
public class Client{

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;
    @Column(name = "Nome")
    @NonNull
    private String name;
    @Column(name = "CPF")
    @NonNull
    private String CPF;
    @Column(name = "Endereço")
    @NonNull
    private String address;
    @Column(name = "Cidade")
    @NonNull
    private String city;
    @Column(name = "CEP")
    @NonNull
    private String zipCode;

    public Client(){
    }

    public Client(Long id, @NonNull String name, @NonNull String CPF, @NonNull String address, @NonNull String city, @NonNull String zipCode) {
        this.id = id;
        this.name = name;
        this.CPF = CPF;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
