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
import servicio.ArticuloServicio;

/**
 *
 * @author sagit
 */
public class ArticuloControl extends org.apache.struts.action.Action {

    private ArticuloPresentador artPre;
    private ArticuloServicio arts;

    public void setArts(ArticuloServicio arts) {
        this.arts = arts;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ArticuloFormulario f = (ArticuloFormulario) form;
        if (f.getAcc().equals("Nuevo Mantenimiento")) {
            artPre = new ArticuloPresentador();
            request.getSession().setAttribute("artPre", artPre);
        }
        if (f.getAcc().equals("Grabar")) {
            artPre.setMsg(arts.grabarArticulo(f.getCod(), f.getNom(), f.getPre(), f.getSto()));
        }
        if (f.getAcc().equals("Buscar")) {
            Object[] fil = arts.buscarArticulo(f.getCod());
            if (fil != null) {
                artPre.setFil(fil);
            } else {
                artPre.setMsg("No existe el articulo");
            }
        }
        if (f.getAcc().equals("Actualizar")) {
            artPre.setMsg(arts.actualizarArticulo(f.getCod(), f.getNom(), f.getPre(), f.getSto()));
        }
        if (f.getAcc().equals("Eliminar")) {
            artPre.setMsg(arts.eliminarArticulo(f.getCod()));
        }
        if (f.getAcc().equals("Limpiar")) {
            artPre.setMsg("");
            Object[] z = {"", "", "", ""};
            artPre.setFil(z);
        }
        return mapping.findForward("ArticuloVista");
    }
}
