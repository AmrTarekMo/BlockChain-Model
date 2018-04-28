/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.util.Date;
import blockchain.Utility.*;


public class Block extends Utility
{

    public String hash, prevHash, data;
    public long nonce;
    private long timeStamp; //in milliseconds.

    
    public Block(String data,String prevHash )
    {
        this.data = data;
        this.prevHash = prevHash;
	this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
    
    public String calculateHash()
    {
        return applySha256(data+prevHash+nonce+timeStamp);
    }
    
   
            
}
