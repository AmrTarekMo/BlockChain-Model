package blockchain;

import java.util.ArrayList;
import java.util.Date;

public class Block extends Utility {
    public String hash, prevHash, merkleRoot;
    public long nonce;
    private long timeStamp;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public Block(String prevHash ) {
        this.prevHash = prevHash;
	    this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
    public String calculateHash() {
        return applySha256(merkleRoot+prevHash+Long.toString(nonce)+Long.toString(timeStamp));
    }

    //Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        merkleRoot = getMerkleRoot(transactions);
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    //Add transactions to this block
    public boolean addTransaction(Transaction transaction) {
        //process transaction and check if valid, unless block is genesis block then ignore.
        if(transaction == null) return false;
        if((prevHash != "0") && (!transaction.processTransaction())) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}
