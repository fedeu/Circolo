
package ejb;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Federica
 */


@Singleton
@Startup
@LocalBean

//Aggiunta
@DataSourceDefinition(
    className ="org.apache.derby.jdbc.EmbeddedDataSource",  
    name ="jdbc/EsameDS",
    user ="APP",
    password ="APP",  
    databaseName ="EsameDB",
    properties = {"connectionAttributes=;create=true"}
)

public class DatabasePopulator {
    @Inject
    CircoloEJB ejb;
    
    //Inizializza il Database -- @PostConstruct aggiunta
    @PostConstruct
    public void init(){
        //Istanzia 3 circoli
        Circolo c1 = new Circolo();
        c1.setNome("Legambiente Benevento");
        c1.setResponsabile("Antonio Basile");
        c1.setEmail("legambiente.benevento@gmail.com");
        c1.setCitta("Benevento");
        c1.setProvincia("Benevento");
        c1.setRegione("Campania");
        
        Circolo c2 = new Circolo();
        c2.setNome("Legambiente Salerno");
        c2.setResponsabile("Antonella Rossi");
        c2.setEmail("legambiente.sa@gmail.com");
        c2.setCitta("Salerno");
        c2.setProvincia("Salerno");
        c2.setRegione("Campania");
        
        Circolo c3 = new Circolo();
        c3.setNome("Legambiente Sicilia");
        c3.setResponsabile("Daniele Verdi");
        c3.setEmail("legambiente.palermo@gmail.com");
        c3.setCitta("Palermo");
        c3.setProvincia("Palermo");
        c3.setRegione("Sicilia");
        
        //Aggiunge i 3 circoli ad database
        ejb.creaCircolo(c1);
        ejb.creaCircolo(c2);
        ejb.creaCircolo(c3);   
    }
    
    //Svuota il database -- aggiunta @PreDestroy
    @PreDestroy
    public void clearDB(){
        List<Circolo> lista = ejb.getAllCircoli();
        for(Circolo c : lista){
            ejb.cancellaCircolo(c);
        }
    }
    
}