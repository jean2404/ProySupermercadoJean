/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import negocio.Proveedor;
import persistencia.ProveedorDao;

/**
 *
 * @author sagit
 */
public class ProveedorServicioImp implements ProveedorServicio {

    private ProveedorDao pd;

    public void setPd(ProveedorDao pd) {
        this.pd = pd;
    }

    @Override
    public String grabarProveedor(String ruc, String nom, String dir) {
        Proveedor prov = new Proveedor(ruc, nom, dir);
        return pd.grabar(prov);
    }

    @Override
    public Object[] buscarProveedor(String ruc) {
        Proveedor prov = pd.buscar(ruc);
        if (prov != null) {
            Object[] fil = new Object[3];
            fil[0] = prov.getRuc();
            fil[1] = prov.getNom();
            fil[2] = prov.getDir();
            return fil;
        }
        return null;
    }

    @Override
    public String actualizarProveedor(String ruc, String nom, String dir) {
        Proveedor prov = new Proveedor(ruc, nom, dir);
        return pd.actualizar(prov);
    }

    @Override
    public String eliminarProveedor(String ruc) {
        return pd.eliminar(ruc);
    }

}
