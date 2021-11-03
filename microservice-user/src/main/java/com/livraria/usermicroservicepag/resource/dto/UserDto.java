package com.livraria.usermicroservicepag.resource.dto;

import com.livraria.usermicroservicepag.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@ToString
public class UserDto {

    private Long id;
    private String name;
    private String nickname;
    private String profile;

    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.profile = user.getProfile().getProfile();
    }

    public static List<UserDto> converter(List<User> users){
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
