/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerosaleatorios;

/**
 *
 * @author netbeans
 */
public class Utilidades {
    public static String imprimir(Object[] s) 
    {
        String resultado = "[ ";
        for (int i = 0; i < s.length-1; i++) {
            resultado += s[i]+", ";
        }
        resultado += s[s.length-1]+" ]";
        return resultado;
    }
}
