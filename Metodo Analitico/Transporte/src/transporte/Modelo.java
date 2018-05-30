/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transporte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import utilidades.Utilidades;

/**
 *
 * @author urc
 */
public class Modelo {
    private int V0, V1, V2, a, c, k, m, n, cantMC;
    private double intervaloMIN, intervaloMAX;
    private String metodo, probabilidades;
    ArrayList<Double> CARGA = new ArrayList<>();
    
    // Cadena a ser impresa en la pantalla resultado 
    private String CADENA_RESULTADO = "";
    
    /*
        Variables del modelo de Transporte
    */
    private int TOTC;
    private double[] VECS, VECM, VECP, VECPAC;
    
    
    // Variable global para frecuencias observadas
    private TreeMap<String, Integer>freqObservadas=new TreeMap<>();
    
    public Modelo(String metodo) {
        this.metodo = metodo;
    }
    
    public void set_parametros(int V0, int V1, int V2, int a, int c, int k, int m, int n, String probabilidades, int cantMC, double iMAX, double iMIN)
    {
        this.V0 = V0;
        this.V1 = V1;
        this.V2 = V2;
        this.a = a;
        this.c = c;
        this.k = k;
        this.m = m;
        this.n = n;
        this.probabilidades = probabilidades;
        this.cantMC = cantMC;
        this.intervaloMAX = iMAX;
        this.intervaloMIN = iMIN;
    }
    
    public void set_parametros_modelo()
    {
        this.VECM = Utilidades.getMarcasClase(this.cantMC, this.intervaloMIN, this.intervaloMAX);
        this.invertirVec(this.VECM);
        this.VECP = Utilidades.getProbabilidadesSob100(this.probabilidades);
        this.VECPAC = Utilidades.getProbabilidadesAcumuladas(this.VECP);
        this.VECS = this.generar_numeros_aleatorios_e_1_y_0();
    }
    
    public double[] generar_numeros_aleatorios_e_1_y_0()
    {
        double[] VECSAUX = new double[this.n];
        MetodosGNA MGNA = new MetodosGNA();
        ArrayList<Double> NAG = new ArrayList<>();
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
                NAG = MGNA.metodoMixto(this.V0, this.a, this.c, this.m, this.n);
                break;
            case "fibonacci":
                NAG = MGNA.metodoFibonacci(this.V1, this.V2, this.a, this.n);
                break;
            case "java":
                NAG = MGNA.metodoJavaRandom(this.n);
                break;
        }
        int i=0;
        Iterator<Double> it = NAG.iterator();
        while (it.hasNext()) {    
            double e = it.next();
            VECSAUX[i] = e;
            i++;
        }
        return VECSAUX;
    }
    
    public String simular()
    {
        this.set_parametros_modelo();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.VECPAC.length; j++) {
                if (this.VECS[i] < this.VECPAC[j]) {
                    this.TOTC += this.VECM[j];
                    this.CADENA_RESULTADO += "Peso "+i+": "+this.VECM[j] + "\n";
                    this.CARGA.add(this.VECM[j]);
                    break;
                }
            }
        }
        
        return this.CADENA_RESULTADO;
    }
    
    public String getFrecuenciasObservadas()
    {
        String fo="";
        // Define/Actualiza la variable global
        this.freqObservadas = Utilidades.getFrecuenciasObservadasTM(this.CARGA, this.VECM);
        Iterator<Entry<String, Integer>> it= this.freqObservadas.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> e = it.next();
            fo += "Marca de clase: "+e.getKey()+", Frecuencia observada: "+String.valueOf(e.getValue()+"\n");
        }
        return fo;
    }
    
    public void invertirVec(double[] array)
    {
        for (int i = 0; i < array.length / 2; i++) {
            double temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
    
    public void cadMCGraf()
    {
        double[] MC = this.VECM;
        String mc="";
        // Preparamos el String para las marcas de clase
        for (int i=0; i < MC.length-1; i++) {
            mc += String.valueOf(MC[i])+",";
        }
        mc += String.valueOf(MC[MC.length-1]);
        if (!Utilidades.escribirArchivo("marcas_de_clase", mc, false)) {
            System.err.println("No se pudo escribir el archivo.");
        }
    }
    
    public void fTeoricaGraf()
    {
        String ejey="";
        double[] probabilidades = this.VECP;
        double valor;
        for (int i = 0; i < probabilidades.length-1; i++) {
            valor = 0.0;
            valor = probabilidades[i]*this.n;
            ejey += String.valueOf(valor)+",";
        }
        valor = 0.0;
        valor = probabilidades[probabilidades.length-1]*this.n;
        ejey += String.valueOf(valor);
        if (!Utilidades.escribirArchivo("eje_y_t", ejey, false)) {
            System.err.println("No se pudo escribir el archivo.");
        }
    }
    
    public void fObservadaGraf()
    {
        Iterator<Entry<String, Integer>> it = this.freqObservadas.entrySet().iterator();
        String ejex="";
        double valor;
        while (it.hasNext()) {
            Entry<String, Integer> e= it.next();
            ejex += String.valueOf(e.getValue())+",";
        }
        // Quitamos el ultimo caracter (,) una coma
        ejex = ejex.substring(0, ejex.length() - 1);
        // Escribimos en el archivo, si ocurre un error nos avisa que no se 
        // pudo realizar la escritura
        if (!Utilidades.escribirArchivo("eje_y_o", ejex, false)) {
            System.err.println("No se pudo escribir el archivo.");
        }
    }
    
    // Getters y Setters

    public int getV0() {
        return V0;
    }

    public void setV0(int V0) {
        this.V0 = V0;
    }

    public int getV1() {
        return V1;
    }

    public void setV1(int V1) {
        this.V1 = V1;
    }

    public int getV2() {
        return V2;
    }

    public void setV2(int V2) {
        this.V2 = V2;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getCantMC() {
        return cantMC;
    }

    public void setCantMC(int cantMC) {
        this.cantMC = cantMC;
    }

    public double getIntervaloMIN() {
        return intervaloMIN;
    }

    public void setIntervaloMIN(double intervaloMIN) {
        this.intervaloMIN = intervaloMIN;
    }

    public double getIntervaloMAX() {
        return intervaloMAX;
    }

    public void setIntervaloMAX(double intervaloMAX) {
        this.intervaloMAX = intervaloMAX;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getProbabilidades() {
        return probabilidades;
    }

    public void setProbabilidades(String probabilidades) {
        this.probabilidades = probabilidades;
    }

    public ArrayList<Double> getCARGA() {
        return CARGA;
    }

    public void setCARGA(ArrayList<Double> CARGA) {
        this.CARGA = CARGA;
    }

    public String getCADENA_RESULTADO() {
        return CADENA_RESULTADO;
    }

    public void setCADENA_RESULTADO(String CADENA_RESULTADO) {
        this.CADENA_RESULTADO = CADENA_RESULTADO;
    }

    public int getTOTC() {
        return TOTC;
    }

    public void setTOTC(int TOTC) {
        this.TOTC = TOTC;
    }

    public double[] getVECS() {
        return VECS;
    }

    public void setVECS(double[] VECS) {
        this.VECS = VECS;
    }

    public double[] getVECM() {
        return VECM;
    }

    public void setVECM(double[] VECM) {
        this.VECM = VECM;
    }

    public double[] getVECP() {
        return VECP;
    }

    public void setVECP(double[] VECP) {
        this.VECP = VECP;
    }

    public double[] getVECPAC() {
        return VECPAC;
    }

    public void setVECPAC(double[] VECPAC) {
        this.VECPAC = VECPAC;
    }
    
}

