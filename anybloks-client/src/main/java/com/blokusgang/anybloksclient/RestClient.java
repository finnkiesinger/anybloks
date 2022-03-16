package com.blokusgang.anybloksclient;

import com.blokusgang.anybloksclient.model.AuthUser;
import com.blokusgang.anybloksclient.model.User;
import com.blokusgang.anybloksclient.utils.TokenStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {
    private static final String endpoint = "http://localhost:3001";
    private static final Client client = ClientBuilder.newClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static User login(String username, String password) {
        WebTarget target = client.target(endpoint).path("users").path("auth");
        AuthUser authUser = new AuthUser(username, password);

        try {
            Response response = target
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(mapper.writeValueAsString(authUser), MediaType.APPLICATION_JSON));
            String output = response.readEntity(String.class);
            JsonNode node = mapper.readTree(output);

            if (node.get("error") != null) {
                return null;
            } else {
                String token = node.get("token").asText();
                JsonNode userNode = node.get("data");

                User user = User.fromJson(userNode);

                try {
                    TokenStorage.writeToken(token);
                } catch(Exception e) {
                    e.printStackTrace();
                }

                return user;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User getUser(String token) {
        WebTarget target = client.target(endpoint).path("users").path("get");
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .get();
        String output = response.readEntity(String.class);
        try {
            JsonNode node = mapper.readTree(output);
            if (node.get("valid").asBoolean()) {
                return User.fromJson(node.get("data"));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
