/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author sagit
 */
public class LineaCompra {

    private String cod, nom;
    private double pre;
    private int can;

    public double getImporte() {
        return pre * can;
    }

    public LineaCompra(String cod, String nom, double pre, int can) {
        this.cod = cod;
        this.nom = nom;
        this.pre = pre;
        this.can = can;
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

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

}