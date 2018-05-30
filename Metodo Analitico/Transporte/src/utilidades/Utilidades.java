/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author urc
 */
public class Utilidades {
    /**
     * Calcula el valor de las marcas de clase para un intervalo.
     * 
     * @param cantMC cantidad de marcas de clase que se van a calcular para un cierto intervalo
     * @param intervaloMin valor minimo del intervalo para el cual se van a calcular las marcas de clase
     * @param intervaloMax valor maximo del intervalo para el cual se van a calcular las marcas de clase
     * @return arreglo de valores que representan las marcas de clase
     */
    public static double[] getMarcasClase(int cantMC, double intervaloMin, double intervaloMax)
    {
        double[] MC = new double[cantMC];
        double iMin = intervaloMin, iMax = intervaloMax;
        double intervalo = iMax - iMin;
        intervalo = intervalo/cantMC;
        
        MC[0] = iMin + (intervalo/2);
        for (int i = 1; i < cantMC; i++) {
            MC[i] = MC[i-1]+intervalo;
        }
        return MC;
    }
    
    /**
     * Calcula el valor de las marcas de clase para un intervalo.
     * 
     * @param cantMC cantidad de marcas de clase que se van a calcular para un cierto intervalo
     * @param intervaloMin valor minimo del intervalo para el cual se van a calcular las marcas de clase
     * @param intervaloMax valor maximo del intervalo para el cual se van a calcular las marcas de clase
     * @return coleccion de valores que representan las marcas de clase
     */
    public static ArrayList<Double> getMarcasClaseAL(int cantMC, double intervaloMin, double intervaloMax)
    {
        ArrayList<Double> MC = new ArrayList<>();
        double[] auxMC = new double[cantMC];
        double iMin = intervaloMin, iMax = intervaloMax;
        double intervalo = iMax - iMin;
        intervalo = intervalo/cantMC;
        
        auxMC[0] = iMin + (intervalo/2);
        for (int i = 1; i < cantMC; i++) {
            auxMC[i] = auxMC[i-1]+intervalo;
            MC.add(auxMC[i]);
        }
        return MC;
    }
    
    // split lista probabilidades

    /**
     *
     * @param prob lista de probabilidades en formato cadena en la cual cada linea representa una probabilidad
     * @return arreglo de probabilidades
     */
    public static double[] getProbabilidades(String prob)
    {
        double[] probabilidades = new double[prob.split("\n").length];
        String[][] cadSplit = new String[prob.split("\n").length][2];
        for (int i=0; i<cadSplit.length; i++) {
            cadSplit[i] = prob.split("\n")[i].split("-");
            probabilidades[i] = Double.parseDouble(cadSplit[i][1]);
        }
        return probabilidades;
    }
    
    /**
     *
     * @param prob lista de probabilidades en formato cadena en la cual cada linea representa una probabilidad
     * @return arreglo de probabilidades
     */
    public static double[] getProbabilidadesSob100(String prob)
    {
        double[] probabilidades = new double[prob.split("\n").length];
        String[][] cadSplit = new String[prob.split("\n").length][2];
        for (int i=0; i<cadSplit.length; i++) {
            cadSplit[i] = prob.split("\n")[i].split("-");
            probabilidades[i] = Double.parseDouble(cadSplit[i][1])/(double)100.0;
        }
        return probabilidades;
    }
    
    /**
     *
     * @param prob arreglo de probabilidades ingresados por el usuario
     * @return arreglo de probabilidades acumuladas
     */
    public static double[] getProbabilidadesAcumuladas(double[] prob)
    {
        double[] PAC = new double[prob.length];
        PAC[0]=prob[0];
        for (int i = 1 ; i < prob.length; i++) {
            PAC[i] = PAC[i-1] + prob[i];
        }
        return PAC;
    }
    
    // Utilidad para preparar archivo

    /**
     * Escribe cadenas en un archivo
     * @param nArchivo nombre del archivo que va ser generado por la funcion
     * @param paraEscribir cadena a ser escrita en el archivo
     * @param agregar   TRUE: agrega el texto al final del archivo
     *                  FALSE: sobre-escribe todo el archivo
     * @return  TRUE: procedimiento exitoso
     *          FALSE: procedimiento fallido
     */
    public static boolean escribirArchivo(String nArchivo, String paraEscribir, boolean agregar)
    {
        String home = System.getProperty("user.home");
        File archivo=new File(home+File.separator+nArchivo);
        try {
            if (archivo.exists()) {
                archivo.delete();
            }
            archivo.createNewFile();
            FileWriter f2 = new FileWriter(archivo, agregar);
            f2.write(paraEscribir);
            f2.close();
            return true;
        } catch (IOException e) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, e);   
            return false;
        }
    }
    
    /**
     * Calcula la frecuencia observada para cada marca de clase, pero devuelve las frecuencias ordenadas por marcas de clase
     * @param CARGA coleccion que representa las marcas de clase generadas para la simulacion
     * @return coleccion tipo <K, V> que representa la Marca de clase y la cantidad de veces que esta aparece en la coleccion
     */
    public static TreeMap<String, Integer> getFrecuenciasObservadasTM(ArrayList<Double> CARGA, double[] mc)
    {
        TreeMap<String, Integer>fObservadas = new TreeMap<>();
        Iterator<Double> it = CARGA.iterator();
        while (it.hasNext()) {
            double e = it.next();
            String MC = String.valueOf(e);
            if (!fObservadas.containsKey(MC)) {
                fObservadas.put(MC, 1);
            } else {
                int contador = fObservadas.get(String.valueOf(e));
                contador++;
                fObservadas.put(MC, contador);
            }
        }
        for (int i=0; i<mc.length; i++) {
            // corroboro si alguna marca de clase no esta contenida en la coleccion
            if (!fObservadas.containsKey(String.valueOf(mc[i]))) {
                // si no esta contenida la cargo, y digo que la frecuencia observada de la misma es de cero.
                fObservadas.put(String.valueOf(mc[i]), 0);
            }
        }
        return fObservadas;
    }
    
    public static String imprimirArray(double[] a) 
    {
        String array = "";
        for (int i=0; i< a.length; i++) {
            array += i+" - " + a[i] + "\n";
        }
        return array;
    }
}
