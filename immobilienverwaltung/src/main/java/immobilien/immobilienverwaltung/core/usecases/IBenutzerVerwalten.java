/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
import immobilien.immobilienverwaltung.core.entities.type.Rolle;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author ahmet
 */
@Local
public interface IBenutzerVerwalten {
    public Benutzer benutzerAnlegen(String benutzerkennung, String passwort, String vorname, String telefonnummer, Rolle rolle);
    public boolean benutzerLoeschen(String benutzerkennung);
    public boolean benutzerBearbeiten();
    public List <Benutzer> benutzerAnzeigen();
    public Benutzer getOneBenutzer(String benutzerkennung);
   public boolean benutzerUpdate(String benutzerkennung, String passwort, String vorname, String telefonnummer, Rolle rolle);
    
}
