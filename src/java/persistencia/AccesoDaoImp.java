/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import negocio.EmpleadoObj;

/**
 *
 * @author sagit
 */
public class AccesoDaoImp implements AccesoDao {

    private EmpleadoJpaController ejc;

    public void setEjc(EmpleadoJpaController ejc) {
        this.ejc = ejc;
    }

    @Override
    public EmpleadoObj validar(String usu, String pas) {
        List lis = ejc.findEmpleadoEntities();
        if (lis != null) {
            for (int i = 0; i < lis.size(); i++) {
                Empleado emp = (Empleado) lis.get(i);
                if (emp.getUsu().equals(usu) && emp.getPas().equals(pas)) {
                    EmpleadoObj eo = new EmpleadoObj();
                    eo.setCod(emp.getCod());
                    eo.setNom(emp.getNom());
                    return eo;
                }
            }
        } 
        return null;
    }
    
  

}
