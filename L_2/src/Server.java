import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static Logger log = LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        log.info("trying to listen to the port");
        new ListeningServer(port).listening();
    }
}
