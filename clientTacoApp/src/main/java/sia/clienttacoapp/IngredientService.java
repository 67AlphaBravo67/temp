package sia.clienttacoapp;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

public interface IngredientService {

  Iterable<Ingredient> findAll();
  
  Ingredient addIngredient(Ingredient ingredient);

  @Bean
  @RequestScope
  default IngredientService ingredientService(OAuth2AuthorizedClientService clientService) {
    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String accessToken = null;

    if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
      OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
      String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();

      if (clientRegistrationId.equals("taco-admin-client")) {
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
        accessToken = client.getAccessToken().getTokenValue(); }
    }
    return new RestIngredientService(accessToken); }

}
