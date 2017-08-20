/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MattsonStack;

import MRC.MRC;
import MRC.TestMRC;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class MattsonTest {
    
    public static void main(String[] args) throws IOException, Exception{
        /* 
        Stack s = new Stack();
        
        Integer[][] cacheHits = new Integer[2][3];
        Integer[] r = {10,20,30};
        cacheHits[0] = r;
        System.out.println(cacheHits[0][2]);
        
        s.push(4);
        s.push(5);
        s.push(4);
        
        System.out.println(s.search(5));
        System.out.println(s.pop());
        */
        
        Scanner reader = new Scanner(System.in);
        System.out.println("WELCOME!!");
        System.out.println("Write the name of the Trace file, no extenstion!");
        String fileName = reader.next();
        
        
        File file = new File(fileName+".txt");
        MattsonStack mattson = new MattsonStack(file);
        
        
        
        TreeMap missRates =  mattson.Mrc();
        Set set = missRates.keySet();
        
        FileWriter fw;
        
        fw = new FileWriter(fileName+"MRC2.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        
        Iterator iter = set.iterator();
        int cacheSize = (int)iter.next();
        
        System.out.println(missRates);
        
        try{
            
            while(iter.hasNext()){
                cacheSize = (int)iter.next();
                bw.write("" + cacheSize + " " + missRates.get(cacheSize));
                bw.write("\n");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(TestMRC.class.getName()).log(Level.SEVERE, null, ex);
        }
        bw.close();
        fw.close();
        
    } 
    
        
        
        
        
}
    
