package com.mg.paymybuddy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Resource
//    UserDetailsService userDetailsService;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email as username,password,enabled "
                        + "from user "
                        + "where email = ?");
    }

    @Override //TODO: passer par le service
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/css/**", "/jquery/**", "images/**").permitAll()
                .antMatchers("/login","/register","/home","/logout*").permitAll()
                .antMatchers("/profile","/contact","/transfer").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_perform")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/profile", true);
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        return authenticationProvider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
}
