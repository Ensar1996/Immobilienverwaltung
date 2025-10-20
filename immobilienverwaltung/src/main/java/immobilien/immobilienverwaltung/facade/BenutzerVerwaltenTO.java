/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;



/**
 *
 * @author ahmet
 */
public record BenutzerVerwaltenTO(String benutzerkennung,
        String passwort, 
        String vorname, 
        String telefonnummer, 
        String rolle) {
    
}
