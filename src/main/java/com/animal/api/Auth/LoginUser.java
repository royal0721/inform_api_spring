package com.animal.api.Auth;

import com.animal.api.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.lang.String;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class LoginUser implements UserDetails {

    private User loginUser;
    public LoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
    public String getId() {
        return loginUser.getId();
    }

    public String getName(){
        return loginUser.getLast_name()+" "+loginUser.getFirst_name();
    }
    //problem here
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> authorities = new LinkedList<>();
            if (loginUser.getRole()==3) {
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
            }
            authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }
    @Override
    public String getPassword() {
        return loginUser.getPassword();
    }

    @Override
    public String getUsername() {
        return getId()+','+loginUser.getRole();
    }

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

//    public Integer getRole(){ return loginUser.getRole(); }
}
