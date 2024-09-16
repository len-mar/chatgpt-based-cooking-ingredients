package com.example.chatgptbasedcookingingredients;

import java.util.List;

    /**
     * {
     *     "choices": [
     *         {
     *             "message": {
     *                 "role": "assistant",
     *                 "content": "This is a test! How can I assist you further?"
     *             }
     *         }
     *     ]
     * }
     */

    public record OpenAiResponse(List<OpenAiChoices> choices) {

        public String getAnswer(){
            return choices().get(0).message().content();
        }
    }
