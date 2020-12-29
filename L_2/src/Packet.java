import java.io.Serializable;

public class Packet implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int size;
    private byte[] data;
    private long fileSize;

    Packet(String name, int size, byte[] data, long fileSize) {
        this.name = name;
        this.size = size;
        this.data = data;
        this.fileSize = fileSize;
    }

    byte[] getData() {
        return data;
    }

    int getSize() { return size; }

    String getName() {
        return name;
    }

    long getFileSize() { return fileSize;}
}
