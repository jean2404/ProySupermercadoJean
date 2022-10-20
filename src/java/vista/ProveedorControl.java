package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.ProveedorServicio;

/**
 *
 * @author sagit
 */
public class ProveedorControl extends org.apache.struts.action.Action {
    
    private ProveedorPresentador provPre;
    private ProveedorServicio ps;
    
    public void setPs(ProveedorServicio ps) {
        this.ps = ps;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProveedorFormulario f = (ProveedorFormulario) form;
        if (f.getAcc().equals("Nuevo Mantenimiento")) {
            provPre = new ProveedorPresentador();
            request.getSession().setAttribute("provPre", provPre);
        }
        if (f.getAcc().equals("Grabar")) {
            provPre.setMsg(ps.grabarProveedor(f.getRuc(), f.getNom(), f.getDir()));
        }
        if (f.getAcc().equals("Buscar")) {
            Object[] fil = ps.buscarProveedor(f.getRuc());
            if (fil != null) {
                provPre.setFil(fil);
            } else {
                provPre.setMsg("No existe el proveedor");
            }
        }
        if (f.getAcc().equals("Actualizar")) {
            provPre.setMsg(ps.actualizarProveedor(f.getRuc(), f.getNom(), f.getDir()));
        }
        if (f.getAcc().equals("Eliminar")) {
            provPre.setMsg(ps.eliminarProveedor(f.getRuc()));
        }
        if (f.getAcc().equals("Limpiar")) {
            provPre.setMsg("");
            Object[]z={"","",""};
            provPre.setFil(z);
        }
        return mapping.findForward("ProveedorVista");
    }
}
