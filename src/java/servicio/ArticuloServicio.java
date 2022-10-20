package servicio;

public interface ArticuloServicio {
    public String grabarArticulo(String cod, String nom, double pre, int sto);
    public Object[] buscarArticulo(String cod);
    public String actualizarArticulo(String cod, String nom, double pre, int sto);
    public String eliminarArticulo(String cod);
}
