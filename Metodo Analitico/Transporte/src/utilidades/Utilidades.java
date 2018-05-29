/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;

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
    public double[] getMarcasClase(int cantMC, double intervaloMin, double intervaloMax)
    {
        double[] MC = new double[cantMC];
        double iMin = intervaloMin, iMax = intervaloMax;
        double intervalo = iMax - iMin;
        intervalo = intervalo/cantMC;
        
        MC[0] = iMin + (intervalo/2);
        System.out.println("Marca de clase " + 0 + ": " + MC[0]);
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
    public ArrayList<Double> getMarcasClaseAL(int cantMC, double intervaloMin, double intervaloMax)
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
    public double[] getProbabilidades(String prob)
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
     * @param prob arreglo de probabilidades ingresados por el usuario
     * @return arreglo de probabilidades acumuladas
     */
    public double[] getProbabilidadesAcumuladas(double[] prob)
    {
        double[] PAC = new double[prob.length];
        PAC[0]=prob[0];
        for (int i = 1 ; i < prob.length; i++) {
            PAC[1] = PAC[i-1] + prob[i];
        }
        return PAC;
    }
    
    // Utilidad para preparar archivo
    
    // Utilidad para escribir en el archivo

}
