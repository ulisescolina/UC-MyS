/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashMap;

/**
 *
 * @author urc
 */
public final class Parametro {
    private HashMap<String, Boolean> PARAMETROS_FUNDAMENTAL = new HashMap<>();
    private HashMap<String, Boolean> PARAMETROS_ADITIVO = new HashMap<>();
    private HashMap<String, Boolean> PARAMETROS_MULTIPLICATIVO = new HashMap<>();
    private HashMap<String, Boolean> PARAMETROS_MIXTO = new HashMap<>();
    private HashMap<String, Boolean> PARAMETROS_FIBONACCI = new HashMap<>();
    private HashMap<String, Boolean> PARAMETROS_JAVARANDOM = new HashMap<>();
        
    public Parametro(){
        this.setPARAMETROS_FUNDAMENTAL();
        this.setPARAMETROS_ADITIVO();
        this.setPARAMETROS_MULTIPLICATIVO();
        this.setPARAMETROS_MIXTO();
        this.setPARAMETROS_FIBONACCI();        
        this.setPARAMETROS_JAVARANDOM();        
    }
    

    // Setter de todos los parametros
    public void setPARAMETROS_FUNDAMENTAL() {
        this.PARAMETROS_FUNDAMENTAL.put("v0",false);
        this.PARAMETROS_FUNDAMENTAL.put("v1",false);
        this.PARAMETROS_FUNDAMENTAL.put("v2",false);
        this.PARAMETROS_FUNDAMENTAL.put("a",true);
        this.PARAMETROS_FUNDAMENTAL.put("c",true);
        this.PARAMETROS_FUNDAMENTAL.put("k",true);
        this.PARAMETROS_FUNDAMENTAL.put("m",true);
        this.PARAMETROS_FUNDAMENTAL.put("n",true);
    }

    public void setPARAMETROS_ADITIVO() {
        this.PARAMETROS_ADITIVO.put("v0",false);
        this.PARAMETROS_ADITIVO.put("v1",false);
        this.PARAMETROS_ADITIVO.put("v2",false);
        this.PARAMETROS_ADITIVO.put("a",false);
        this.PARAMETROS_ADITIVO.put("c",false);
        this.PARAMETROS_ADITIVO.put("k",true);
        this.PARAMETROS_ADITIVO.put("m",true);
        this.PARAMETROS_ADITIVO.put("n",true);
    }

    public void setPARAMETROS_MULTIPLICATIVO() {
        this.PARAMETROS_MULTIPLICATIVO.put("v0",true);
        this.PARAMETROS_MULTIPLICATIVO.put("v1",false);
        this.PARAMETROS_MULTIPLICATIVO.put("v2",false);
        this.PARAMETROS_MULTIPLICATIVO.put("a",true);
        this.PARAMETROS_MULTIPLICATIVO.put("c",false);
        this.PARAMETROS_MULTIPLICATIVO.put("k",false);
        this.PARAMETROS_MULTIPLICATIVO.put("m",true);
        this.PARAMETROS_MULTIPLICATIVO.put("n",true);
    }

    public void setPARAMETROS_MIXTO() {
        this.PARAMETROS_MIXTO.put("v0",true);
        this.PARAMETROS_MIXTO.put("v1",false);
        this.PARAMETROS_MIXTO.put("v2",false);
        this.PARAMETROS_MIXTO.put("a",true);
        this.PARAMETROS_MIXTO.put("c",true);
        this.PARAMETROS_MIXTO.put("k",false);
        this.PARAMETROS_MIXTO.put("m",true);
        this.PARAMETROS_MIXTO.put("n",true);
    }
    
    public void setPARAMETROS_FIBONACCI() {
        this.PARAMETROS_FIBONACCI.put("v0",false);
        this.PARAMETROS_FIBONACCI.put("v1",true);
        this.PARAMETROS_FIBONACCI.put("v2",true);
        this.PARAMETROS_FIBONACCI.put("a",true);
        this.PARAMETROS_FIBONACCI.put("c",false);
        this.PARAMETROS_FIBONACCI.put("k",false);
        this.PARAMETROS_FIBONACCI.put("m",false);
        this.PARAMETROS_FIBONACCI.put("n",true);
    }
    
    public void setPARAMETROS_JAVARANDOM() {
        this.PARAMETROS_JAVARANDOM.put("v0",false);
        this.PARAMETROS_JAVARANDOM.put("v1",false);
        this.PARAMETROS_JAVARANDOM.put("v2",false);
        this.PARAMETROS_JAVARANDOM.put("a",false);
        this.PARAMETROS_JAVARANDOM.put("c",false);
        this.PARAMETROS_JAVARANDOM.put("k",false);
        this.PARAMETROS_JAVARANDOM.put("m",false);
        this.PARAMETROS_JAVARANDOM.put("n",true);
    }

    // Getter de todos los parametros
    public HashMap<String, Boolean> getPARAMETROS_FUNDAMENTAL() {
        return PARAMETROS_FUNDAMENTAL;
    }

    public HashMap<String, Boolean> getPARAMETROS_ADITIVO() {
        return PARAMETROS_ADITIVO;
    }

    public HashMap<String, Boolean> getPARAMETROS_MULTIPLICATIVO() {
        return PARAMETROS_MULTIPLICATIVO;
    }

    public HashMap<String, Boolean> getPARAMETROS_MIXTO() {
        return PARAMETROS_MIXTO;
    }

    public HashMap<String, Boolean> getPARAMETROS_FIBONACCI() {
        return PARAMETROS_FIBONACCI;
    }
    
    public HashMap<String, Boolean> getPARAMETROS_JAVARANDOM() {
        return PARAMETROS_JAVARANDOM;
    }

}
