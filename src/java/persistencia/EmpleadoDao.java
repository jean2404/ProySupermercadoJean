
package persistencia;

public interface EmpleadoDao {
    public String grabar(Empleado emp);
    public Empleado buscar(String cod);
    public String actualizar(Empleado emp);
    public String eliminar(String cod);
}
