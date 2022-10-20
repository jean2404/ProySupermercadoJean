/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sagit
 */
@Entity
@Table(name = "DETALLEVENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleventa.findAll", query = "SELECT d FROM Detalleventa d")
    , @NamedQuery(name = "Detalleventa.findByNum", query = "SELECT d FROM Detalleventa d WHERE d.detalleventaPK.num = :num")
    , @NamedQuery(name = "Detalleventa.findByCod", query = "SELECT d FROM Detalleventa d WHERE d.detalleventaPK.cod = :cod")
    , @NamedQuery(name = "Detalleventa.findByCan", query = "SELECT d FROM Detalleventa d WHERE d.can = :can")})
public class Detalleventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleventaPK detalleventaPK;
    @Basic(optional = false)
    @Column(name = "CAN")
    private int can;
    @JoinColumn(name = "COD", referencedColumnName = "COD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "NUM", referencedColumnName = "NUM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venta venta;

    public Detalleventa() {
    }

    public Detalleventa(DetalleventaPK detalleventaPK) {
        this.detalleventaPK = detalleventaPK;
    }

    public Detalleventa(DetalleventaPK detalleventaPK, int can) {
        this.detalleventaPK = detalleventaPK;
        this.can = can;
    }

    public Detalleventa(String num, String cod) {
        this.detalleventaPK = new DetalleventaPK(num, cod);
    }

    public DetalleventaPK getDetalleventaPK() {
        return detalleventaPK;
    }

    public void setDetalleventaPK(DetalleventaPK detalleventaPK) {
        this.detalleventaPK = detalleventaPK;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleventaPK != null ? detalleventaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleventa)) {
            return false;
        }
        Detalleventa other = (Detalleventa) object;
        if ((this.detalleventaPK == null && other.detalleventaPK != null) || (this.detalleventaPK != null && !this.detalleventaPK.equals(other.detalleventaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Detalleventa[ detalleventaPK=" + detalleventaPK + " ]";
    }
    
}
