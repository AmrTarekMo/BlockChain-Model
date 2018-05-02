package blockchain;

import java.security.*;
import java.util.*;

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

    public boolean processTransaction() {
        if(!verifiySignature()) {
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }

        //gather transaction inputs (Make sure they are unspent):
        for(TransactionInput i : inputs) {
            i.UTXO = BlockChain.UTXOs.get(i.transactionOutputId);
        }

        //check if transaction is valid:
        if(getInputsValue() < BlockChain.minimumTransaction) {
            System.out.println("#Transaction Inputs to small: " + getInputsValue());
            return false;
        }

        //generate transaction outputs:
        double leftOver = getInputsValue() - amount; //get amount of inputs then the left over change:
        transactionID = generatehash();
        outputs.add(new TransactionOutput( this.toPK, amount,transactionID)); //send amount to recipient
        outputs.add(new TransactionOutput( this.fromPK, leftOver,transactionID)); //send the left over 'change' back to sender

        //add outputs to Unspent list
        for(TransactionOutput o : outputs) {
            BlockChain.UTXOs.put(o.id , o);
        }

        //remove transaction inputs from UTXO lists as spent:
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue; //if Transaction can't be found skip it
            BlockChain.UTXOs.remove(i.UTXO.id);
        }
        return true;
    }
    //returns sum of inputs(UTXOs) amounts
    public double getInputsValue() {
        double total = 0;
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue; //if Transaction can't be found skip it
            total += i.UTXO.amount;
        }
        return total;
    }

    //returns sum of outputs:
    public double getOutputsValue() {
        double total = 0;
        for(TransactionOutput o : outputs) {
            total += o.amount;
        }
        return total;
    }
}
