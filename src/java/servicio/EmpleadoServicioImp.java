package servicio;

import persistencia.Empleado;
import persistencia.EmpleadoDao;

public class EmpleadoServicioImp implements EmpleadoServicio {

    private EmpleadoDao ed;

    public void setEd(EmpleadoDao ed) {
        this.ed = ed;
    }

    @Override
    public String grabarEmpleado(String cod, String nom, String tip, String usu, String pas) {
        Empleado emp = new Empleado(cod, nom, tip, usu, pas);
        return ed.grabar(emp);
    }

    @Override
    public Object[] buscarEmpleado(String cod) {
        Empleado emp = ed.buscar(cod);
        if (emp != null) {
            Object[] fil = new Object[5];
            fil[0] = emp.getCod();
            fil[1] = emp.getNom();
            fil[2] = emp.getTip();
            fil[3] = emp.getUsu();
            fil[4] = emp.getPas();
            return fil;
        }
        return null;
    }

    @Override
    public String actualizarProveedor(String cod, String nom, String tip, String usu, String pas) {
        Empleado emp = new Empleado(cod, nom, tip, usu, pas);
        return ed.actualizar(emp);
    }

    @Override
    public String eliminarProveedor(String cod) {
        return ed.eliminar(cod);
    }

}
