package persistencia;

import java.util.List;
import negocio.Cliente;
import negocio.Detalleventa;
import negocio.LineaVenta;
import negocio.Venta;

public interface VentaDao {
    public List listarArticulos();
    public Cliente buscarCliente(String dni);
    public String grabarVenta(Venta v);
    public String grabarDetalleVenta(Detalleventa dv);
    public List listarVentas();
}
