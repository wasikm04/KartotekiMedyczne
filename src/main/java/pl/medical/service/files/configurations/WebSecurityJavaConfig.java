package pl.medical.service.files.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) //@PreAuthorize("hasRole('ROLE_ADMIN')")
public class WebSecurityJavaConfig extends WebSecurityConfigurerAdapter {

    private MongoDBAuthenticationProvider authenticationProvider;

    public WebSecurityJavaConfig(MongoDBAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/js/**", "/css/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        HttpSecurity disable = http
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setStatus(HttpServletResponse.SC_OK);
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .invalidateHttpSession(true)
                .deleteCookies("SESSION")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .antMatchers("/testData").permitAll()
                .antMatchers("/testAppointment").permitAll()
                .antMatchers("/testDoctor").permitAll()
                .antMatchers("/actuator/**").hasRole("ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/user/**", "/card/**", "/appointment/**", "/file/**", "/doctor-card/**").hasRole("USER")
                .antMatchers("/treatment/**", "/referral/**", "/medical-test/**", "/prescription/**").hasRole("DOCTOR")
                .antMatchers("/role/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
}
