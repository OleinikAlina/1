import java.net.InetAddress;

public class KeyMap {
    private InetAddress address;
    private String userUid;
    public KeyMap(InetAddress address,String userUid){
        this.address=address;
        this.userUid=userUid;
    }

    public InetAddress getAddress(){
        return address;
    }

    public String getUserUid(){
        return userUid;
    }
}
