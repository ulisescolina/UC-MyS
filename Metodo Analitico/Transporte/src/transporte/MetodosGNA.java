/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transporte;

import java.util.ArrayList;

/**
 *
 * @author urc
 */
public class MetodosGNA {    
    /**
     *
     * Relación fundamental Vi+1 = (a Vi + c Vi-k) mod m
     * @param a escalar que multiplica a Vi
     * @param c escalar que multiplica al segundo termino Vi-k
     * @param k escalar que representa la cantidad de espacios a crear antes de iniciar con los numeros aleatorios
     * @param m escalar que representa el techo para la generacion de numeros
     * @param n cantidad de numeros aleatorios a crear
     * @return
     */
    public ArrayList<Double> relacionFundamental(int a, int c, int k, int m, int n)
    {
        double numero=0.0, numero2=0.0;
        double[] auxNumerosAleatorios = new double[k+n];
        double[] numerosAleatorios = new double[n];
        // Se cargan los K valores necesarios al array para el funcionamiento del método
        for (int i = 0; i<k; i++) {
            auxNumerosAleatorios[i] = k+i;
        }
        
        // Se da inicio al metodo
        for (int i = k; i<k+n ; i++) {
            numero = ( a * auxNumerosAleatorios[i-1] + c * auxNumerosAleatorios[i-k]) % m;
            numero2 = numero/(double)(m-1);
            auxNumerosAleatorios[i] = numero2;
        }
        // Se copian todos los elementos del array a uno nuevo, sin copiar los K valores que se generaron al inicio del método
        System.arraycopy(auxNumerosAleatorios, k, numerosAleatorios, 0,numerosAleatorios.length);
        ArrayList<Double>NAG = new ArrayList<>();
        for (int i = 0; i < numerosAleatorios.length; i++) {
            NAG.add(numerosAleatorios[i]);
        }
        return NAG;
    }
    
    public ArrayList<Double> metodoAditivo(int k, int m, int n)
    {
        /*
        * caso especial del metodo fundamental en donde se tienen los escalares
        * igualados a 1, esto permite que se reutilice el codigo de la relacion
        * fundamental
        */
        return this.relacionFundamental(1, 1, k, m, n);
    }
    
    public ArrayList<Double> metodoMultiplicativo(int V0, int a, int m, int n)
    {
        int numero = 0;
        double[] numerosAleatorios = new double[n];
        double numero2 = 0.0;
        for (int i = 0; i<n ; i++) {
            numero = (a*V0) % m;   
            numero2 = (double)numero / (double)(m-1);
            V0 = numero;
            numerosAleatorios[i] = numero2;  
        }
        ArrayList<Double>NAG = new ArrayList<>();
        for (int i = 0; i < numerosAleatorios.length; i++) {
            NAG.add(numerosAleatorios[i]);
        }
        return NAG;
    }
    
    public ArrayList<Double> metodoMixto(int V0, int a, int c, int m, int n)
    {
        double[] numerosAleatorios = new double[n];
        int numero=0;
        double numero2=0.0;
        for (int i = 0; i<n ; i++) {
            /*
            * desde Filmina: el valor de "a" debe ser un entero impar y no
            * divisible por 3 o 5
            
            * Ademas establece ciertas condiciones para el valor Vi que no
            * termino de entender:
            * - 
            */
            
            numero = ( a * V0 + c) % m;
            numero2 = numero/(double)(m-1);
            V0 = numero;
            numerosAleatorios[i] = numero2;
        }
        
        ArrayList<Double>NAG = new ArrayList<>();
        for (int i = 0; i < numerosAleatorios.length; i++) {
            NAG.add(numerosAleatorios[i]);
        }
        return NAG;
    }
    
    public ArrayList<Double> metodoFibonacci(int V1, int V2, int a, int n)
    {
        int k;
        double[] NA = new double[n];
        // Seteo los dos primeros lugares con las semillas como dice el metdodo
        NA[0] = V1;
        NA[1] = V2;
        
        ArrayList<Double> NAG = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (NA[i-2]+NA[i-1] <= a) {
                k = 0;
            } else {
                k = -1;
            }
            NA[i] = NA[i-2]+NA[i-1]+(k*a);
            NAG.add(NA[i]);
        }
        return NAG;
    }
    
    
    public ArrayList<Double> metodoJavaRandom(int n)
    {
        ArrayList<Double> NAG = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double valor = Math.random();
            NAG.add(valor);
        }
        return NAG;
    }
}
