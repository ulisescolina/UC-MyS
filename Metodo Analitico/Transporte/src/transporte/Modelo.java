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
public class Modelo {
    private int V0, V1, V2, a, c, k, m, n, cantMC;
    private double intervaloMin, intervaloMAX;
    private String metodo;
    
    /*
        Variables del modelo de Transporte
    */
    private int TOTC;
    private double[] VECM, VECP, VECPAC;
    
    public Modelo(String metodo) {
        this.metodo = metodo;
    }
    
    public void set_parametros(int V0, int V1, int V2, int a, int c, int k, int m, int n)
    {
        this.V0 = V0;
        this.V1 = V1;
        this.V2 = V2;
        this.a = a;
        this.c = c;
        this.k = k;
        this.m = m;
        this.n = n;
    }
    
    public void generar_numeros_aleatorios_e_1_y_0()
    {
        MetodosGNA MGNA = new MetodosGNA();
        ArrayList<Integer> NAG = new ArrayList<>();
        switch (this.metodo.toLowerCase()) {
            case "fundamental":
                NAG = MGNA.relacionFundamental(this.a, this.c, this.k, this.m, this.n);
                break;
            case "aditivo":
                NAG = MGNA.metodoAditivo(this.k, this.m, this.n);
                break;
            case "multiplicativo":
                NAG = MGNA.metodoMultiplicativo(this.V0, this.a, this.m, this.n);
                break;
            case "mixto":
                NAG = MGNA.metodoMixto(this.V0, this.a, this.c, this.k, this.m, this.n);
                break;
            case "fibonacci":
                NAG = MGNA.metodoFibonacci(this.V1, this.V2, this.a, this.n);
                break;
        }
        
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
    
    
    
}

