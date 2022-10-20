/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

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
import negocio.Compra;
import negocio.Venta;

/**
 *
 * @author sagit
 */
@Entity
@Table(name = "EMPLEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByCod", query = "SELECT e FROM Empleado e WHERE e.cod = :cod")
    , @NamedQuery(name = "Empleado.findByNom", query = "SELECT e FROM Empleado e WHERE e.nom = :nom")
    , @NamedQuery(name = "Empleado.findByTip", query = "SELECT e FROM Empleado e WHERE e.tip = :tip")
    , @NamedQuery(name = "Empleado.findByUsu", query = "SELECT e FROM Empleado e WHERE e.usu = :usu")
    , @NamedQuery(name = "Empleado.findByPas", query = "SELECT e FROM Empleado e WHERE e.pas = :pas")})
public class Empleado implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cod")
    private List<Compra> compraList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cod")
    private List<Venta> ventaList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD")
    private String cod;
    @Basic(optional = false)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @Column(name = "TIP")
    private String tip;
    @Basic(optional = false)
    @Column(name = "USU")
    private String usu;
    @Basic(optional = false)
    @Column(name = "PAS")
    private String pas;

    public Empleado() {
    }

    public Empleado(String cod) {
        this.cod = cod;
    }

    public Empleado(String cod, String nom, String tip, String usu, String pas) {
        this.cod = cod;
        this.nom = nom;
        this.tip = tip;
        this.usu = usu;
        this.pas = pas;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
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
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Empleado[ cod=" + cod + " ]";
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }
    
}
