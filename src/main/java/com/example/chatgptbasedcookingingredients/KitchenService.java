package com.example.chatgptbasedcookingingredients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

// builds our client to communicate with openai's api
// and specifies request and response for our ingredient method
@Service
public class KitchenService {
    private final RestClient restClient;

    public KitchenService(@Value("${AUTH_KEY}") String key) {
        // passes the api key via header
        this.restClient = RestClient.builder()
                .defaultHeader("Authorization", "Bearer " + key)
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .build();
    }


    public String categorizeIngredient(String ingredient) {
        // builds request, incl a model and a list of messages (containing a user and their prompt) and a temperature to define specificity
        OpenAIRequest request = new OpenAIRequest("gpt-4o-mini",
                List.of(new OpenAiMessage(
                        "user",
                        "is " + ingredient + " considered vegetarian, vegan or regular? please answer in one word without punctuation and in lowercase")),
                0.2
        );

        // sends request and maps response onto our response object (and returns just the answer field)
        OpenAiResponse response = restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class);

        return response.getAnswer();
    }

    // bonus: recipe generator
    public String generateRecipe(String ingredients) {
        // builds request, incl a model and a list of messages (containing a user and their prompt) and a temperature to define specificity
        OpenAIRequest request = new OpenAIRequest("gpt-4o-mini",
                List.of(new OpenAiMessage(
                        "user",
                        "recipe with title, description and list of ingredients using: " + ingredients)),
                0.2
        );

        // sends request and maps response onto our response object (and returns just the answer field)
        OpenAiResponse response = restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class);

        return response.getAnswer();
    }
}
