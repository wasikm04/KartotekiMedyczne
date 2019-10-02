package pl.medical.service.files.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.User;
import pl.medical.service.files.repositories.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoDBAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userrepository;
    private PasswordEncoder passwordEncoder;


    public MongoDBAuthenticationProvider(PasswordEncoder passwordEncoder,
                                         UserRepository userrepository) {
        this.passwordEncoder = passwordEncoder;
        this.userrepository = userrepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName(); //email == login
        String password = authentication.getCredentials().toString();
        User user = userrepository.findUserByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication failed for " + email);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }

        return new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
