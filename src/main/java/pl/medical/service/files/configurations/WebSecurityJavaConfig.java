package pl.medical.service.files.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) //@PreAuthorize("hasRole('ROLE_ADMIN')") można umieszczać w servisach
public class WebSecurityJavaConfig extends WebSecurityConfigurerAdapter {

    private MongoDBAuthenticationProvider authenticationProvider;

    public WebSecurityJavaConfig(MongoDBAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                //.loginPage("/login")
                .loginProcessingUrl("/loginApp")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("SESSION")
                .permitAll()
                    .and()
                .authorizeRequests()
                .antMatchers("/user/**", "/card/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                    //prescriptions,medicaltests,referral,treatment rozdzielić do kontrolerów i uprawnienia tylko pracowników
                //session.invalidate(); do kontrolera logowania??
                    .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
