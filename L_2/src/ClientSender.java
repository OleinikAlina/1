import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSender {
    ObjectOutputStream objectOutputStream;
    private Socket socket;
    private String filePath;
    private InetAddress address;
    private int port;
    private int sizeBuff = 1024;
    private byte[] buffer;
    private Logger log = LoggerFactory.getLogger(ClientSender.class);


    ClientSender(String filePath, InetAddress address, int port) {
        this.filePath = filePath;
        this.address = address;
        this.port = port;
    }

    public void send() {
        //буфер в который будем считывать данные
        buffer = new byte[sizeBuff];
        File file = new File(filePath);
        String name = file.getName();
        long fileSize = file.length();

        try {
            log.info("Create client socket");
            this.socket = new Socket(address, port);
            log.info("connection established");
        } catch (IOException e) {
            log.error("connection not established");
        }

        try {
            log.info("Create ObjectOutputStream");
            objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            log.info("Created ObjectOutputStream");
        } catch (IOException e) {
            log.error("ObjectOutputStream not created");
        }

        try (BufferedInputStream bufferInputStream = new BufferedInputStream(new FileInputStream(file))) {
            log.info("Created bufferInputStream");
            int packetSize;
            log.info("read data into the buffer");
            while ((packetSize = bufferInputStream.readNBytes(buffer, 0, sizeBuff)) != 0) {
                objectOutputStream.writeObject(new Packet(name, packetSize, buffer, fileSize));
                buffer = new byte[1024];
            }
            objectOutputStream.writeObject(new Packet(name, 0, null, fileSize));
            log.info("file sent");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ждем ответ сервера
        try (BufferedReader massage = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            if (massage.read() == 1) {
                System.out.println("sending was successful");
            } else {
                System.out.println("sending was not successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            objectOutputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

