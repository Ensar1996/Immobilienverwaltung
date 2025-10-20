/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;

import java.util.Date;

/**
 *
 * @author Ensar
 */

public record MietvertragTO(
    String vertragsstatus,    
    int vertragsnummer,
    Date mietbeginn,
    Date mietende,
    double monatlicheMiete,
    double kaution,
    String mieterName,
    int immobiliennummer
    
) {
}


