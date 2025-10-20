/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;


import immobilien.immobilienverwaltung.core.entities.type.Adresse;

/**
 *
 * @author ahmet
 */
public record ImmobilienTO(
        int immobiliennummer,
        Adresse adresse,
        double grosse,
        String typ,
        int baujahr,
        String zustand,
        double mietpreisProMonat
        ) {
    
}
