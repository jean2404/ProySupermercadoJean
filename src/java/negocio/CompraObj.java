/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sagit
 */
public class CompraObj {
    private List ces=new ArrayList();
    
    public void agregarLinea(String cod, String nom, double pre, int can) {
        LineaCompra lin = new LineaCompra(cod, nom, pre, can);
        ces.add(lin);
    }

    public void quitarLinea(String cod){
        for (int i = 0; i < ces.size(); i++) {
            LineaCompra lin=(LineaCompra)ces.get(i);
            if(lin.getCod().equals(cod)){
                ces.remove(i);
            }
        }
    }
    
    public double getTotal(){
        double tot=0;
        for (int i = 0; i < ces.size(); i++) {
            LineaCompra lin=(LineaCompra)ces.get(i);
            tot+=lin.getImporte();
        }
        return tot;
    }
    
    public List getCes() {
        return ces;
    }
    
    
}
