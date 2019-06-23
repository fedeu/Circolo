
package ungheresefederica_jms;

import ejb.Circolo;
import java.util.Scanner;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Federica
 */
public class JMSClient {

    public static void main(String[] args) throws NamingException {
        Context ctx = new InitialContext();
        Destination topic = (Destination) ctx.lookup("jms/javaee7/Topic");
        ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/javaee7/ConnectionFactory");
        Scanner in = new Scanner(System.in);
        
        //Inserimento dei dati da cambiare relativamente ad uno specifico Circolo -- Aggiunte println
        System.out.println("---Inserire l'id del circolo da modificare: ");
        Long id = Long.parseLong(in.nextLine());    //modifica da metodo nextLong a metodo nextLine con cast a Long
        
        System.out.println("\n---Inserire il nuovo nome: ");
        String nome = in.nextLine();
        System.out.println("\n---Inserire il nuovo responsabile: ");
        String responsabile = in.nextLine();
        
        //Istanziazione di un circolo contenente le sole informazioni utili a reperirlo dal DB e a modificarne i dati -- aggiunta println
        Circolo c = new Circolo();
        c.setId(id);
        c.setNome(nome);
        c.setResponsabile(responsabile);
        
        try(JMSContext jmsCtx = cf.createContext()){
            jmsCtx.createProducer().send(topic, c);
        }   
    }  
}