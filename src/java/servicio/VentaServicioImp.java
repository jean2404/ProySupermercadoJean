package servicio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.Articulo;
import negocio.Cliente;
import negocio.Detalleventa;
import negocio.DetalleventaPK;
import negocio.LineaVenta;
import negocio.Venta;
import negocio.VentaObj;
import persistencia.Empleado;
import persistencia.VentaDao;

public class VentaServicioImp implements VentaServicio {

    private VentaObj ven;
    private VentaDao vendao;

    public void setVendao(VentaDao vendao) {
        this.vendao = vendao;
    }

    @Override
    public Object[] nuevaVenta(String codEmp) {
        ven = new VentaObj();
        Object[] fil = new Object[3];
        fil[0] = getNum();
        fil[1] = getFecha();
        fil[2] = codEmp;
        return fil;
    }

     private String getFecha() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(d);
    }
    
    private String getNum(){
        String numObt="";
        List lis=vendao.listarVentas();
        for (int i = 0; i < lis.size(); i++) {
            Venta ven=(Venta)lis.get(i);
            numObt=ven.getNum();
        }
        String numStr=numObt.substring(0,1);
        String numEnt=numObt.substring(2);
        String numeroEntAum=String.valueOf(Integer.parseInt(numEnt)+1);
        while(numeroEntAum.length()<5){
            numeroEntAum='0'+numeroEntAum;
        }
        return numStr+numeroEntAum;
    }
    
    @Override
    public List listarArticulos() {
        List lis = vendao.listarArticulos();
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
        ven.agregarLinea(cod, nom, pre, can);
        List lista = new ArrayList();
        for (int i = 0; i < ven.getCes().size(); i++) {
            LineaVenta lin = (LineaVenta) ven.getCes().get(i);
            Object[] fil = new Object[6];
            fil[0] = lin.getCod();
            fil[1] = lin.getNom();
            fil[2] = lin.getPre();
            fil[3] = lin.getCan();
            fil[4] = lin.getImporte();
            fil[5] = ven.getTotal();
            lista.add(fil);
        }
        return lista;
    }

    @Override
    public List quitarArticulo(String cod) {
        ven.quitarLinea(cod);
        List lista = new ArrayList();
        for (int i = 0; i < ven.getCes().size(); i++) {
            LineaVenta lin = (LineaVenta) ven.getCes().get(i);
            Object[] fil = new Object[6];
            fil[0] = lin.getCod();
            fil[1] = lin.getNom();
            fil[2] = lin.getPre();
            fil[3] = lin.getCan();
            fil[4] = lin.getImporte();
            fil[5] = ven.getTotal();
            lista.add(fil);
        }
        return lista;
    }

    @Override
    public Object[] buscarCliente(String dni) {
        Cliente cli = vendao.buscarCliente(dni);
        if (cli != null) {
            Object[] fil = new Object[2];
            fil[0] = cli.getDni();
            fil[1] = cli.getNom();
            return fil;
        }
        return null;
    }

    @Override
    public String grabarVenta(String num, String fec, double tot, String dni, String codEmp) {
        Venta v= new Venta();
        v.setNum(num);
        v.setFec(fec);
        v.setTot(tot);
        Cliente cli= new Cliente();
        cli.setDni(dni);
        v.setDni(cli);
        Empleado emp=new Empleado();
        emp.setCod(codEmp);
        v.setCod(emp);
        String msg=vendao.grabarVenta(v);
        for (int i = 0; i < ven.getCes().size(); i++) {
            LineaVenta lin=(LineaVenta)ven.getCes().get(i);
            Detalleventa dt=new Detalleventa();
            Articulo art=new Articulo();
            art.setCod(lin.getCod());
            dt.setArticulo(art);
            dt.setCan(lin.getCan());
            DetalleventaPK dvp=new DetalleventaPK();
            dvp.setCod(lin.getCod());
            dvp.setNum(num);
            dt.setDetalleventaPK(dvp);
            dt.setVenta(v);
            msg=vendao.grabarDetalleVenta(dt);
        }
        return msg;
    }

}
