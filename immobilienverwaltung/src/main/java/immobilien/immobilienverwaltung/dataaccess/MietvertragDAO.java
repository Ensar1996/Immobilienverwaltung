/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * 
*/
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import immobilien.immobilienverwaltung.core.entities.type.Vertragsstatus;
import jakarta.ejb.Singleton;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Ensar
 */

@Singleton
public class MietvertragDAO {

    @PersistenceContext
    private EntityManager em;

    public Mietvertrag anlegen(Mietvertrag mietvertrag) {
        MietvertragEntity entity = new MietvertragEntity(mietvertrag);
        em.persist(entity);
        return entity.toMietvertrag();
    }

    public Mietvertrag suchen(int vertragsnummer) {
        MietvertragEntity entity = em.find(MietvertragEntity.class, vertragsnummer);
        return entity != null ? entity.toMietvertrag() : null;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Mietvertrag> alleVertraege() {
        em.clear(); 
        TypedQuery<MietvertragEntity> query = em.createQuery("SELECT m FROM MietvertragEntity m", MietvertragEntity.class);
        return query.getResultList().stream()
                .map(MietvertragEntity::toMietvertrag)
                .collect(Collectors.toList());
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Mietvertrag verlaengern(int vertragsnummer, Date neuesEnddatum) {
        MietvertragEntity entity = em.find(MietvertragEntity.class, vertragsnummer);
        if (entity == null) return null;
        entity.setMietende(neuesEnddatum);
        entity.setVertragsstatus(Vertragsstatus.AKTIV);
        em.merge(entity);
        em.flush();    // <-- erzwingt sofortiges Schreiben
        em.clear(); 
        return entity.toMietvertrag();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Mietvertrag kuendigen(int vertragsnummer, Date kuendigungsdatum) {
        MietvertragEntity entity = em.find(MietvertragEntity.class, vertragsnummer);
        if (entity == null) return null;
        entity.setMietende(kuendigungsdatum);
        entity.setVertragsstatus(Vertragsstatus.GEKUENDIGT);
        em.merge(entity);
        em.flush();    
        em.clear(); 
        return entity.toMietvertrag();
    }

    public boolean loeschen(int vertragsnummer) {
        MietvertragEntity entity = em.find(MietvertragEntity.class, vertragsnummer);
        if (entity == null) return false;
        em.remove(entity);
        return true;
    }
    

    
}


