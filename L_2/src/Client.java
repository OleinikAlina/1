import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
    private static Logger log = LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        log.info("program started working");
        if (args.length < 3) {
            log.info("error: not enough arguments");
            return;
        }
        try {
            String filePath = args[0];
            InetAddress address = InetAddress.getByName(args[1]);
            int port = Integer.parseInt(args[2]);
            new ClientSender(filePath, address, port).send();
        } catch (UnknownHostException e) {
            log.error("invalid command line arguments");
        }
    }
}
