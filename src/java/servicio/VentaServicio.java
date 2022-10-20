package servicio;

import java.util.List;

public interface VentaServicio {
    public Object[] nuevaVenta(String codEmp);
    public List listarArticulos();
    public List agregarArticulo(String cod, String nom, double pre, int can);
    public List quitarArticulo(String cod);
    public Object[]buscarCliente(String dni);
    public String grabarVenta(String num, String fec, double tot,String dni,String codEmp);
}
