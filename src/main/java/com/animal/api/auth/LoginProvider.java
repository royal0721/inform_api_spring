package com.animal.api.auth;

import com.animal.api.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginProvider implements AuthenticationProvider {

    @Autowired
    private LoginUserService userLoginService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginProvider() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 獲得使用者帳號及密碼
        // decode
        String account = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = userLoginService.loadUserByUsername(account);
        // 帳號密碼驗證邏輯
        System.out.println(account);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(password);
        System.out.println(passwordEncoder.matches(password, user.getPassword()));
//        password.equals(user.getPassword())
        if (account.equals(user.getUsername()) && passwordEncoder.matches(password, user.getPassword()) ) {

            // 生成Authentication令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(account, password, user.getAuthorities());
            return auth;
        } else {
            throw new BadCredentialsException("Password error");
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}