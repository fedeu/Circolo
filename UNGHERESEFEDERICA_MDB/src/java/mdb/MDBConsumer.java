
package mdb;

import ejb.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Federica
 */

//Aggiunte proprietà mancanti
@MessageDriven(mappedName="jms/javaee7/Topic",
    activationConfig = { @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/javaee7/Topic")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/javaee7/Topic")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/javaee7/Topic")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MDBConsumer implements MessageListener {
    
    //Aggiunto costruttore
    public MDBConsumer() {}
    
    @Override
    public void onMessage(Message message){
        //Aggiunta del try-catch
        try {
            Circolo c = message.getBody(Circolo.class);
            Context ctx = new InitialContext();
            CircoloEJBRemote ejb = (CircoloEJBRemote) ctx.lookup("java:global/UNGHERESEFEDERICA_EJB/CircoloEJB!ejb.CircoloEJBRemote");
            Long id = c.getId();
            Circolo daModificare = ejb.getCircoloById(id);
            daModificare.setResponsabile(c.getResponsabile());
            daModificare.setNome(c.getNome());
            ejb.cambiaCircolo(daModificare);   
            
            //Aggiunta di println per notificare l'avvenuto cambiamento della specifica entità Circolo
            System.out.println("CAMBIATO: "+ejb.getCircoloById(id));

        } catch (JMSException | NamingException ex) {
            Logger.getLogger(MDBConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }   
}