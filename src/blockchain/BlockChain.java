package blockchain;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.*;

public class BlockChain {
    public static ArrayList<Block> chain = new ArrayList<>();
    public static HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>();
    public static int difficulty = 5;
    public static double minimumTransaction = 0.1f;

    public void PrintChain(){
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
        System.out.println(blockchainJson);
    }
    public boolean isValid() {
        String target = new String(new char[5]).replace('\0', '0');
        for(int i=0 ; i<chain.size()-1 ; i++) {
            Block current = chain.get(i+1), previous = chain.get(i);
            if(!current.hash.equals(current.calculateHash()) ) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!current.prevHash.equals(previous.hash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            if(!current.hash.substring( 0, difficulty).equals(target)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
