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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sagit
 */
@Entity
@Table(name = "ARTICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findByCod", query = "SELECT a FROM Articulo a WHERE a.cod = :cod")
    , @NamedQuery(name = "Articulo.findByNom", query = "SELECT a FROM Articulo a WHERE a.nom = :nom")
    , @NamedQuery(name = "Articulo.findByPre", query = "SELECT a FROM Articulo a WHERE a.pre = :pre")
    , @NamedQuery(name = "Articulo.findBySto", query = "SELECT a FROM Articulo a WHERE a.sto = :sto")})
public class Articulo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private List<Detallecompra> detallecompraList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private List<Detalleventa> detalleventaList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD")
    private String cod;
    @Basic(optional = false)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @Column(name = "PRE")
    private double pre;
    @Basic(optional = false)
    @Column(name = "STO")
    private int sto;

    public Articulo() {
    }

    public Articulo(String cod) {
        this.cod = cod;
    }

    public Articulo(String cod, String nom, double pre, int sto) {
        this.cod = cod;
        this.nom = nom;
        this.pre = pre;
        this.sto = sto;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPre() {
        return pre;
    }

    public void setPre(double pre) {
        this.pre = pre;
    }

    public int getSto() {
        return sto;
    }

    public void setSto(int sto) {
        this.sto = sto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Articulo[ cod=" + cod + " ]";
    }

    @XmlTransient
    public List<Detalleventa> getDetalleventaList() {
        return detalleventaList;
    }

    public void setDetalleventaList(List<Detalleventa> detalleventaList) {
        this.detalleventaList = detalleventaList;
    }

    @XmlTransient
    public List<Detallecompra> getDetallecompraList() {
        return detallecompraList;
    }

    public void setDetallecompraList(List<Detallecompra> detallecompraList) {
        this.detallecompraList = detallecompraList;
    }
    
}
