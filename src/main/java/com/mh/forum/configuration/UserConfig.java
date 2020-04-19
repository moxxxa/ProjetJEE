package com.mh.forum.configuration;

import com.mh.forum.exceptions.UserAuthenticationException;
import com.mh.forum.services.UserAuthentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedResource;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

@Configuration
@ManagedResource
public class UserConfig {



    public UserAuthentication tokenDecode(String token) {
        try {
            int pos = token.indexOf(" ");
            
            token = token.substring(pos + 1);
            String credential = new String(Base64.getDecoder().decode(token));
            String[] credentials = credential.split(":");
            return new UserAuthentication(credentials[0], credentials[1]);
        } catch (Exception e) {
            throw new UserAuthenticationException();
        }
    }
/*    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}
