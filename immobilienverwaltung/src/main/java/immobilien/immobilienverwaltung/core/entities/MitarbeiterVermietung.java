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
 * test
 */
public class MitarbeiterVermietung extends Benutzer implements Serializable {
    private static final long serialVersionUID = 2L;

    public MitarbeiterVermietung(String benutzerKennung, String passwort, String name, String telefonnummer, Rolle rolle) {
        super(benutzerKennung, passwort, name, telefonnummer, rolle);
    }
}
