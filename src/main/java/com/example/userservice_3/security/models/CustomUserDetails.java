package com.example.userservice_3.security.models;

import com.example.userservice_3.models.Role;
import com.example.userservice_3.models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@JsonDeserialize
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {



   // private User user;

    private List<CustomGrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Long userId;



   public CustomUserDetails(User user){

      // this.user=user;

       this.accountNonExpired = true;
       this.accountNonLocked = true;
       this.enabled = true;
       this.credentialsNonExpired = true;
       this.password = user.getHashedPassWord();
       this.username = user.getEmail();
       this.userId = user.getId();

       List<CustomGrantedAuthority> grantedAuthorities = new ArrayList<>();

       for (Role role: user.getRoles()) {
           grantedAuthorities.add(new CustomGrantedAuthority(role));
       }

       this.authorities = grantedAuthorities;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }





    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
                    return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
