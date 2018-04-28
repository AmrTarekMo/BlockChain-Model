/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.util.ArrayList;
import com.google.gson.*;

/**
 *
 * @author GOOD
 */
public class BlockChain 
{
   public static ArrayList<Block> chain = new ArrayList();
   public static int difficulty = 6;
   
   public static boolean isValid()
   {
       String target = new String(new char[5]).replace('\0', '0'); //Create a string with difficulty * "0"
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
   public static void main(String[] args)
   {
       	String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        chain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        chain.get(0).mineBlock(difficulty);
   }
}
