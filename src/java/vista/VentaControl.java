package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.VentaServicio;

public class VentaControl extends org.apache.struts.action.Action {

    private VentaPresentador venPre;
    private VentaServicio venser;

    public void setVenser(VentaServicio venser) {
        this.venser = venser;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        VentaFormulario f = (VentaFormulario) form;
        if (f.getAcc().equals("Nueva Venta")) {
            venPre = new VentaPresentador();
            venPre.setFil(venser.nuevaVenta(f.getCodEmp()));
            request.getSession().setAttribute("venPre", venPre);
        }
        if (f.getAcc().equals("Listar Articulos")) {
            venPre.setLis(venser.listarArticulos());
            return mapping.findForward("VentaListado");
        }
        if (f.getAcc().equals("Agregar Articulo")) {
            venPre.setLis(venser.agregarArticulo(f.getCod(), f.getNom(), f.getPre(), f.getCan()));
        }
        if (f.getAcc().equals("Quitar Articulo")) {
            venPre.setLis(venser.quitarArticulo(f.getCod()));
        }

        if (f.getAcc().equals("Buscar Cliente")) {
            Object[]fil=venser.buscarCliente(f.getDni());
            if(fil!=null){
                venPre.setFil2(fil);
            } else {
                venPre.setMsg("No existe el cliente");
            }
        }
        
        if (f.getAcc().equals("Grabar Venta")) {
            venPre.setMsg(venser.grabarVenta(f.getNum(), f.getFec(), f.getTot(), f.getDni(), f.getCodEmp()));
        }
        return mapping.findForward("VentaVista");
    }
}
