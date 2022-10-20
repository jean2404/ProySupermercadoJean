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
import servicio.CompraServicio;

/**
 *
 * @author sagit
 */
public class CompraControl extends org.apache.struts.action.Action {
    private CompraPresentador comPre;
    private CompraServicio comser;

    public void setComser(CompraServicio comser) {
        this.comser = comser;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CompraFormulario f=(CompraFormulario)form;
        if(f.getAcc().equals("Nueva Compra")){
            comPre=new CompraPresentador();
            comPre.setFil(comser.nuevaCompra(f.getCodEmp()));
            request.getSession().setAttribute("comPre", comPre);
        }
        if(f.getAcc().equals("Listar Articulos")){
            comPre.setLis(comser.listarArticulos());
            return mapping.findForward("CompraListado");
        }
        if(f.getAcc().equals("Agregar Articulo")){
            comPre.setLis(comser.agregarArticulo(f.getCod(), f.getNom(), f.getPre(), f.getCan()));
        }
        if(f.getAcc().equals("Quitar Articulo")){
            comPre.setLis(comser.quitarArticulo(f.getCod()));
        }
        
        if(f.getAcc().equals("Buscar Proveedor")){
            Object[]fil=comser.buscarProveedor(f.getRuc());
            if(fil!=null){
                comPre.setFil2(fil);
            } else {
                comPre.setMsg("No existe el Proveedor");
            }
        }
        if(f.getAcc().equals("Grabar Compra")){
            comPre.setMsg(comser.grabarCompra(f.getNum(), f.getFec(), f.getTot(), f.getRuc(), f.getCodEmp()));
        }
               
        return mapping.findForward("CompraVista");
    }
}
