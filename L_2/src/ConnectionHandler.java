import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private Socket socket;
    private Logger log = LoggerFactory.getLogger(ConnectionHandler.class);

    ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        log.info("create a directory");
        File dir = new File("uploads");
        if (!dir.exists()) {
            dir.mkdir();
        }

        int realSize = 0;
        int tempSize = 0;
        long startTime;
        long timeTempStart, timeTempEnd;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             BufferedWriter massage = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
             log.info("we accept packages");

            startTime = timeTempStart = System.currentTimeMillis();
            Packet packet = (Packet) objectInputStream.readObject();
            File file = new File(dir.getPath(), packet.getName());
            if (!file.exists()) {
                file.createNewFile();
            } else {
                int i = 0;
                while (true) {
                    file = new File(dir.getPath(), String.valueOf(i) + packet.getName());
                    if (!file.exists()) {
                        file.createNewFile();
                        break;
                    } else i++;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(packet.getData());
            realSize += packet.getSize();

            while ((packet = (Packet) objectInputStream.readObject()).getSize() != 0) {
                realSize += packet.getSize();
                tempSize += packet.getSize();
                fileOutputStream.write(packet.getData(), 0, packet.getSize());
                timeTempEnd = System.currentTimeMillis();
                if (timeTempEnd - timeTempStart > 3000) {
                    long instantReceptionRate = tempSize/(timeTempEnd - timeTempStart);
                    long averageSpeed = realSize/(timeTempEnd - startTime);
                    printSpeed(instantReceptionRate,averageSpeed);
                    timeTempStart = timeTempEnd;
                    tempSize = 0;
                }
            }
            timeTempEnd = System.currentTimeMillis();
            long instantReceptionRate = tempSize/(timeTempEnd - timeTempStart);
            long averageSpeed = realSize/(timeTempEnd - startTime);
            printSpeed(instantReceptionRate,averageSpeed);

            log.info("file received");
            log.info("compare the size of the sent and received files");
            if (checkFileSize(packet.getFileSize(), realSize)) {
                massage.write(1);
                massage.flush();
            } else {
                massage.write(0);
                massage.flush();
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    boolean checkFileSize(long sentFileSize, long receivedFileSize) {
        // проверяем совпадает ли размер переданного файла с полученным
        return (sentFileSize == receivedFileSize);
    }

    void printSpeed(long instantReceptionRate, long averageSpeed) {
        //выводим мгновенную скорость приёма и среднюю скорость за сеанс
        System.out.println("instant reception rate = " + instantReceptionRate );
        System.out.println("instant reception rate = " + averageSpeed );
    }
}
