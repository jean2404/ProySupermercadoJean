package servicio;

import negocio.Cliente;
import persistencia.ClienteDao;

public class ClienteServicioImp implements ClienteServicio {

    private ClienteDao cd;

    public void setCd(ClienteDao cd) {
        this.cd = cd;
    }

    @Override
    public String grabarCliente(String dni, String nom, String dir) {
        Cliente cli = new Cliente(dni, nom, dir);
        return cd.grabar(cli);
    }

    @Override
    public Object[] buscarCliente(String dni) {
        Cliente cli = cd.buscar(dni);
        if (cli != null) {
            Object[] fil = new Object[3];
            fil[0] = cli.getDni();
            fil[1] = cli.getNom();
            fil[2] = cli.getDir();
            return fil;
        }
        return null;
    }

    @Override
    public String actualizarCliente(String dni, String nom, String dir) {
        Cliente cli = new Cliente(dni, nom, dir);
        return cd.actualizar(cli);
    }

    @Override
    public String eliminarCliente(String dni) {
        return cd.eliminar(dni);
    }

}
