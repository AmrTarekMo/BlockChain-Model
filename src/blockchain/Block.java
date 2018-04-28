package blockchain;

import java.util.Date;

public class Block extends Utility {
    public String hash, prevHash, data;
    public long nonce;
    private long timeStamp;

    public Block(String data,String prevHash ) {
        this.data = data;
        this.prevHash = prevHash;
	    this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
    public String calculateHash() {
        return applySha256(data+prevHash+nonce+timeStamp);
    }
            
}
