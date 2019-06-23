
package ejb;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Federica
 */
@Remote
public interface CircoloEJBRemote {
    //Metodi per le query in Circolo.java
    Circolo getCircoloById(Long id);
    List<Circolo> getCircoliByRegione(String regione);
    List<Circolo> getAllCircoli();
    
    //Metodi per popolare e svuotare il database
    void creaCircolo(Circolo c);
    void cancellaCircolo(Circolo c);
    void cambiaCircolo(Circolo c);
}
