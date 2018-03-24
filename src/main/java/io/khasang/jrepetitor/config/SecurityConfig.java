package io.khasang.jrepetitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/create").access("USER")
                .antMatchers("/users/**").access("USER")
                .and().csrf().disable().formLogin().defaultSuccessUrl("/",false);
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("user").roles("user").build());
//        manager.createUser(User.withUsername("admin").password("admin").roles("admin").build());
//        return manager;
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {

//        //не использовать инмемори в продакшене
//        auth.inMemoryAuthentication().withUser("user").password("user").roles("user");
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("admin");
//            }
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


        private PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
}


