package com.blokusgang.anybloksclient.model;

import com.fasterxml.jackson.databind.JsonNode;

public class User {
    private String username;
    private int games;
    private int wins;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public User(String username, int games, int wins) {
        this.username = username;
        this.games = games;
        this.wins = wins;
    }

    public static User fromJson(JsonNode userNode) {
        return new User(
                userNode.get("username").asText(),
                userNode.get("games").asInt(),
                userNode.get("wins").asInt()
        );
    }
}
