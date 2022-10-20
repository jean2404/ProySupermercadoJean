package persistencia;

import negocio.Proveedor;
import persistencia.exceptions.NonexistentEntityException;

public class ProveedorDaoImp implements ProveedorDao {

    private ProveedorJpaController pjc;

    public void setPjc(ProveedorJpaController pjc) {
        this.pjc = pjc;
    }

    @Override
    public String grabar(Proveedor prov) {
        String msg;
        try {
            pjc.create(prov);
            msg = "Proveedor Grabado";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public Proveedor buscar(String ruc) {
        return pjc.findProveedor(ruc);
    }

    @Override
    public String actualizar(Proveedor prov) {
        String msg;
        try {
            pjc.edit(prov);
            msg = "Proveedor Actualizado";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public String eliminar(String ruc) {
        String msg;
        try {
            pjc.destroy(ruc);
            msg = "Proveedor eliminado";
        } catch (NonexistentEntityException e) {
            msg = e.getMessage();
        }
        return msg;
    }
}
