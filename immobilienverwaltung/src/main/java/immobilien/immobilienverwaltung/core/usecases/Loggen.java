/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
import immobilien.immobilienverwaltung.dataaccess.BenutzerEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author ahmet
 */

@Stateless
public class Loggen implements ILoggen{

    @EJB LoginManager lManager; 
    
    @PersistenceContext
    private EntityManager em;
    
    

    @Override
    public Boolean einloggen(String benutzerkennung, String passwort) {
        Benutzer benutzer = lManager.findBenutzer(benutzerkennung);
        if(benutzer == null){
            return false;
        }
        return benutzer.getPasswort().equals(passwort);
    } 
    
    @Override
    public String getRolleVonBenutzer(String benutzerkennung) {
    BenutzerEntity benutzer = em.find(BenutzerEntity.class, benutzerkennung);
    if (benutzer != null) {
        return benutzer.getRolle().name(); 
    }
    return "";
}
}

    

