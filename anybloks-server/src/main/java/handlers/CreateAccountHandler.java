package handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.AuthUserRepository;
import db.UserRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.AuthUser;
import models.User;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.http.HttpStatus;
import utils.JwtUtil;
import utils.JsonGenerator;

import java.io.IOException;

public class CreateAccountHandler extends HttpServlet {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        String body = IOUtils.toString(req.getReader());
        JsonNode node = mapper.readTree(body);

        String username = node.get("username").asText();
        String password = node.get("password").asText();

        if (username == null || password == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            resp.getWriter().println(JsonGenerator.error("Your request could not be handled"));
        }

        password = JwtUtil.hm256.hmacHex(password);

        AuthUser findUser = AuthUserRepository.find(username);
        AuthUser newUser = new AuthUser(username, password);
        if (findUser != null) {
            if (findUser.equals(newUser)) {
                User user = UserRepository.find(findUser.getUsername());
                String userJson = mapper.writeValueAsString(user);
                String data = JsonGenerator.jwt(JwtUtil.generate(findUser.getUsername()), userJson);

                resp.setStatus(HttpStatus.OK_200);
                resp.getWriter().println(data);
                return;
            }
        } else {
            int id = AuthUserRepository.create(newUser);
            if (id != 0) {
                User user = UserRepository.createUser(id, username);
                String userJson = mapper.writeValueAsString(user);

                String data = JsonGenerator.jwt(JwtUtil.generate(user.getUsername()), userJson);

                resp.setStatus(HttpStatus.OK_200);
                resp.getWriter().println(data);
                return;
            }
        }

        resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
        resp.getWriter().println(JsonGenerator.error("Something went wrong"));
    }
}
