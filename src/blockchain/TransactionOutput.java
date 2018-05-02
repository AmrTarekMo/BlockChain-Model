package blockchain;

import java.security.*;

public class TransactionOutput extends Utility {
    public String id;
    public PublicKey toPK; //also known as the new owner of these coins.
    public double amount; //the amount of coins they own
    public String parentTransactionId; //the id of the transaction this output was created in

    //Constructor
    public TransactionOutput(PublicKey toPK, double amount, String parentTransactionId) {
        this.toPK = toPK;
        this.amount = amount;
        this.parentTransactionId = parentTransactionId;
        this.id = applySha256(getStringFromKey(toPK)+Double.toString(amount)+parentTransactionId);
    }

    //Check if coin belongs to you
    public boolean isMine(PublicKey publicKey) {
        return (publicKey == toPK);
    }
}
