import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.rmi.server.UID;
import java.util.*;

public class SendReceiver {
    Logger log = LoggerFactory.getLogger(this.getClass());
    private MulticastSocket socket;
    private InetAddress address;
    private String uid;
    private HashMap<KeyMap, Date> users = new HashMap<>();

    public SendReceiver(MulticastSocket socket, InetAddress address, String uid) {
        this.socket = socket;
        this.address = address;
        this.uid = uid;
    }

    public void sendRecv() {
        //KeyMap keyMap = new KeyMap(address, uid.toString().getBytes());
        byte[] bufSend = uid.getBytes();
        byte[] bufRecv = new byte[bufSend.length];
        int port = 6000;

        DatagramPacket packetSend = new DatagramPacket(bufSend, bufSend.length, address, port);
        DatagramPacket packetRecv = new DatagramPacket(bufRecv, bufRecv.length);
        Date sendTime = new Date();
        while (true) {
            try {
                Date currentTime = new Date();
                if ((currentTime.getTime() - sendTime.getTime()) > 1000) {
                    socket.send(packetSend);
                    log.info("sent: " + currentTime);
                    sendTime = currentTime;
                }
                socket.receive(packetRecv);
                packageHandler(packetRecv);
            } catch (SocketTimeoutException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean containsKey(InetAddress addr, String uid) {
        for (KeyMap key : users.keySet()) {
            if (key.getAddress().equals(addr) &&
                    key.getUserUid().equals(uid)) {
                return true;
            }
        }
        return false;
    }

    private void replace(InetAddress addr, String uid, Date date) {
        for (KeyMap key : users.keySet()) {
            if (key.getAddress().equals(addr) &&
                    key.getUserUid().equals(uid)) {
                users.replace(key, date);
                return;
            }
        }
    }

    private void put(InetAddress addr, String uid, Date date) {
        users.put(new KeyMap(addr, uid), date);
    }

    private void packageHandler(DatagramPacket packetRecv) {
        Date date = new Date();
        log.info("received " + date);
        InetAddress recvAddr = packetRecv.getAddress();
        String recvData = new String(packetRecv.getData());
        if (containsKey(recvAddr, recvData)) {
            replace(recvAddr, recvData, date);
            updateUsersList(false);
        } else {
            put(recvAddr, recvData, date);
            updateUsersList(true);
        }
    }

    private void printUsersList() {
        System.out.println("############################################");
        for (KeyMap key : users.keySet()) {
            System.out.println(key.getAddress() + ":  " + key.getUserUid() + " " + users.get(key));
        }
        System.out.println("############################################");
    }

    private void updateUsersList(boolean isAdded) {
        Date date = new Date();
        boolean isRemoved = false;
        List<KeyMap> list = new ArrayList<>();
        for (KeyMap key : users.keySet()) {
            if ((date.getTime() - users.get(key).getTime()) > 5000) {
                list.add(key);
                isRemoved = true;
            }
        }
        for (KeyMap key : list) {
            users.remove(key);
        }
        if (isRemoved || isAdded) {
            printUsersList();
        }
    }
}
