package servicio;

import negocio.Articulo;
import persistencia.ArticuloDao;

public class ArticuloServicioImp implements ArticuloServicio {

    private ArticuloDao artd;

    public void setArtd(ArticuloDao artd) {
        this.artd = artd;
    }

    @Override
    public String grabarArticulo(String cod, String nom, double pre, int sto) {
        Articulo art = new Articulo(cod, nom, pre, sto);
        return artd.grabar(art);
    }

    @Override
    public Object[] buscarArticulo(String cod) {
        Articulo art = artd.buscar(cod);
        if (art != null) {
            Object[] fil = new Object[4];
            fil[0] = art.getCod();
            fil[1] = art.getNom();
            fil[2] = art.getPre();
            fil[3] = art.getSto();
            return fil;
        }
        return null;
    }

    @Override
    public String actualizarArticulo(String cod, String nom, double pre, int sto) {
        Articulo art = new Articulo(cod, nom, pre, sto);
        return artd.actualizar(art);
    }

    @Override
    public String eliminarArticulo(String cod) {
        return artd.eliminar(cod);
    }

}
