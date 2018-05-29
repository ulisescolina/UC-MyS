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
    public ArrayList<Integer> relacionFundamental(int a, int c, int k, int m, int n)
    {
        String[] auxNumerosAleatorios = new String[k+n];
        String[] numerosAleatorios = new String[n];
        // Se cargan los K valores necesarios al array para el funcionamiento del método
        for (int i = 0; i<k; i++) {
            auxNumerosAleatorios[i] = String.valueOf(k+i);
        }
        
        // Se da inicio al metodo
        for (int i = k; i<k+n ; i++) {
            int numero;
            numero = ( a * Integer.parseInt(auxNumerosAleatorios[i-1]) + c * Integer.parseInt(auxNumerosAleatorios[i-k])) % m;
            auxNumerosAleatorios[i] = String.valueOf(numero);
        }
        // Se copian todos los elementos del array a uno nuevo, sin copiar los K valores que se generaron al inicio del método
        System.arraycopy(auxNumerosAleatorios, k, numerosAleatorios, 0,numerosAleatorios.length);
        ArrayList<Integer>NAG = new ArrayList<>();
        for (int i = 0; i < numerosAleatorios.length; i++) {
            NAG.add(Integer.parseInt(numerosAleatorios[i]));
        }
        return NAG;
    }
    
    public ArrayList<Integer> metodoAditivo(int k, int m, int n)
    {
        /*
        * caso especial del metodo fundamental en donde se tienen los escalares
        * igualados a 1, esto permite que se reutilice el codigo de la relacion
        * fundamental
        */
        return this.relacionFundamental(1, 1, k, m, n);
    }
    
    public ArrayList<Integer> metodoMultiplicativo(int V0, int a, int m, int n)
    {
        String[] numerosAleatorios = new String[n];
        int auxV0 = V0;
        boolean control = true;
        /* 
            la filmina dice que es posible imponer condiciones iniciales al valor inicial V0
            aunque no explica cuales son las pautas para establecer estas condiciones iniciales
        
            La que establezco es que no sea divisible por 2 ni por 5.
        */
        
        for (int i = 1; i<n ; i++) {
            int numero, numero2;
            /*
            * La filmina da una explicacion de como obtener el escalar "a"
            * no termino de entender que quiere que se haga para obtener
            * el resultado de dicho escalar
            
            * Indagar ademas sobre las condiciones para el valor Vi que no
            * se entiende demasiado
            */
            
            numero = ( a * Integer.parseInt(numerosAleatorios[i-1])) % m;
            numerosAleatorios[i] = String.valueOf(numero);  
        }
        
        ArrayList<Integer>NAG = new ArrayList<>();
        for (int i = 0; i < numerosAleatorios.length; i++) {
            NAG.add(Integer.parseInt(numerosAleatorios[i]));
        }
        return NAG;
    }
    
    public ArrayList<Integer> metodoMixto(int V0, int a, int c, int k, int m, int n)
    {
        String[] numerosAleatorios = new String[n];
        int auxV0 = V0;
        boolean control = true;
        numerosAleatorios[0] = String.valueOf(auxV0);
        
        
        for (int i = 1; i<n ; i++) {
            int numero=0;
            /*
            * desde Filmina: el valor de "a" debe ser un entero impar y no
            * divisible por 3 o 5
            
            * Ademas establece ciertas condiciones para el valor Vi que no
            * termino de entender:
            * - 
            */
            
            numero = ( a * Integer.parseInt(numerosAleatorios[i-1]) + c) % m;
            numerosAleatorios[i] = String.valueOf(numero);
        }
        
        ArrayList<Integer>NAG = new ArrayList<>();
        for (int i = 0; i < numerosAleatorios.length; i++) {
            NAG.add(Integer.parseInt(numerosAleatorios[i]));
        }
        return NAG;
    }
    
    public ArrayList<Integer> metodoFibonacci(int V1, int V2, int a, int n)
    {
        int k;
        int[] NA = new int[n];
        // Seteo los dos primeros lugares con las semillas como dice el metdodo
        NA[0] = V1;
        NA[1] = V2;
        
        ArrayList<Integer> NAG = new ArrayList<>();
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
}
