/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;

import jakarta.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Ensar
 */
public record ReparaturauftragAnzeigeTO(
    String auftragsnummer,
    @JsonbDateFormat("yyyy-MM-dd")    
    LocalDate wartungsdatum,
    String beschreibung,
    String massnahmen,
    String wartungsstatus,
    String zustand
) {}
