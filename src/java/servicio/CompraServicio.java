/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.util.List;

/**
 *
 * @author sagit
 */
public interface CompraServicio {
    public Object[] nuevaCompra(String codEmp);
    public List listarArticulos();
    public List agregarArticulo(String cod, String nom,double pre, int can);
    public List quitarArticulo(String cod);//cod del articulo a quitar
    public Object[] buscarProveedor(String ruc);
    public String grabarCompra(String num,String fec, double tot, String ruc, String codEmp);
}
