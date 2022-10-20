package persistencia;

import negocio.Cliente;

public interface ClienteDao {

    public String grabar(Cliente cli);
    public Cliente buscar(String dni);
    public String actualizar(Cliente cli);
    public String eliminar(String dni);
    
}
