import blockchain.BlockChain;

public class Main {

    public static void main(String[] args) {

        BlockChain BC = new BlockChain();
        BC.mineBlock("Genesis Block" , "0");
        BC.PrintChain();
        if(BC.isValid()){
            System.out.println("YES");
        }
        else
            System.out.println("NO");

    }
}
