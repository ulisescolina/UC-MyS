/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author netbeans
 */
public class Controlador {
    private MetodoCongruencias mc;
    
    public Controlador() 
    {
        this.mc = new MetodoCongruencias();
    }

    public MetodoCongruencias getMc() {
        return mc;
    }

    public void setMc(MetodoCongruencias mc) {
        this.mc = mc;
    }
    
    
}
