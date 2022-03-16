package utils;

public class JsonGenerator {
    public static String error(String message) {
        return "{\"error\": \"" + message + "\"}";
    }
    public static String valid(boolean valid) {
        return "{\"valid\": " + valid + "}";
    }
    public static String valid(boolean valid, String data) {
        return "{\"valid\": " + valid + ", \"data\": " + data + "}";
    }
    public static String jwt(String token, String data) {
        return "{\"token\": \"" + token + "\", \"data\": " + data + "}";
    }
}
