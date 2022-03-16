package server;

import handlers.CreateAccountHandler;
import handlers.FetchUserHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;

public class GameServer {

    public static void main(String[] args) throws Exception {
        GameServer server = new GameServer();
        server.setPort(3001);
        server.start();
        server.join();
    }

    Server server;
    ServerConnector connector;

    public GameServer() {
        this.server = new Server();
        this.connector = new ServerConnector(server);
        this.server.addConnector(connector);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        handler.addServlet(CreateAccountHandler.class, "/users/auth");
        handler.addServlet(FetchUserHandler.class, "/users/get");

        JakartaWebSocketServletContainerInitializer.configure(handler, (context, container) -> {
            container.setDefaultMaxBinaryMessageBufferSize(65535);
            container.addEndpoint(GameSocket.class);
        });
    }

    public void setPort(int port) {
        this.connector.setPort(port);
    }

    public void start() throws Exception {
        this.server.start();
    }

    public void join() throws InterruptedException {
        System.out.println("URI: " + server.getURI());
        System.out.println("Press CTRL-C to stop the server");
        this.server.join();
    }

    public void stop() throws Exception {
        this.server.stop();
    }
}
