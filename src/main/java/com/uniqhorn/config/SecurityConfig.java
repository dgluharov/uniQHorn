package com.uniqhorn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /*
     * @Autowired private AuthenticationEntryPoint authEntryPoint;
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
    }

    // Authentication logic
    /*
     * @Override protected void configure(HttpSecurity http) throws Exception {
     * http.csrf().disable(); http
     * .authorizeRequests().antMatchers("/api/login").authenticated();//.and()
     *
     *
     * .authorizeRequests().antMatchers("/api/users**").authenticated().anyRequest()
     * .hasAnyRole("ADMIN").and()
     * .authorizeRequests().antMatchers("/api/client**").authenticated().anyRequest(
     * ).hasAnyRole("MASTER").and()
     *
     *
     *
     *
     * //.authorizeRequests().antMatchers("/api/users**").authenticated().anyRequest
     * ().hasAnyRole("ADMIN").and()
     * //.authorizeRequests().antMatchers("/api/client**").authenticated().
     * anyRequest().hasAnyRole("MASTER") //.and()
     *
     * // .antMatchers("/api/users**").hasRole("ADMIN") //
     * .antMatchers("/api/client**").hasRole("MASTER")
     *
     * //.anyRequest().authenticated()
     *
     * http.authorizeRequests().antMatchers("/api/users**").hasAnyRole("ADMIN").and(
     * ).httpBasic().authenticationEntryPoint(authEntryPoint);
     * http.authorizeRequests().antMatchers("/api/client**").hasAnyRole("MASTER")
     *
     * .anyRequest().authenticated().and()
     *
     * .httpBasic() .authenticationEntryPoint(authEntryPoint); }
     */

    // users authorization

    @Configuration

    @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationEntryPoint authEntryPoint;

        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.antMatcher("/api/users/**").authorizeRequests().antMatchers("/api/users/**")
                    .hasAnyRole("ADMIN", "MASTER", "USER").and().httpBasic();
        }

    }

    // client authorization
    @Configuration
    @Order(0)
    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationEntryPoint authEntryPoint;

        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.antMatcher("/api/client/**").authorizeRequests()
					.antMatchers("/api/client/**").hasAnyRole("MASTER")
                    .and().httpBasic();
        }
    }

    // Login authorization
    @Configuration
    @Order(2)
    public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationEntryPoint authEntryPoint;

        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests().antMatchers("/api/login").authenticated().and().httpBasic()
                    .authenticationEntryPoint(authEntryPoint);
        }
    }

    @Bean
    public AuthenticationEntryPoint authEntryPoint() {
        return new AuthenticationEntryPoint();
    }

    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

}
