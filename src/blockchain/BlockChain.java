/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.util.ArrayList;
import com.google.gson.*;

public class BlockChain 
{
   public ArrayList<Block> chain;
   public static int difficulty = 6;
   
   
   public BlockChain()
   {
       chain=new ArrayList();
   }
   public boolean isValid()
   {
       String target = new String(new char[5]).replace('\0', '0'); 
       for(int i=0 ; i<chain.size()-1 ; i++)
       {
           Block current=chain.get(i+1), previous=chain.get(i);
           if(!current.hash.equals(current.calculateHash()) )
           {
               	System.out.println("Current Hashes not equal"); 
                return false;
           }

           if(!current.prevHash.equals(previous.hash))
           {
               	System.out.println("Previous Hashes not equal");
                return false;
           }
           
           if(!current.hash.substring( 0, difficulty).equals(target))
           {
		System.out.println("This block hasn't been mined");
		return false;
           }
       }
       
       return true;
   }
   
   public void mineBlock(int difficulty, String data, String prevHash) 
   {
        Block block=new Block(data,prevHash);
        String target = new String(new char[difficulty]).replace('\0', '0');
	while(!block.hash.substring( 0, difficulty).equals(target))
        { 
		block.nonce ++;
		block.hash = block.calculateHash();
        }
	System.out.println("Block Mined!!! : " + block.hash);
        chain.add(block);
   }
}
