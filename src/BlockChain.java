import java.util.ArrayList;
//import com.google.gson.GsonBuilder;

public class BlockChain {
    ArrayList<Block> blockchain;

    public BlockChain(){
        blockchain.add(genesisBlock());
    }
    public Block genesisBlock(){
        Block genesis = new Block("0","Genesis Block");
        return genesis;
    }
    public void AddBlock(String Data){
        Block last = blockchain.get(blockchain.size()-1);
        Block temp = new Block(last.hash,Data);
        blockchain.add(temp);
    }
    public void printBlockChain(){
    /*    String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    */
        for (int i = 0; i < blockchain.size() ; i++) {
            System.out.println(blockchain.get(i).hash + "  " + blockchain.get(i).data + "  " +blockchain.get(i).timeStamp + "  ");
        }
    }
    public boolean isValid(){
        Block cur , prev;
        for(int i=1 ; i<blockchain.size() ; i++){
            prev = blockchain.get(i-1);
            cur = blockchain.get(i);
            if(cur.prevHash != prev.hash)
                return false;
        }
        return true;
    }
}
