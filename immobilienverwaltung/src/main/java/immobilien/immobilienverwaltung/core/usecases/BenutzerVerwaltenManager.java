/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
import immobilien.immobilienverwaltung.dataaccess.BenutzerEntity;
import immobilien.immobilienverwaltung.dataaccess.BenutzerVerwaltenDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author ahmet
 */
@Stateless
public class BenutzerVerwaltenManager{
    
    @EJB
    BenutzerVerwaltenDAO benutzerVerwaltenDAO;
        
        public Benutzer benutzerAnlegen(Benutzer benutzer){

            return benutzerVerwaltenDAO.benutzerAnlegen(benutzer);
        }
        public List<Benutzer> benutzerAnzeigen(Benutzer benutzer){
            return benutzerVerwaltenDAO.benutzerAnzeigen(benutzer);
        }
        
        
       public boolean benutzerLoeschen(String benutzerkennung){
           
           return benutzerVerwaltenDAO.benutzerLoeschen(benutzerkennung);
       }
       
       public Benutzer getOneBenutzer(String benutzerkennung){
           return benutzerVerwaltenDAO.getOneBenutzer(benutzerkennung);
       }
       
       public boolean benutzerUpdate(Benutzer benutzer){
           return benutzerVerwaltenDAO.benutzerUpdate(benutzer);
       }
      
}
