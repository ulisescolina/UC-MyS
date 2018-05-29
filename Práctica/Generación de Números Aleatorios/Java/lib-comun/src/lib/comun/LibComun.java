/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.comun;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author netbeans
 */
public class LibComun {

    public LibComun()
    {
        
    }
    
    public boolean escribirArchivo(Object[] elementos)
    {
        FileWriter fw = null;
        try {
            String home = System.getProperty("user.home");
            String ruta = home+File.separator+"generacion_aleatoria_"+System.currentTimeMillis()+".txt";
            System.out.println("Sistema: "+ruta);
            File f = new File(ruta);
            fw = new FileWriter(f);
            try (BufferedWriter escritura = new BufferedWriter(fw)) {
                for (Object elemento : elementos) {
                    escribir(escritura, (String) elemento);
                }
            }
            return true;
        } catch (IOException ex) {
            return false;
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                return false;
            }
        }
    }
    
    public void escribir (BufferedWriter e, String linea) {
        try {
            e.write(linea);
            e.newLine();
        } catch (IOException ex) {
            Logger.getLogger(LibComun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
