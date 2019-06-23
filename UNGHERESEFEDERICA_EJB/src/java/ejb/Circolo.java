
package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Federica
 */
@Entity
@NamedQueries({
    @NamedQuery(name="findAllCircoli",query="select c from Circolo c"),
    @NamedQuery(name="findById",query="select c from Circolo c where c.id = :id_circolo"),
    @NamedQuery(name="findByRegione",query="select c from Circolo c where c.regione = :regione_circolo")
})
public class Circolo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    //Variabili d'istanza
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  
    private String nome,responsabile,email,citta,provincia,regione;
    
    //Costruttori
    public Circolo(){}

    public Circolo(String nomeC, String respons, String emailC, String cittaC, String prov, String reg) {
        nome = nomeC;
        responsabile = respons;
        email = emailC;
        citta = cittaC;
        provincia = prov;
        regione = reg;
    }
    
    //To string
    @Override
    public String toString(){
        return "Nome: "+nome+"\nResponsabile: "+responsabile+
            "\nEmail: "+email+"\nCitta': "+citta+"\nProvincia: "+provincia+
            "\nRegione: "+regione+"\nId: "+id+"\n";
    }
    
    //Get e Set
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(String responsabile) {
        this.responsabile = responsabile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }
   
}