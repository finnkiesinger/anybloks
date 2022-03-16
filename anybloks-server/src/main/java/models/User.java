package models;

import com.fasterxml.jackson.databind.JsonNode;

public class User {
    private String username;
    private int games;
    private int wins;

    public String getUsername() {
        return username;
    }

    public int getGames() {
        return games;
    }

    public int getWins() {
        return wins;
    }

    public User(String username) {
        this.username = username;
        this.wins = 0;
        this.games = 0;
    }

    public User(String username, int games, int wins) {
        this.username = username;
        this.games = games;
        this.wins = wins;
    }

    public static User fromJsonNode(JsonNode node) {
        User user = new User("user_not_found");
        if (node.get("username") != null) {
            user.username = node.get("username").asText();
        }
        if (node.get("games") != null) {
            user.games = node.get("games").asInt();
        } else {
            user.games = 0;
        }
        if (node.get("wins") != null) {
            user.wins = node.get("wins").asInt();
        } else {
            user.wins = 0;
        }

        return user;
    }
}
