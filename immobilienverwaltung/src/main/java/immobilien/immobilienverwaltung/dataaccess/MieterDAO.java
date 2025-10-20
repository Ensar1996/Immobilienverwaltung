/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Mieter;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Ensar
 */

@Singleton
public class MieterDAO {
    private static final Logger LOGGER = Logger.getLogger(MieterDAO.class.getName());
    
    @PersistenceContext
    private EntityManager em;
    
    public Mieter suchen (int mieterid) {
        
        MieterEntity mieterEntity = em.find(MieterEntity.class,mieterid);
        
         if (mieterEntity == null)
            return null;
        else
            return mieterEntity.toMieter();
        
    }
    
    public Mieter anlegen (Mieter mieter) {
        
        MieterEntity mieterEntity = new MieterEntity(mieter);
        em.persist(mieterEntity);
        
        return mieterEntity.toMieter();
        
    }
    
    public boolean loeschen(Mieter mieter) {
         MieterEntity mieterEntity = em.find(MieterEntity.class,mieter.getMieterid());       
         if (mieterEntity == null)
             return false;
         
         em.remove(mieterEntity);
         return true;
     }
    
    public List<Mieter> alleMieterAnzeigen() {
    TypedQuery<MieterEntity> query = em.createQuery("SELECT m FROM MieterEntity m", MieterEntity.class);
    
    return query.getResultList().stream()
                .map(MieterEntity::toMieter)
                .collect(Collectors.toList());
}
    
    public boolean bearbeiten(Mieter mieter) {
        MieterEntity entity = em.find(MieterEntity.class, mieter.getMieterid());
        if (entity == null) {
            return false;
        }

        entity.setName(mieter.getName());
        entity.setTelefonnummer(mieter.getTelefonnummer());
        entity.setEmail(mieter.getEmail());
        entity.setGeburtsdatum(mieter.getGeburtsdatum());

        return true;
    }

    
   
    
    
     
}
