/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRC;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class TestMRC {
    
    public static void main(String[] args) throws IOException{
        Scanner reader = new Scanner(System.in);
        
        System.out.println("Write the name of the Trace file, no extenstion!");
        String fileName = reader.next();
        fileName = fileName ;
        
        File file = new File(fileName+".txt");
        MRC mrc = new MRC(file);
        
        FileWriter fw;
        
        fw = new FileWriter(fileName+"MRC128.txt");
        
        
        
        BufferedWriter bw = new BufferedWriter(fw);
        String j;
        double[] missRates128;
        System.out.println(mrc.objetos);
        
        for(int x=0 ; x<mrc.trace.length; x++){
            System.out.println(mrc.trace[x]);
        }
        try {
            missRates128 = mrc.MRC128();
            
            for(int i =0 ; i < missRates128.length; i++){
                j = String.format("%1.7f",missRates128[i] );
                System.out.println(j);
                bw.write(j);
                bw.write("\n");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(TestMRC.class.getName()).log(Level.SEVERE, null, ex);
        }
        bw.close();
        fw.close();
        
    } 
    
}
