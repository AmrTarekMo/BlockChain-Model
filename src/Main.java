import blockchain.BlockChain;
import blockchain.Transaction;
import blockchain.Wallet;
import blockchain.Utility;
import java.security.Security;


public class Main {

    public static void main(String[] args) {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Wallet wallet1 = new Wallet();
        Wallet wallet2 = new Wallet();

        System.out.println("Private and public keys:");
        System.out.println(Utility.getStringFromKey(wallet1.SK));
        System.out.println(Utility.getStringFromKey(wallet1.PK));
        //Create a test transaction from WalletA to walletB
        Transaction transaction = new Transaction(wallet1.PK, wallet2.PK, 5, null);
        transaction.generateSignature(wallet1.SK);
        //Verify the signature works and verify it from the public key
        System.out.println("Is signature verified");
        System.out.println(transaction.verifiySignature());
    }
}
