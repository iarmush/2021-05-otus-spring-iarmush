package ru.otus.lesson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers("/")
            .antMatchers("/css/**")
            .antMatchers("/book/list");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/book/create", "/book/*/delete").hasAuthority("ADMIN")
            .antMatchers("/book/list", "/book/search/*").hasAnyAuthority("READER", "WRITER", "ADMIN")
            .antMatchers("/book/*/comment").hasAnyAuthority("WRITER", "ADMIN")
            .antMatchers("/**").denyAll()
            .and()
            .formLogin()
            .and()
            .logout().logoutUrl("/logout");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
