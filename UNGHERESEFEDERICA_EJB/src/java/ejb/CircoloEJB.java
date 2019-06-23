
package ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Federica
 */
@Stateless
@LocalBean
@Interceptors(CountCall.class)
public class CircoloEJB implements CircoloEJBRemote {
    @Inject
    EntityManager em;
    
    @Override
    public Circolo getCircoloById(Long id) {
        TypedQuery q = em.createNamedQuery("findById", Circolo.class);
        q.setParameter("id_circolo", id);
        return (Circolo) q.getSingleResult();
    }

    @Override
    public List<Circolo> getCircoliByRegione(String regione) {
        TypedQuery q = em.createNamedQuery("findByRegione",Circolo.class);
        q.setParameter("regione_circolo", regione);
        return q.getResultList();
    }

    @Override
    public List<Circolo> getAllCircoli() {
        TypedQuery q = em.createNamedQuery("findAllCircoli",Circolo.class);
        return q.getResultList();
    }

    @Override
    public void creaCircolo(Circolo c) {
        em.persist(c);
    }

    @Override
    public void cancellaCircolo(Circolo c) {
        em.remove(em.merge(c));
    }

    @Override
    public void cambiaCircolo(Circolo c) {
        em.merge(c);
    }
   
}