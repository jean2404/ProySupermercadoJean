package persistencia;

import negocio.Articulo;

public interface ArticuloDao {

    public String grabar(Articulo art);
    public Articulo buscar(String cod);
    public String actualizar(Articulo art);
    public String eliminar(String cod);
    
}
