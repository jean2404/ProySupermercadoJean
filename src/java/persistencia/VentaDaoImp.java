package persistencia;

import java.util.List;
import negocio.Articulo;
import negocio.Cliente;
import negocio.Detalleventa;
import negocio.LineaVenta;
import negocio.Venta;

public class VentaDaoImp implements VentaDao {

    private ArticuloJpaController ajc;
    private ClienteJpaController1 cjc1;
    private VentaJpaController vjc;
    private DetalleventaJpaController djc;

    public void setDjc(DetalleventaJpaController djc) {
        this.djc = djc;
    }

    public void setVjc(VentaJpaController vjc) {
        this.vjc = vjc;
    }

    public void setCjc1(ClienteJpaController1 cjc1) {
        this.cjc1 = cjc1;
    }

    public void setAjc(ArticuloJpaController ajc) {
        this.ajc = ajc;
    }

    @Override
    public List listarArticulos() {
        return ajc.findArticuloEntities();
    }

    @Override
    public Cliente buscarCliente(String dni) {
        return cjc1.findCliente(dni);
    }

    @Override
    public String grabarVenta(Venta v) {
        String msg;
        try {
            vjc.create(v);
            msg = "Venta grabada";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public String grabarDetalleVenta(Detalleventa dv) {
        String msg;
        try {
            djc.create(dv);
            msg = "Venta grabada";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public List listarVentas() {
        return vjc.findVentaEntities();
    }

}
