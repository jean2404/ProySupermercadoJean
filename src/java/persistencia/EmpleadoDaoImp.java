package persistencia;

import persistencia.exceptions.NonexistentEntityException;

public class EmpleadoDaoImp implements EmpleadoDao {

    private EmpleadoJpaController ejc;

    public void setEjc(EmpleadoJpaController ejc) {
        this.ejc = ejc;
    }

    @Override
    public String grabar(Empleado emp) {
        String msg;
        try {
            ejc.create(emp);
            msg = "Empleado grabado";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public Empleado buscar(String cod) {
        return ejc.findEmpleado(cod);
    }

    @Override
    public String actualizar(Empleado emp) {
        String msg;
        try {
            ejc.edit(emp);
            msg = "Empleado actualizado";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public String eliminar(String cod) {
        String msg;
        try {
            ejc.destroy(cod);
            msg = "Empleado eliminado";
        } catch (NonexistentEntityException e) {
            msg = e.getMessage();
        }
        return msg;
    }
}
