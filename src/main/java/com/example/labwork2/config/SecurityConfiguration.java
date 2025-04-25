package com.example.labwork2.config;

import com.example.labwork2.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 🔹 Менеджер аутентификации
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // 🔹 Кодировщик пароля (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔹 Конфигурация безопасности
   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // ❌ Отключаем CSRF для API
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // ✅ Доступ к Swagger
            .requestMatchers(HttpMethod.GET, "/courses/**").hasAnyRole("STUDENT", "TEACHER", "ADMIN") // 🔹 Чтение доступно всем
            .requestMatchers("/admin/**").hasRole("ADMIN") // 🔹 Только админ
            .requestMatchers("/teacher/**").hasRole("TEACHER") // 🔹 Только учитель
            .requestMatchers(HttpMethod.POST, "/courses/**").hasAnyRole("TEACHER", "ADMIN") // 🔹 Создавать курсы могут учителя и админы
            .requestMatchers(HttpMethod.PUT, "/courses/**").hasAnyRole("TEACHER", "ADMIN") // 🔹 Обновлять курсы могут учителя и админы
            .requestMatchers(HttpMethod.DELETE, "/courses/**").hasRole("ADMIN") // 🔹 Удалять курсы может только админ
            .anyRequest().authenticated() // ❗ Остальные запросы требуют авторизации
        )
        .httpBasic(withDefaults()); // 🔹 Включаем Basic Auth
    return http.build();
}

}
