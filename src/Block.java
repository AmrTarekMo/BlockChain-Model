import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block{
    String hash , prevHash = "" , data ;
    long timeStamp;

    Block(String prevHash , String data , long timeStamp) throws NoSuchAlgorithmException {
        this.data = data;
        this.timeStamp = timeStamp;
        this.prevHash = prevHash;
        this.hash = generateHash(data + prevHash + Long.toString(timeStamp));
    }
    public String generateHash(String message)throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA256");
        byte[] result = mDigest.digest(message.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length ; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100 ,16).substring(1));
        }
        return sb.toString();
    }
}
