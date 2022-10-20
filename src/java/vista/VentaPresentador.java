package vista;

import java.util.ArrayList;
import java.util.List;

public class VentaPresentador {
    private String msg="";
    private Object[]fil={"","","","","",""};
    private Object[]fil2={"",""};
    private List lis=new ArrayList();

    public VentaPresentador() {
        lis.add(fil);
    }

    public Object[] getFil2() {
        return fil2;
    }

    public void setFil2(Object[] fil2) {
        this.fil2 = fil2;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getFil() {
        return fil;
    }

    public void setFil(Object[] fil) {
        this.fil = fil;
    }

    public List getLis() {
        return lis;
    }

    public void setLis(List lis) {
        this.lis = lis;
    }
    
}
