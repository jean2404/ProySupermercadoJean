/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.EmpleadoServicio;

/**
 *
 * @author sagit
 */
public class EmpleadoControl extends org.apache.struts.action.Action {

    private EmpleadoPresentador empPre;
    private EmpleadoServicio es;

    public void setEs(EmpleadoServicio es) {
        this.es = es;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EmpleadoFormulario f = (EmpleadoFormulario) form;
        if (f.getAcc().equals("Nuevo Mantenimiento")) {
            empPre = new EmpleadoPresentador();
            request.getSession().setAttribute("empPre", empPre);
        }
        if (f.getAcc().equals("Grabar")) {
            empPre.setMsg(es.grabarEmpleado(f.getCod(), f.getNom(), f.getTip(), f.getUsu(), f.getPas()));
        }
        if (f.getAcc().equals("Buscar")) {
            Object[] fil = es.buscarEmpleado(f.getCod());
            if (fil != null) {
                empPre.setFil(fil);
            } else {
                empPre.setMsg("No existe el empleado");
            }
        }
        if (f.getAcc().equals("Actualizar")) {
            empPre.setMsg(es.actualizarProveedor(f.getCod(), f.getNom(), f.getTip(), f.getUsu(), f.getPas()));
        }
        if (f.getAcc().equals("Eliminar")) {
            empPre.setMsg(es.eliminarProveedor(f.getCod()));
        }
        if (f.getAcc().equals("Limpiar")) {
            empPre.setMsg("");
            Object[] z = {"", "", "", "", ""};
            empPre.setFil(z);
        }
        return mapping.findForward("EmpleadoVista");
    }
}
