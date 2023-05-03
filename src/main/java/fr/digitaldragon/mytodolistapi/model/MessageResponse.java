package fr.digitaldragon.mytodolistapi.model;

import lombok.Data;

@Data
public class MessageResponse {

    private String messageResponse;

    public MessageResponse(String message) {
        this.messageResponse = message;
    }
}
