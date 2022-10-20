package servicio;

public interface EmpleadoServicio {
    public String grabarEmpleado(String cod, String nom, String tip, String usu, String pas);
    public Object[] buscarEmpleado(String cod);
    public String actualizarProveedor(String cod, String nom, String tip, String usu, String pas);
    public String eliminarProveedor(String cod);
}
