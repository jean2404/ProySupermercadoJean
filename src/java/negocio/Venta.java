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
@Table(name = "VENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByNum", query = "SELECT v FROM Venta v WHERE v.num = :num")
    , @NamedQuery(name = "Venta.findByFec", query = "SELECT v FROM Venta v WHERE v.fec = :fec")
    , @NamedQuery(name = "Venta.findByTot", query = "SELECT v FROM Venta v WHERE v.tot = :tot")})
public class Venta implements Serializable {

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
    @JoinColumn(name = "DNI", referencedColumnName = "DNI")
    @ManyToOne(optional = false)
    private Cliente dni;
    @JoinColumn(name = "COD", referencedColumnName = "COD")
    @ManyToOne(optional = false)
    private Empleado cod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private List<Detalleventa> detalleventaList;

    public Venta() {
    }

    public Venta(String num) {
        this.num = num;
    }

    public Venta(String num, String fec, double tot) {
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

    public Cliente getDni() {
        return dni;
    }

    public void setDni(Cliente dni) {
        this.dni = dni;
    }

    public Empleado getCod() {
        return cod;
    }

    public void setCod(Empleado cod) {
        this.cod = cod;
    }

    @XmlTransient
    public List<Detalleventa> getDetalleventaList() {
        return detalleventaList;
    }

    public void setDetalleventaList(List<Detalleventa> detalleventaList) {
        this.detalleventaList = detalleventaList;
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
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Venta[ num=" + num + " ]";
    }
    
}
