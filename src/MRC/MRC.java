/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import reuse.distance.test.CalculateReuseDistance;

/**
 *
 * @author Jorge
 */
public class MRC {
 
    String[] trace;
    //CAMBIAR EL 
    String tnp;
    String[] objetos;
    int[] cacheSizes;
    
    public MRC(File file){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            List<String> lista = new LinkedList<>();
            String tmp; 
            
            
            tnp = br.readLine();
            
            objetos = tnp.split(" ");
            
            
            
            while( (tmp = br.readLine())!= null ){
                lista.add(tmp);
            }
            
            Iterator iter = lista.iterator();
            this.trace = new String[lista.size()];
            int index = 0;
            while(iter.hasNext()){
                trace[index] = (String)iter.next() ;
                index++;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CalculateReuseDistance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalculateReuseDistance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public double[] MRC128() throws Exception{
        
        if(trace==null){
            throw new Exception("The trace has not been set");
        }
        //2 4 8 16 32 64 128
        int[] cacheSizesTmp = {2,4,8,16,32,64,128};
        this.cacheSizes = cacheSizesTmp;
        
        int[] misses = new int[cacheSizes.length];        
        
        
        //Initialize the misses array to zero
        Arrays.fill(misses, 0);
        
        int[] distances = new int[this.objetos.length];
                     
        //Initialize distances to zero
        Arrays.fill(distances, 0);
        
        int distanceTmp =0;
        int x=0;
        //Iterate over the trace array
        for(int traceIndex=0; traceIndex< this.trace.length; traceIndex++){
            //Iterate over the distances array to update the value
            for(int distanceIndex = 0 ; distanceIndex < distances.length; distanceIndex++){
                
                if(objetos[distanceIndex]!= trace[traceIndex]){
                    distances[distanceIndex] = distances[distanceIndex] +1;
                }else{
                    
                    //Save the reuse distance of the object we are looking at 
                    //from the trace.
                    distanceTmp = distances[distanceIndex];
                    //iterate over the cacheSizes array to add a miss to the 
                    // misses array.
                    
                    x = 0;
                    //Update the misses for the appropiate caches sizes
                    while(x <= cacheSizes.length && cacheSizes[x]<=distanceTmp  ){
                        
                        misses[x] = misses[x]+1;
                                                
                        x++;
                    }
                    
                    //Set the reuse distance of the object back to zero.
                    distances[distanceIndex] = 0;
                    
                }
            }
         
         
        }
        
        double[] missRates = new double[misses.length];
        for(int k = 0; k < missRates.length; k++){
            missRates[k] = ((double)misses[k])/trace.length;
        }
        
        return missRates;
    }
    
}
