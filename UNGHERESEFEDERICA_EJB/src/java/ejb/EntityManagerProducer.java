/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Federica
 */
public class EntityManagerProducer {
    @Produces
    @PersistenceContext(unitName="EsamePU")
    EntityManager em;
}
