/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;

import immobilien.immobilienverwaltung.core.entities.Mieter;
import immobilien.immobilienverwaltung.core.entities.type.Adresse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */
public record MieterTO(int mieterid, String name, Adresse adresse, String telefonnummer, String email, Date geburtsdatum) {
    
     public Mieter toMieter() {
        return new Mieter(mieterid, name, adresse, telefonnummer, email, geburtsdatum);
    }
    
}
