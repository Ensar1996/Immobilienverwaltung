/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Immobilien;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ahmet
 */
@Singleton
public class ImmobilienDAO{
        
    @PersistenceContext
    private EntityManager eManager;
    
    public Immobilien immobilienAnlegen(Immobilien immobilien){
       
        ImmobilienEntity iEntity = new ImmobilienEntity(immobilien);
        eManager.persist(iEntity);
        return iEntity.toImmobilien();
        
    }
    
    public List<Immobilien> immobilienAnzeigen(Immobilien immobilien){
        
        
        String jdbc;
        
        if(immobilien.getImmobiliennummer()== 0 && immobilien.getAdresse() == null ){
             jdbc = "SELECT b FROM ImmobilienEntity b";
        }else{
            
             jdbc = "SELECT b FROM ImmobilienEntity b WHERE";
             
             if(immobilien.getImmobiliennummer() !=0){
                  jdbc += "b.immobiliennummer" +  immobilien.getImmobiliennummer();
             }
             if(immobilien.getAdresse() != null){
                 jdbc += "b.adresse" + immobilien.getAdresse();
             }
             if(immobilien.getGroesse() != 0){
               jdbc += "b.grosse" + immobilien.getGroesse();
             }
             if(immobilien.getTyp() != null){
                 jdbc += "b.typ" + immobilien.getTyp();
             }
             if(immobilien.getBaujahr() != 0){
                 jdbc += "b.baujahr" + immobilien.getBaujahr();
             }
             if(immobilien.getZustand() != null){
                 jdbc += "b.zustand" + immobilien.getZustand(); 
             }
             if(immobilien.getMietpreisProMonat() != 0){
                 jdbc += "b.mietpreisProMonat" + immobilien.getMietpreisProMonat();
             }
            
        }
        
         TypedQuery<ImmobilienEntity> query = eManager.createQuery(jdbc,ImmobilienEntity.class);
        
         return
            query.getResultList().stream() 
                .map(immobilienEntity -> immobilienEntity.toImmobilien()) 
                .collect(Collectors.toList());
        
        }
        
    public Immobilien getOneImmobilien(int immobiliennummer){
        
        ImmobilienEntity iEntity = eManager.find(ImmobilienEntity.class, immobiliennummer);
        
        if(iEntity == null){
            return null;
        }
        return iEntity.toImmobilien();
    }
    
    public boolean immobilienUpdate(Immobilien immobilien){
        ImmobilienEntity iEntity = eManager.find(ImmobilienEntity.class, immobilien.getImmobiliennummer());
        
        if(iEntity == null){
            return false;
        }
        iEntity.setAdresse(immobilien.getAdresse());
        iEntity.setGroesse(immobilien.getGroesse());
        iEntity.setTyp(immobilien.getTyp());
        iEntity.setBaujahr(immobilien.getBaujahr());
        iEntity.setZustand(immobilien.getZustand());
        iEntity.setMietpreisProMonat(immobilien.getMietpreisProMonat());
        eManager.persist(iEntity);
        return true;
           
    }
    
    public boolean immobilienLoeschen(int immobiliennummer){
        
        ImmobilienEntity iEntity = eManager.find(ImmobilienEntity.class, immobiliennummer);
        
        if(iEntity != null){
         eManager.remove(iEntity);    
        }
       return false;
    }
    
}
