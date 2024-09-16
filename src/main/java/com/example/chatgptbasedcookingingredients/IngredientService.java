package com.example.chatgptbasedcookingingredients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class IngredientService {
    private final RestClient restClient;

    public IngredientService(@Value ("${AUTH_KEY}") String key){
            this.restClient = RestClient.builder()
                    .defaultHeader("Authorization", "Bearer " + key)
                    .baseUrl("https://api.openai.com/v1/chat/completions")
                    .build();
    }


    public String categorizeIngredient(String ingredient) {
        OpenAIRequest request = new OpenAIRequest("gpt-4o-mini",
                List.of(new OpenAiMessage("user",
                        "is " + ingredient + " considered vegetarian, vegan or regular? please answer in one word without punctuation and in lowercase")),
                0.2
        );

        OpenAiResponse response = restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class);

        return response.getAnswer();
    }
}
