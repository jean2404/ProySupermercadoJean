package servicio;

public interface ClienteServicio {

    public String grabarCliente(String dni, String nom, String dir);
    public Object[] buscarCliente(String dni);
    public String actualizarCliente(String dni, String nom, String dir);
    public String eliminarCliente(String dni);
}
