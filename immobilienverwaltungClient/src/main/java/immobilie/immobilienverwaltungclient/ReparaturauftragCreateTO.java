/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import jakarta.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Ensar
 */
public record ReparaturauftragCreateTO(
    String auftragsnummer,
        
    @JsonbDateFormat("yyyy-MM-dd")    
    LocalDate wartungsdatum,
    String beschreibung,
    String massnahmen,
    String wartungsstatus,   
    int immobiliennummer     
) {}
