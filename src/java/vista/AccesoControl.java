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
import servicio.AccesoServicio;

/**
 *
 * @author sagit
 */
public class AccesoControl extends org.apache.struts.action.Action {

    private AccesoServicio as;

    public void setAs(AccesoServicio as) {
        this.as = as;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AccesoFormulario f = (AccesoFormulario) form;
        Object[] fil = as.validar(f.getUsu(), f.getPas());

        if (fil != null) {
            request.getSession().setAttribute("fil", fil);
            return mapping.findForward("Menu");
        } else {
            request.getSession().setAttribute("msg", "Acceso denegado");
            return mapping.findForward("Mensaje");
        }

    }
}
