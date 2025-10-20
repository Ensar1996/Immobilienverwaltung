/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
import immobilien.immobilienverwaltung.core.entities.type.Rolle;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author ahmet
 */
@Stateless
public class BenutzerVerwalten implements IBenutzerVerwalten {

    @EJB private BenutzerVerwaltenManager benutzerVerwaltenManager;
    
    @Override
    public Benutzer benutzerAnlegen(String benutzerkennung, String passwort, String vorname, String telefonnummer, Rolle rolle) {
        Benutzer benutzer = new Benutzer();
        benutzer.setBenutzerkennung(benutzerkennung);
        benutzer.setPasswort(passwort);
        benutzer.setName(vorname);
        benutzer.setTelefonnummer(telefonnummer);
        benutzer.setRolle(rolle);
        return benutzerVerwaltenManager.benutzerAnlegen(benutzer);
    }

    @Override
    public boolean benutzerLoeschen(String benutzerkennung) {
        
        if(benutzerkennung != null){
        return benutzerVerwaltenManager.benutzerLoeschen(benutzerkennung);
        }
        return false;
    }

    @Override
    public boolean benutzerBearbeiten() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    @Override
    public List<Benutzer> benutzerAnzeigen() {
        Benutzer getBenutzer = new Benutzer();
        
        return benutzerVerwaltenManager.benutzerAnzeigen(getBenutzer);
    }

    @Override
    public Benutzer getOneBenutzer(String benutzerkennung) {
        if(benutzerkennung != null){
            return benutzerVerwaltenManager.getOneBenutzer(benutzerkennung);
        }
        return null;
    }

    @Override
    public boolean benutzerUpdate(String benutzerkennung, String passwort, String vorname, String telefonnummer, Rolle rolle) {
        Benutzer benutzer = benutzerVerwaltenManager.getOneBenutzer(benutzerkennung);
        if(benutzer == null){
            return false;
        }
        benutzer.setBenutzerkennung(benutzerkennung);
        benutzer.setPasswort(passwort);
        benutzer.setName(vorname);
        benutzer.setTelefonnummer(telefonnummer);
        benutzer.setRolle(rolle);
        
        return benutzerVerwaltenManager.benutzerUpdate(benutzer);
    }

   
      
         
    }


