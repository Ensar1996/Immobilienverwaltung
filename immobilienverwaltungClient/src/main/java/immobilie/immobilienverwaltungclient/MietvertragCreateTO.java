/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;
import java.util.Date;

/**
 *
 * @author Ensar
 */


public record MietvertragCreateTO(
    String vertragsstatus,
    Date mietbeginn,
    Date mietende,
    double monatlicheMiete,
    double kaution,
    int mieterId,
    int immobiliennummer
) {}
