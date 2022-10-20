package persistencia;

import negocio.Cliente;
import persistencia.exceptions.NonexistentEntityException;

public class ClienteDaoImp implements ClienteDao {

    private ClienteJpaController cjc;

    public void setCjc(ClienteJpaController cjc) {
        this.cjc = cjc;
    }

    @Override
    public String grabar(Cliente cli) {
        String msg;
        try {
            cjc.create(cli);
            msg = "Cliente grabado";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public Cliente buscar(String dni) {
        return cjc.findCliente(dni);
    }

    @Override
    public String actualizar(Cliente cli) {
        String msg;
        try {
            cjc.edit(cli);
            msg = "Cliente actualizado";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public String eliminar(String dni) {
        String msg;
        try {
            cjc.destroy(dni);
            msg = "Cliente eliminado";
        } catch (NonexistentEntityException e) {
            msg = e.getMessage();
        }
        return msg;
    }

}
