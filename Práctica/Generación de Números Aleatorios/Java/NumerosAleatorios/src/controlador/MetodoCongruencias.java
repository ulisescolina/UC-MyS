/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.*;

/**
 *
 * @author netbeans
 */
public class MetodoCongruencias {
    public MetodoCongruencias()
    {
        
    }
    
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
    public Object[] relacionFundamental(int a, int c, int k, int m, int n)
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
        return numerosAleatorios;
    }
    
    public Object[] metodoAditivo(int k, int m, int n)
    {
        /*
        * caso especial del metodo fundamental en donde se tienen los escalares
        * igualados a 1, esto permite que se reutilice el codigo de la relacion
        * fundamental
        */
        return this.relacionFundamental(1, 1, k, m, n);
    }
    
    public Object[] metodoMultiplicativo(int V0, int a, int m, int n)
    {
        String[] numerosAleatorios = new String[n];
        int auxV0 = V0;
        boolean control = true;
        /* 
            la filmina dice que es posible imponer condiciones iniciales al valor inicial V0
            aunque no explica cuales son las pautas para establecer estas condiciones iniciales
        
            La que establezco es que no sea divisible por 2 ni por 5.
        */
        while (control) {
            if (V0%2 == 0 || V0%5==0) {
                auxV0++;
            } else {
                numerosAleatorios[0] = String.valueOf(auxV0);
                control = false;
            }
        }
        
        for (int i = 1; i<n ; i++) {
            int numero;
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
        return numerosAleatorios;
    }
    
    public Object[] metodoMixto(int V0, int a, int c, int k, int m, int n)
    {
        String[] numerosAleatorios = new String[n];
        int auxV0 = V0;
        boolean control = true;
        numerosAleatorios[0] = String.valueOf(auxV0);
        
        
        for (int i = 1; i<n ; i++) {
            int numero;
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
        return numerosAleatorios;   
    }
}
