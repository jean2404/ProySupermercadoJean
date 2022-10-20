package servicio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.Articulo;
import negocio.Compra;
import negocio.CompraObj;
import negocio.Detallecompra;
import negocio.DetallecompraPK;
import negocio.LineaCompra;
import negocio.Proveedor;
import persistencia.CompraDao;
import persistencia.Empleado;

public class CompraServicioImp implements CompraServicio {

    private CompraObj com;
    private CompraDao comdao;

    public void setComdao(CompraDao comdao) {
        this.comdao = comdao;
    }

    @Override
    public Object[] nuevaCompra(String codEmp) {
        com = new CompraObj();
        Object[] fil = new Object[3];
        fil[0] = getNum();
        fil[1] = getFecha();
        fil[2] = codEmp;
        return fil;
    }

        private String getNum(){
        String numObt="";
        List lis=comdao.listarCompras();
        for (int i = 0; i < lis.size(); i++) {
            Compra com=(Compra)lis.get(i);
            numObt=com.getNum();
        }
        String numStr=numObt.substring(0,1);
        String numEnt=numObt.substring(2);
        String numeroEntAum=String.valueOf(Integer.parseInt(numEnt)+1);
        while(numeroEntAum.length()<5){
            numeroEntAum='0'+numeroEntAum;
        }
        return numStr+numeroEntAum;
    }
    
    private String getFecha() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(d);
    }

    @Override
    public List listarArticulos() {
        List lis = comdao.listarArticulos();
        List lista = new ArrayList();
        for (int i = 0; i < lis.size(); i++) {
            Articulo art = (Articulo) lis.get(i);
            Object[] fil = new Object[3];
            fil[0] = art.getCod();
            fil[1] = art.getNom();
            fil[2] = art.getPre();
            lista.add(fil);
        }
        return lista;
    }

    @Override
    public List agregarArticulo(String cod, String nom, double pre, int can) {
        com.agregarLinea(cod, nom, pre, can);
        List lista = new ArrayList();
        for (int i = 0; i < com.getCes().size(); i++) {
            LineaCompra lin = (LineaCompra) com.getCes().get(i);
            Object[] fil = new Object[6];
            fil[0] = lin.getCod();
            fil[1] = lin.getNom();
            fil[2] = lin.getPre();
            fil[3] = lin.getCan();
            fil[4] = lin.getImporte();
            fil[5] = com.getTotal();
            lista.add(fil);
        }
        return lista;
    }

    @Override
    public List quitarArticulo(String cod) {
        com.quitarLinea(cod);
        List lista = new ArrayList();
        for (int i = 0; i < com.getCes().size(); i++) {
            LineaCompra lin = (LineaCompra) com.getCes().get(i);
            Object[] fil = new Object[6];
            fil[0] = lin.getCod();
            fil[1] = lin.getNom();
            fil[2] = lin.getPre();
            fil[3] = lin.getCan();
            fil[4] = lin.getImporte();
            fil[5] = com.getTotal();
            lista.add(fil);
        }
        return lista;
    }

    @Override
    public Object[] buscarProveedor(String ruc) {
        Proveedor prov = comdao.buscarProveedor(ruc);
        if (prov != null) {
            Object[] fil = new Object[2];
            fil[0] = prov.getRuc();
            fil[1] = prov.getNom();
            return fil;
        }
        return null;
    }

    @Override
    public String grabarCompra(String num, String fec, double tot, String ruc, String codEmp) {
        Compra c = new Compra();
        c.setNum(num);
        c.setFec(fec);
        c.setTot(tot);
        Proveedor prov = new Proveedor();
        prov.setRuc(ruc);
        c.setRuc(prov);
        Empleado emp = new Empleado();
        emp.setCod(codEmp);
        c.setCod(emp);
        String msg = comdao.grabarCompra(c);
        for (int i = 0; i < com.getCes().size(); i++) {
            LineaCompra lin = (LineaCompra) com.getCes().get(i);
            Detallecompra dt = new Detallecompra();
            Articulo art = new Articulo();
            art.setCod(lin.getCod());
            dt.setArticulo(art);
            dt.setCan(lin.getCan());
            DetallecompraPK dcp = new DetallecompraPK();
            dcp.setCod(lin.getCod());
            dcp.setNum(num);
            dt.setDetallecompraPK(dcp);
            dt.setCompra(c);
            msg = comdao.grabarDetalleCompra(dt);
        }
        return msg;
    }

}
