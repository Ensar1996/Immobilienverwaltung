/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Benutzer;



/**
 *
 * @author ahmet
 */
public interface ILoggen  {
     public Boolean einloggen(String benutzerkennung, String passwort);
     
String getRolleVonBenutzer(String benutzerkennung);  
}
