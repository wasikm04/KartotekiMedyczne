package pl.medical.service.files.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.User;
import pl.medical.service.files.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoDBAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userrepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName(); //email == login

        Object credentials = authentication.getCredentials();
        if (!(credentials instanceof String)) {
            return null;
        }
        String password = credentials.toString();
        User user = userrepository.findUserByEmail(email);


        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }

            Authentication auth = new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);
            return auth;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
