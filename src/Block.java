import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block{
    String hash , prevHash = "" , data ;
    long timeStamp;

    Block(String prevHash , String data){
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.prevHash = prevHash;
        this.hash = generateHash();
    }
    public String generateHash(){
        String input = data + prevHash + Long.toString(timeStamp);
        return SHA256(input);
    }
    public String SHA256(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input,
            byte[] hash = digest.digest(message.getBytes("UTF-8"));

            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
