/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.util.Date;
import blockchain.Utility.*;
import com.google.gson.*;

/**
 *
 * @author GOOD
 */
public class Block extends Utility
{

    public String hash, prevHash, data; 
    private long timeStamp,nonce; //in milliseconds.

    
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
    
    public void mineBlock(int difficulty) 
    {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target))
                {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
    }
            
}
