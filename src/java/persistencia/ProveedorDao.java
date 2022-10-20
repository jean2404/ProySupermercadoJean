package persistencia;

import negocio.Proveedor;

public interface ProveedorDao {

    public String grabar(Proveedor prov);

    public Proveedor buscar(String ruc);

    public String actualizar(Proveedor prov);

    public String eliminar(String ruc);
}
