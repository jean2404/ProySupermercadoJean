package negocio;

import java.util.ArrayList;
import java.util.List;

public class VentaObj {

    private List ces = new ArrayList();

    public void agregarLinea(String cod, String nom, double pre, int can) {
        LineaVenta lin = new LineaVenta(cod, nom, pre, can);
        ces.add(lin);
    }

    public void quitarLinea(String cod) {
        for (int i = 0; i < ces.size(); i++) {
            LineaVenta lin = (LineaVenta) ces.get(i);
            if (lin.getCod().equals(cod)) {
                ces.remove(i);
            }
        }
    }

    public double getTotal() {
        double tot = 0;
        for (int i = 0; i < ces.size(); i++) {
            LineaVenta lin = (LineaVenta) ces.get(i);
            tot += lin.getImporte();
        }
        return tot;
    }

    public List getCes() {
        return ces;
    }

}
