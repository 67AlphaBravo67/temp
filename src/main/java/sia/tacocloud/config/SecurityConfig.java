package sia.tacocloud.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.repository.UserRepository;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepo) {
        return userRepo::findByUsername; }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http/*.csrf(CsrfConfigurer::disable)*/
                .authorizeHttpRequests(auth -> auth.requestMatchers("/design", "/orders").hasRole("USER"))
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(form -> form.loginPage("/login"))
                .logout(out -> out.logoutSuccessUrl("/logout"))
                .build();
    }
}

