package sia.serverauthorization;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.serverauthorization.users.User;
import sia.serverauthorization.users.UserRepository;

@SpringBootApplication
public class ServerAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerAuthorizationApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(
            UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.save(
                    new User("pavel", encoder.encode("password"), "ROLE_ADMIN"));
            repo.save(
                    new User("nikita", encoder.encode("password"), "ROLE_ADMIN"));
        };
    }
}
