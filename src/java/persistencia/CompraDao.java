/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import negocio.Compra;
import negocio.Detallecompra;

import negocio.LineaCompra;
import negocio.Proveedor;

/**
 *
 * @author sagit
 */
public interface CompraDao {
    public List listarArticulos();
    public Proveedor buscarProveedor(String ruc);
    public String grabarCompra(Compra c);
    public String grabarDetalleCompra(Detallecompra dc);
    public List listarCompras();
}
