/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

/**
 *
 * @author ahmet
 */
public record BenutzerTO(
        String benutzerkennung,
        String passwort,
        String vorname,
        String telefonnummer,
        String rolle) {
    
}
