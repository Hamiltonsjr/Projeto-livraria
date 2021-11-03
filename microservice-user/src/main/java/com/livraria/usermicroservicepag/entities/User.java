package com.livraria.usermicroservicepag.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public User(String name, String nickname, Profile profile) {
        this.name = name;
        this.nickname = nickname;
        this.profile = profile;
    }
}

