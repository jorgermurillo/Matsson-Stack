/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reuse.distance.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jorge
 */
public class ReuseDistanceTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner reader = new Scanner(System.in);
        String[] objetos = {"ab","bc","cd","de","ef","fg","gh"};
        System.out.println("Write the name of the file where the trace will be "
                + "written!");
        String fileName = reader.next();
        fileName = fileName + ".txt";
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        
        System.out.println("Enter the trace length!");
        int traceLength = reader.nextInt();
        
        System.out.println("Insert the seed for the Random Number Generator!");
        Random rn = new Random(reader.nextInt());
        
        int numeroRandom;
        
        
        
        //Escribir los objetos a referenciar en la primera linea
        /*
        for(int j=0; j < objetos.length-1;j++){
            bw.write(objetos[j] + " ");
            
        }
        bw.write(objetos[objetos.length-1]);
        
        bw.write("\n");
        */
        
        
        
        for(int i =0; i<traceLength; i++){
        
            
            numeroRandom = rn.nextInt(objetos.length);
            System.out.println(numeroRandom);
            bw.append(objetos[numeroRandom]);
            bw.append("\n");
            //ESTAS DOS LINEA ERAN SOLO UNA PRUEBA 
            //bw.write(String.valueOf(numeroRandom));
            //bw.write("\n");
        
        
        }
        
        bw.close();
        fw.close();
        System.out.println("Done!");
        
    }
    
}
