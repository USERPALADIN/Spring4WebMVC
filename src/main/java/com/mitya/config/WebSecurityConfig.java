package com.mitya.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//
//@Configuration
//@EnableWebSecurity
//@ComponentScan("com.mitya")
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(bcryptPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("login")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

//    @Bean
//    public PasswordEncoder bcryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}