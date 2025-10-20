/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
import immobilien.immobilienverwaltung.core.entities.type.Rolle;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author ahmet
 */
@Singleton

public class BenutzerDAO {

    @PersistenceContext
    private EntityManager em;

    public Benutzer findBenutzer(String benutzerkennung){

        BenutzerEntity benutzerEntity = em.find(BenutzerEntity.class, benutzerkennung.trim());

    if(benutzerEntity != null){

        return benutzerEntity.toBenutzer();

    }else{

    return null;

    }  

  }

}

 
