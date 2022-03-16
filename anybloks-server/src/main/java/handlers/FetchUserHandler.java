package handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.UserRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import utils.JwtUtil;
import utils.JsonGenerator;

import java.io.IOException;

public class FetchUserHandler extends HttpServlet {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bearer = req.getHeader("Authorization");

        String token = bearer.split(" ")[1];
        String[] components = token.split("\\.");
        String header = components[0];
        String payload = components[1];
        String hashed = JwtUtil.hm256.hmacHex(header + "." + payload);

        String validationToken = header + "." + payload + "." + hashed;
        String decodedPayload = new String(JwtUtil.base64.decode(payload));
        JsonNode payloadMap = mapper.readTree(decodedPayload);
        String username = payloadMap.get("sub").asText();
        User user = UserRepository.find(username);

        long exp = payloadMap.get("exp").asLong();

        boolean valid = token.equals(validationToken) && System.currentTimeMillis() < exp && user != null;
        resp.setContentType("application/json");
        if (valid) {
            resp.getWriter().println(JsonGenerator.valid(true, mapper.writeValueAsString(user)));
        } else {
            resp.getWriter().println(JsonGenerator.valid(false));
        }
    }
}
