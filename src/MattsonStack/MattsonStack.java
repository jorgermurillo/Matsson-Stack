/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MattsonStack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import reuse.distance.test.CalculateReuseDistance;

/**
 *
 * @author Jorge
 */
public class MattsonStack {
    
    Stack stack = new Stack();
    String[] trace;
    int[] cacheSizes;
    Map<Integer,Integer> hits;
    
    
    public MattsonStack(File file){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            List<String> lista = new LinkedList<>();
            String tmp; 
                     
            //ESTA LINE SE NECESITA SOLO SI HAY UNA LINEA EXTRA EN EL TRACE         
            //tmp = br.readLine();
            
            
            while( (tmp = br.readLine())!= null ){
                lista.add(tmp);
            }
            
            
            this.trace = new String[lista.size()];
            
            trace= lista.toArray(new String[0]);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CalculateReuseDistance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalculateReuseDistance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
        
    public Map<Integer,Integer> Hits() throws Exception{
        
        if(trace==null){
            throw new Exception("The trace has not been set");
        }
        
        
        int stackDistance=0;
        
        int hitTmp=0;
        hits = new TreeMap<>();
        
        //El valor del key 0 equivale al valor de Hit(infinito)
        for(int traceIndex = 0; traceIndex < trace.length ; traceIndex++){
            
            //Buscar al objeto del trace q estamos  viendo y calcular su distan
            //cia desde el tope del stack, donde una distancia de -1 significa 
            //que el elemento no esta en el stack. (Enumera desde 1, no 0)!!!!
            stackDistance = stack.search(trace[traceIndex]);
            System.out.println(stack);
            
            stack.remove(trace[traceIndex]);
            
            
            stack.push(trace[traceIndex]);
            
            if(hits.containsKey( stackDistance )){
                hitTmp = hits.get(stackDistance);
                hits.replace(stackDistance, hitTmp+1);
            }else{
                hits.put(stackDistance, 1);
            }
            
            
            
            
        }
          
        System.out.println("WWAAASSS");
        System.out.println(hits);
        System.out.println("WWAAASSS");
        
        return hits;
    }    
    
    public TreeMap<Integer,Double> Mrc() throws Exception{
        
        this.hits = this.Hits();
        
        Set<Integer> set = hits.keySet();
        
        Integer[][] cacheHits = new Integer[2][set.size()];
        Integer[] keysArray = set.toArray(new Integer[0]);
        Arrays.sort(keysArray);
        cacheHits[0] = keysArray;
        
        //totalHits es el denominador de la formula
        int totalHits = 0;
        
        
        
        for(int i= 0;i<keysArray.length;i++){
            
            //cacheHits[0][x] tiene el arreglo de tamanos de cache
            //cacheHits[1][x] tiene el arreglo de hits
            cacheHits[1][i]= hits.get(cacheHits[0][i]);
            totalHits = totalHits + cacheHits[1][i];
         
        }
    
        double[] hitRates = new double[keysArray.length];
        
        //Empezar a calcular los hit rates
        int partialHits = 0;
        for(int j=1; j<keysArray.length; j++){
            
            
            partialHits = partialHits + cacheHits[1][j];
            
            hitRates[j]= 1.0 - ((double)partialHits)/totalHits;
            
        }
        
        TreeMap<Integer,Double> mapa = new TreeMap<>();
        
        for(int e =0; e <keysArray.length; e++){
            mapa.put(cacheHits[0][e], hitRates[e]);
        }
        System.out.println("HEY");
        //System.out.println(mapa);
        return mapa;
    }


    


}
