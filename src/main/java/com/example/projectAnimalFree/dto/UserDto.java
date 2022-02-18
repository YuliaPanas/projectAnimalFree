package com.example.projectAnimalFree.dto;

import com.example.projectAnimalFree.enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Set<Roles> roles;


    public UserDto() {
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
