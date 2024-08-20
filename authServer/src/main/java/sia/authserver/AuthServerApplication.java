package sia.authserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.authserver.users.User;
import sia.authserver.users.UserRepository;

@SpringBootApplication
@EnableWebSecurity
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(
            UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.save(
                    new User("pavel", encoder.encode("12345"), "ROLE_ADMIN"));
            repo.save(
                    new User("nikita", encoder.encode("nikita"), "ROLE_ADMIN"));
        };
    }

}
