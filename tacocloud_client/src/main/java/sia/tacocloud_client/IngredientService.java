package sia.tacocloud_client;

public interface IngredientService {
    public Iterable<Ingredient> findAll();

    public Ingredient addIngredient(Ingredient ingredient);
}
