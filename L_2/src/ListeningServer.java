import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListeningServer {
    private int port;
    private ServerSocket serverSocket;
    private Logger log = LoggerFactory.getLogger(ListeningServer.class);

    public ListeningServer(int port) {
        this.port = port;
    }

    public void listening () {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            log.error("can't bind on port" + port);
        }
        while (true) {
            log.info("waiting for connections to the server");
            try {
                Socket socketClient = serverSocket.accept();
                log.info("connection was successful");
                log.info("create a new thread that will work with the next client");
                new Thread(new ConnectionHandler(socketClient)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
