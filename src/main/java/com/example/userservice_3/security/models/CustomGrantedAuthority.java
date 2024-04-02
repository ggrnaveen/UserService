package com.example.userservice_3.security.models;

import com.example.userservice_3.models.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@NoArgsConstructor
@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

   // private Role role;

    private String authority;


    public CustomGrantedAuthority(Role role) {
        this.authority = role.getName();
    }



    @Override
    public String getAuthority() {
        return authority;
    }
}
