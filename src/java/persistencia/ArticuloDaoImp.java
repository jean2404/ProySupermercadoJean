package persistencia;

import negocio.Articulo;
import persistencia.exceptions.NonexistentEntityException;

public class ArticuloDaoImp implements ArticuloDao {

    private ArticuloJpaController ajc;

    public void setAjc(ArticuloJpaController ajc) {
        this.ajc = ajc;
    }

    @Override
    public String grabar(Articulo art) {
        String msg;
        try {
            ajc.create(art);
            msg = "Articulo grabado";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public Articulo buscar(String cod) {
        return ajc.findArticulo(cod);
    }

    @Override
    public String actualizar(Articulo art) {
        String msg;
        try {
            ajc.edit(art);
            msg = "Articulo actualizado";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public String eliminar(String cod) {
        String msg;
        try {
            ajc.destroy(cod);
            msg = "Articulo eliminado";
        } catch (NonexistentEntityException e) {
            msg = e.getMessage();
        }
        return msg;
    }
}
