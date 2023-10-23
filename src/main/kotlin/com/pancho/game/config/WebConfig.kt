package com.pancho.game.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebMvc
@EnableWebSecurity
@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000/")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION")
            .allowCredentials(true) //FE to access login cookie generated from BE
    }

//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        val users: User.UserBuilder = User.withDefaultPasswordEncoder()
//        val manager = InMemoryUserDetailsManager()
//        manager.createUser(users.username("user").password("password").roles("USER").build())
//        manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build())
//        return manager
//    }

    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .securityMatcher("/board/**")
            .authorizeHttpRequests{
                    it.anyRequest().authenticated()
            }
        return http.build()
    }
}