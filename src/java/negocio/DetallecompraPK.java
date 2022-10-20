/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author sagit
 */
@Embeddable
public class DetallecompraPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUM")
    private String num;
    @Basic(optional = false)
    @Column(name = "COD")
    private String cod;

    public DetallecompraPK() {
    }

    public DetallecompraPK(String num, String cod) {
        this.num = num;
        this.cod = cod;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (num != null ? num.hashCode() : 0);
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallecompraPK)) {
            return false;
        }
        DetallecompraPK other = (DetallecompraPK) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.DetallecompraPK[ num=" + num + ", cod=" + cod + " ]";
    }
    
}
