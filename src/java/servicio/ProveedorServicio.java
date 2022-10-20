/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

/**
 *
 * @author sagit
 */
public interface ProveedorServicio {

    public String grabarProveedor(String ruc, String nom, String dir);
    public Object[] buscarProveedor(String ruc);
    public String actualizarProveedor(String ruc, String nom, String dir);
    public String eliminarProveedor(String ruc);
}
