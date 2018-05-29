/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerosaleatorios;

import controlador.*;
import GUI.*;

/**
 *
 * @author netbeans
 */
public class NumerosAleatorios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controlador c = new Controlador();
        MyS mys = new MyS(c);
        mys.setVisible(true);
    }
    
}
