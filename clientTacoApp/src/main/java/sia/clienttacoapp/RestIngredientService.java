package sia.clienttacoapp;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

public class RestIngredientService implements IngredientService {
    private final RestTemplate restTemplate;
    public RestIngredientService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return Arrays.asList(Objects.requireNonNull(restTemplate
                .getForObject("http://localhost:8080/api/ingredients", Ingredient[].class)));
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return restTemplate.postForObject( "http://localhost:8080/api/ingredients", ingredient, Ingredient.class);
    }

    public RestIngredientService(String accessToken) { this.restTemplate = new RestTemplate();
        if (accessToken != null) {
            this.restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));
        }
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        return (request, bytes, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + accessToken);
            return execution.execute(request, bytes);
        };
    }
}
