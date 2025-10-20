/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.entities;

import immobilien.immobilienverwaltung.core.entities.type.Rolle;
import java.io.Serializable;

/**
 *
 * @author ahmet
 */
public class MitarbeiterService extends Benutzer implements Serializable{
    public static final long serialVersionUID =2L;

    public MitarbeiterService(String benutzerKennung, String passwort, String name, String telefonnummer, Rolle rolle) {
        super(benutzerKennung, passwort, name, telefonnummer, rolle);
    }
    
}
