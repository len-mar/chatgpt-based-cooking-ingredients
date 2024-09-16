package com.example.chatgptbasedcookingingredients;

import java.util.List;
    // structures objects the same way the api's response is also structured
    public record OpenAiResponse(List<OpenAiChoices> choices) {

        // returns just the answer since the response object has many other fields
        public String getAnswer(){
            return choices().get(0).message().content();
        }
    }
