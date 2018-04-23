import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block{
    String hash , prevHash = "" , data ;
    long timeStamp;

    Block(String prevHash , String data) throws NoSuchAlgorithmException {
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.prevHash = prevHash;
        this.hash = generateHash();
    }
    public String generateHash()throws NoSuchAlgorithmException{
        String input = data + prevHash + Long.toString(timeStamp);
        return SHA256(input);
    }
    public String SHA256(String message)throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA256");
        byte[] result = mDigest.digest(message.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length ; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100 ,16).substring(1));
        }
        return sb.toString();
    }
}
