package sia.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.config.Human;

public interface UserRepository extends CrudRepository<Human, Long> {
    Human findByUsername(String username);
}
