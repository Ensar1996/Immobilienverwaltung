/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

/**
 *
 * @author ahmet
 */
public record ImmobilienAnzeige(
        int immobiliennummer,
        String adresse,
        double grosse,
        String typ,
        int baujahr,
        String zustand,
        double mietpreisProMonat,
        int vertragsnummer
) {
    
}
