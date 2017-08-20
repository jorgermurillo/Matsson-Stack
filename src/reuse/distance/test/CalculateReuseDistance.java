/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reuse.distance.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class CalculateReuseDistance {

    char[] trace;
    String objetos;
    
    
    public CalculateReuseDistance(File file){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            List<String> lista = new LinkedList<>();
            String tmp; 
            
            objetos = br.readLine();
            
            
            
            
            while( (tmp = br.readLine())!= null ){
                lista.add(tmp);
            }
            
            Iterator iter = lista.iterator();
            this.trace = new char[lista.size()];
            int index = 0;
            while(iter.hasNext()){
                trace[index] = ((String)iter.next()).charAt(0) ;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CalculateReuseDistance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalculateReuseDistance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public int[] MRC128( String filename){
        //2 4 8 16 32 64 128
        int[] cacheSizes = {2,4,8,16,32,64,128};
        
        int[] MissRates = new int[cacheSizes.length];        
        
        int[] distances = new int[this.objetos.length()];
        
        //Initialize distances to zero
        
        for(int x=0; x< distances.length; x++){
            
        }
        
        return null;
    }
    
}
