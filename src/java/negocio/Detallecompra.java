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
@Table(name = "DETALLECOMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallecompra.findAll", query = "SELECT d FROM Detallecompra d")
    , @NamedQuery(name = "Detallecompra.findByNum", query = "SELECT d FROM Detallecompra d WHERE d.detallecompraPK.num = :num")
    , @NamedQuery(name = "Detallecompra.findByCod", query = "SELECT d FROM Detallecompra d WHERE d.detallecompraPK.cod = :cod")
    , @NamedQuery(name = "Detallecompra.findByCan", query = "SELECT d FROM Detallecompra d WHERE d.can = :can")})
public class Detallecompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallecompraPK detallecompraPK;
    @Basic(optional = false)
    @Column(name = "CAN")
    private int can;
    @JoinColumn(name = "COD", referencedColumnName = "COD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "NUM", referencedColumnName = "NUM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compra compra;

    public Detallecompra() {
    }

    public Detallecompra(DetallecompraPK detallecompraPK) {
        this.detallecompraPK = detallecompraPK;
    }

    public Detallecompra(DetallecompraPK detallecompraPK, int can) {
        this.detallecompraPK = detallecompraPK;
        this.can = can;
    }

    public Detallecompra(String num, String cod) {
        this.detallecompraPK = new DetallecompraPK(num, cod);
    }

    public DetallecompraPK getDetallecompraPK() {
        return detallecompraPK;
    }

    public void setDetallecompraPK(DetallecompraPK detallecompraPK) {
        this.detallecompraPK = detallecompraPK;
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

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallecompraPK != null ? detallecompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallecompra)) {
            return false;
        }
        Detallecompra other = (Detallecompra) object;
        if ((this.detallecompraPK == null && other.detallecompraPK != null) || (this.detallecompraPK != null && !this.detallecompraPK.equals(other.detallecompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Detallecompra[ detallecompraPK=" + detallecompraPK + " ]";
    }
    
}
