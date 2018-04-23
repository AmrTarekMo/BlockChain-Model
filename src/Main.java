public class Main {

    public static void main(String[] args) {

        BlockChain BC = new BlockChain();
        BC.AddBlock("im the second block");
        BC.AddBlock("im the third block");
        BC.AddBlock("Yo im the 4 block");
        BC.AddBlock("Hey im the 5 block");
        BC.printBlockChain();
        if(BC.isValid()){
            System.out.println("YES");
        }
        else
            System.out.println("NO");
    }
}
