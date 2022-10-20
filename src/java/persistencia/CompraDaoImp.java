package persistencia;

import java.util.List;
import negocio.Compra;
import negocio.Detallecompra;
import negocio.Detalleventa;
import negocio.LineaCompra;
import negocio.Proveedor;

public class CompraDaoImp implements CompraDao {

    private ArticuloJpaController ajc;
    private ProveedorJpaController1 pjc1;
    private CompraJpaController comjc;
    private DetallecompraJpaController dcjc;

    public void setDcjc(DetallecompraJpaController dcjc) {
        this.dcjc = dcjc;
    }
    
    

    public void setComjc(CompraJpaController comjc) {
        this.comjc = comjc;
    }

    public void setPjc1(ProveedorJpaController1 pjc1) {
        this.pjc1 = pjc1;
    }

    public void setAjc(ArticuloJpaController ajc) {
        this.ajc = ajc;
    }

    @Override
    public List listarArticulos() {
        return ajc.findArticuloEntities();
    }

    @Override
    public Proveedor buscarProveedor(String ruc) {
        return pjc1.findProveedor(ruc);
    }

    @Override
    public String grabarCompra(Compra c) {
        String msg;
        try {
            comjc.create(c);
            msg = "Compra grabada";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public String grabarDetalleCompra(Detallecompra dc) {
        String msg;
        try {
            dcjc.create(dc);
            msg = "Compra grabada";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public List listarCompras() {
        return comjc.findCompraEntities();
    }

}
