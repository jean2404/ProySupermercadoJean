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
import servicio.ClienteServicio;

/**
 *
 * @author sagit
 */
public class ClienteControl extends org.apache.struts.action.Action {
    
    private ClientePresentador cliPre;
    private ClienteServicio cs;
    
    public void setCs(ClienteServicio cs) {
        this.cs = cs;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ClienteFormulario f = (ClienteFormulario) form;
        if (f.getAcc().equals("Nuevo Mantenimiento")) {
            cliPre = new ClientePresentador();
            request.getSession().setAttribute("cliPre", cliPre);
        }
        if (f.getAcc().equals("Grabar")) {
            cliPre.setMsg(cs.grabarCliente(f.getDni(), f.getNom(), f.getDir()));
        }
        if (f.getAcc().equals("Buscar")) {
            Object[] fil = cs.buscarCliente(f.getDni());
            if (fil != null) {
                cliPre.setFil(fil);
            } else {
                cliPre.setMsg("No existe el cliente");
            }
        }
        if (f.getAcc().equals("Actualizar")) {
            cliPre.setMsg(cs.actualizarCliente(f.getDni(), f.getNom(), f.getDir()));
        }
        if (f.getAcc().equals("Eliminar")) {
            cliPre.setMsg(cs.eliminarCliente(f.getDni()));
        }
        if (f.getAcc().equals("Limpiar")) {
            cliPre.setMsg("");
            Object[]z={"","",""};
            cliPre.setFil(z);
        }
        return mapping.findForward("ClienteVista");
    }
}
