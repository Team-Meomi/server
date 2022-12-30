package com.project.meomi.global.security

import com.project.meomi.global.filter.ExceptionHandlerFilter
import com.project.meomi.global.filter.JwtRequestFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtRequestFilter: JwtRequestFilter,
    private val exceptionHandlerFilter: ExceptionHandlerFilter
) {

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors()
            .and()
            .csrf().disable()
            .httpBasic().disable()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
            .antMatchers(HttpMethod.PATCH, "/api/v1/auth/**").permitAll()

//            .antMatchers("/api/v1/user/**").hasRole("USER")
//            .antMatchers("/api/v1/admin/**").hasRole("ADMIN")

            .antMatchers(HttpMethod.POST, "/api/v1/user/study/**").authenticated()
            .antMatchers(HttpMethod.GET, "/api/v1/user/study/**").authenticated()
            .antMatchers(HttpMethod.DELETE, "/api/v1/user/study/**").authenticated()
            .antMatchers(HttpMethod.PATCH, "/api/v1/user/study/**").authenticated()

            .antMatchers(HttpMethod.POST, "/api/v1/user/comment/**").authenticated()
            .antMatchers(HttpMethod.GET, "/api/v1/user/comment/**").authenticated()
            .antMatchers(HttpMethod.DELETE, "/api/v1/user/comment/**").authenticated()
            .antMatchers(HttpMethod.PATCH, "/api/v1/user/comment/**").authenticated()

            .antMatchers(HttpMethod.GET, "/api/v1/user/**").authenticated()

            .antMatchers(HttpMethod.GET, "/api/v1/admin/**").authenticated()

            .anyRequest().authenticated()

        http
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(exceptionHandlerFilter, JwtRequestFilter::class.java)

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

}