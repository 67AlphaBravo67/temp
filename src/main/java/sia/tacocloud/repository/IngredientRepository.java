
package sia.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.model.Ingredient;

@Repository
public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
}