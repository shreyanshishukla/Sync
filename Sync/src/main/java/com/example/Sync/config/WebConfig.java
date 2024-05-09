package com.example.Sync.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
@EnableWebMvc
@EnableMethodSecurity
public class WebConfig {

@Autowired
private UserDetailsService userDetailsService;

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
  return authenticationConfiguration.getAuthenticationManager();

}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf((csrf)->csrf.disable()).authorizeHttpRequests((authorize)->
                {
                    authorize.requestMatchers("/auth/login").permitAll().anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterConfig(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:8081");
        config.setAllowedHeaders(Arrays.asList(

                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT

        ));
        config.setAllowedMethods(
              Arrays.asList(  HttpMethod.GET.name(),
                      HttpMethod.POST.name(),
                      HttpMethod.DELETE.name(),
                      HttpMethod.PUT.name())

        );
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**",config);

        FilterRegistrationBean<CorsFilter> bean= new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(-102);
         return  bean;
    }
}
