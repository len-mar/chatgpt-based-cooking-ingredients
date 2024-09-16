package com.example.chatgptbasedcookingingredients;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// handles post mapping for our requests and nothing else
@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService service;

    @PostMapping
    String categorizeIngredient(@RequestBody String ingredient) {
        return service.categorizeIngredient(ingredient);
    }

    @PostMapping("/recipes")
    String generateRecipe(@RequestBody String ingredients){
        return service.generateRecipe(ingredients);
    }
}
