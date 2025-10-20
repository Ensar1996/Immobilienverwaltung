/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
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
public class BenutzerVerwaltenDAO {
    
    @PersistenceContext
    private EntityManager eManager;
    
    public Benutzer benutzerAnlegen(Benutzer benutzer){
    
        BenutzerEntity benutzerEntity = new BenutzerEntity(benutzer);
        eManager.persist(benutzerEntity);
        return benutzerEntity.toBenutzer();
    }
    
    public List<Benutzer> benutzerAnzeigen(Benutzer benutzer){
        
        String jdbc;
        
        if(benutzer.getBenutzerkennung() == null && benutzer.getVorname()== null 
                && benutzer.getTelefonnummer()==null && benutzer.getRolle() == null){
            jdbc = "SELECT b FROM BenutzerEntity b";
        }else{
            jdbc = "SELECT b FROM BenutzerEntity b WHERE";
            
            if(benutzer.getBenutzerkennung() !=null)
                jdbc += "b.benutzerkennung" + benutzer.getBenutzerkennung();
            if(benutzer.getVorname() != null)
                jdbc += "b.vorname" + benutzer.getVorname();
            if(benutzer.getTelefonnummer() != null)
                jdbc += "b.telefonnummer" + benutzer.getTelefonnummer();
            if(benutzer.getRolle() != null)
                jdbc += "b.rolle"+ benutzer.getRolle();
                
        }
        
        TypedQuery<BenutzerEntity> query = eManager.createQuery(jdbc,BenutzerEntity.class);
       
         return
            query.getResultList().stream() 
                .map(benutzerEntity -> benutzerEntity.toBenutzer()) 
                .collect(Collectors.toList());
        
    }
    
    
    public boolean benutzerLoeschen(String  benutzerkennung){
        
        BenutzerEntity bEntity = eManager.find(BenutzerEntity.class, benutzerkennung);
        if(bEntity != null){
            eManager.remove(bEntity);
        }
        return false;
    }
    
    public Benutzer getOneBenutzer(String benutzerkennung){
        BenutzerEntity bEntity = eManager.find(BenutzerEntity.class, benutzerkennung);
        
        if(bEntity != null){
            return bEntity.toBenutzer();
        }
        return null;
    }
    
    public boolean benutzerUpdate(Benutzer benutzer){
         BenutzerEntity bEntity = eManager.find(BenutzerEntity.class, benutzer.getBenutzerkennung());
         
         if(bEntity == null)
             return false;
         
         bEntity.setBenutzerkennung(benutzer.getBenutzerkennung());
         bEntity.setPasswort(benutzer.getPasswort());
         bEntity.setVorname(benutzer.getVorname());
         bEntity.setTelefonnummer(benutzer.getTelefonnummer());
         bEntity.setRolle(benutzer.getRolle());
         
         eManager.persist(bEntity);
         return true; 
    }
    
}
