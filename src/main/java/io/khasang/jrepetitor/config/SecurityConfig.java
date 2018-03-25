package io.khasang.jrepetitor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/create").access("hasRole('ADMIN') or hasRole('SUPERADMIN')")
                .antMatchers("/user/**").access("hasRole('USER') or hasRole('SUPERADMIN')")
                .antMatchers("/superadmin/**").access("hasRole('SUPERADMIN')")
                .and().csrf().disable().formLogin().defaultSuccessUrl("/", false);
    }

    // инмемори не использовать в продакшн
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("user").roles("USER").and()
//                .withUser("superadmin").password("superadmin").roles("SUPERADMIN").and()
//                .withUser("admin").password("admin").roles("ADMIN");
//    }

    //In-Memory Authentication
//    @Bean
//    public UserDetailsService userDetailsService() throws Exception {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
//        manager.createUser(User.withUsername("admin").password("password").roles("USER","ADMIN").build());
//        return manager;
//    }

//    @Override
//    @Bean
//    public UserDetailsService userDetailsService() {
//        User.UserBuilder builder = User.withDefaultPasswordEncoder();
//        UserDetails user = builder.username("user").password("password").roles("USER").build();
//        UserDetails admin = builder.username("admin").password("password").roles("USER", "ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
