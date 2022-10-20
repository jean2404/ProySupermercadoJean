/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import persistencia.Empleado;

/**
 *
 * @author sagit
 */
@Entity
@Table(name = "COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c")
    , @NamedQuery(name = "Compra.findByNum", query = "SELECT c FROM Compra c WHERE c.num = :num")
    , @NamedQuery(name = "Compra.findByFec", query = "SELECT c FROM Compra c WHERE c.fec = :fec")
    , @NamedQuery(name = "Compra.findByTot", query = "SELECT c FROM Compra c WHERE c.tot = :tot")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUM")
    private String num;
    @Basic(optional = false)
    @Column(name = "FEC")
    private String fec;
    @Basic(optional = false)
    @Column(name = "TOT")
    private double tot;
    @JoinColumn(name = "COD", referencedColumnName = "COD")
    @ManyToOne(optional = false)
    private Empleado cod;
    @JoinColumn(name = "RUC", referencedColumnName = "RUC")
    @ManyToOne(optional = false)
    private Proveedor ruc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
    private List<Detallecompra> detallecompraList;

    public Compra() {
    }

    public Compra(String num) {
        this.num = num;
    }

    public Compra(String num, String fec, double tot) {
        this.num = num;
        this.fec = fec;
        this.tot = tot;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFec() {
        return fec;
    }

    public void setFec(String fec) {
        this.fec = fec;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    public Empleado getCod() {
        return cod;
    }

    public void setCod(Empleado cod) {
        this.cod = cod;
    }

    public Proveedor getRuc() {
        return ruc;
    }

    public void setRuc(Proveedor ruc) {
        this.ruc = ruc;
    }

    @XmlTransient
    public List<Detallecompra> getDetallecompraList() {
        return detallecompraList;
    }

    public void setDetallecompraList(List<Detallecompra> detallecompraList) {
        this.detallecompraList = detallecompraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (num != null ? num.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Compra[ num=" + num + " ]";
    }
    
}
