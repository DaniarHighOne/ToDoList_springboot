package com.example.taskList.web.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
public class JwtEntity implements UserDetails {

    private Long id;
    private final String username;
    private final String name;
    private String password;
    private final Collection< ? extends GrantedAuthority> authorities;//for granted roles such as admin etc

    /**
     * we can delete getters of name username password and auth collection because of @Data
     * which is automatically provide getters (if there no any logic inside)
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
