import java.io.IOException;
import java.net.*;
import java.rmi.server.UID;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("error: not enough arguments");
            return;
        }
        InetAddress address = InetAddress.getByName(args[0]);
        UID uid = new UID();
        MulticastSocket socket = new MulticastSocket(6000);
        socket.joinGroup(address);
        socket.setSoTimeout(100);
        SendReceiver sendReceiver = new SendReceiver(socket,address,uid.toString());
        sendReceiver.sendRecv();
    }
}


