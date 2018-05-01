package blockchain;


import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;

public class Transaction extends Utility{

    public String transactionID;
    public PublicKey fromPK;
    public PublicKey toPK;
    public double amount;
    private static int sq = 0;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    public Transaction(PublicKey from , PublicKey to , double amount , ArrayList<TransactionInput> inputs){
        this.fromPK = from;
        this.toPK = to;
        this.amount = amount;
        this.inputs = inputs;
    }

    public String generatehash(){
        sq++;
        return applySha256(getStringFromKey(fromPK)+getStringFromKey(toPK)+Double.toString(amount)+Integer.toString(sq));
    }
    public void generateSignature(PrivateKey privateKey) {
        String data = getStringFromKey(fromPK) + getStringFromKey(toPK) + Double.toString(amount)	;
        signature = applyECDSASig(privateKey,data);
    }
    public boolean verifiySignature() {
        String data = getStringFromKey(fromPK) + getStringFromKey(toPK) + Double.toString(amount)	;
        return verifyECDSASig(fromPK, data, signature);
    }
}
