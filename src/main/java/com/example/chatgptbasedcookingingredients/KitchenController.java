package com.example.chatgptbasedcookingingredients;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// handles post mapping for our requests and nothing else
@RestController
@RequestMapping
@RequiredArgsConstructor
public class KitchenController {
    private final KitchenService service;

    @PostMapping("/ingredient")
    String categorizeIngredient(@RequestBody String ingredient) {
        return service.categorizeIngredient(ingredient);
    }

    @PostMapping("/recipe")
    String generateRecipe(@RequestBody String ingredients) {
        return service.generateRecipe(ingredients);
    }
}
