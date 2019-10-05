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
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginProcessingUrl("/login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("SESSION")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/user/**", "/card/**", "/appointment/**", "/file/**").hasRole("USER")
                .antMatchers("/treatment/**", "/referral/**", "/medical-test/**", "/prescription/**").hasRole("DOCTOR")
                .antMatchers("/role/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
}
