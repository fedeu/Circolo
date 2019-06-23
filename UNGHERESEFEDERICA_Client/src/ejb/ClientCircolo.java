
package ejb;

import java.util.List;
import java.util.Scanner;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Federica
 */
public class ClientCircolo {

    public static void main(String[] args) throws NamingException{
        Context ctx = new InitialContext();
        CircoloEJBRemote ejb = (CircoloEJBRemote) ctx.lookup("java:global/UNGHERESEFEDERICA_EJB/CircoloEJB!ejb.CircoloEJBRemote");
        Scanner in = new Scanner(System.in);
        
        //A. Stampa di tutti i circoli -- Aggiunta println
        System.out.println("---Stampa di tutti i circoli---");
        List<Circolo> listaTot = ejb.getAllCircoli();
        for(Circolo c : listaTot){
            System.out.println("-"+c.toString());
        }
        
        //B. Stampa di tutti i circoli di una data regione -- Aggiunta println
        System.out.println("\n---Inserire una regione per effettuare la ricerca dei circoli:");
        String regione = in.next();
        List<Circolo> listaRegione = ejb.getCircoliByRegione(regione);  //aggiunta del parametro regione
        for(Circolo c : listaRegione){
            System.out.println(c.toString());
        }
    }
    
}