package server;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.util.concurrent.CountDownLatch;

@ClientEndpoint
@ServerEndpoint(value = "/game/")
public class GameSocket {
    CountDownLatch closureLatch = new CountDownLatch(1);

    @OnOpen
    public void onWebSocketConnect(Session session) {
        session.setMaxIdleTimeout(3*60*1000);
        System.out.println("Socket connected: " + session);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("Socket closed: " + reason);
        closureLatch.countDown();
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
        cause.printStackTrace();
    }

    @OnMessage
    public void onWebSocketMessage(Session session, String message) {
        System.out.println("Message: " + message);
    }

    public void awaitClosure() throws InterruptedException {
        System.out.println("Awaiting closure from remote");
        closureLatch.await();
    }
}
