package negocio;

public class EmpleadoObj {

    private String cod,nom;

    public EmpleadoObj() {
    }

    public EmpleadoObj(String cod, String nom) {
        this.cod = cod;
        this.nom = nom;
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
    
    
    
}
