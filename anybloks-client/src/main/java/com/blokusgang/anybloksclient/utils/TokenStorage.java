package com.blokusgang.anybloksclient.utils;

import java.io.*;

public class TokenStorage {
    private static final File storage = new File(System.getProperty("user.dir"), "token.txt");
    public static void writeToken(String token) throws IOException {
        clearToken();
        storage.createNewFile();
        PrintWriter writer = new PrintWriter(storage);
        writer.print(token);
        writer.close();
    }

    public static String readToken() throws IOException {
        if (!storage.exists()) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new FileReader(storage));
        String token = reader.readLine();
        reader.close();
        return token;
    }

    public static void clearToken() {
        if (storage.exists()) {
            storage.delete();
        }
    }
}
