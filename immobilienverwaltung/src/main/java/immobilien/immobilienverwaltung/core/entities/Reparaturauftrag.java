/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import immobilien.immobilienverwaltung.core.entities.type.Wartungsstatus;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ensar
 */

public class Reparaturauftrag implements Serializable {

    private String auftragsnummer;
    private LocalDate wartungsdatum;
    private String beschreibung;
    private String massnahmen;
    private Wartungsstatus wartungsstatus;
    private Immobilien immobilien;

    public Reparaturauftrag() {}

    public Reparaturauftrag(String auftragsnummer, LocalDate wartungsdatum, String beschreibung,
                             String massnahmen, Wartungsstatus wartungsstatus, Immobilien immobilien) {
        this.auftragsnummer = auftragsnummer;
        this.wartungsdatum = wartungsdatum;
        this.beschreibung = beschreibung;
        this.massnahmen = massnahmen;
        this.wartungsstatus = wartungsstatus;
        this.immobilien = immobilien;
    }

    public String getAuftragsnummer() {
        return auftragsnummer;
    }

    public void setAuftragsnummer(String auftragsnummer) {
        this.auftragsnummer = auftragsnummer;
    }

    public LocalDate getWartungsdatum() {
        return wartungsdatum;
    }

    public void setWartungsdatum(LocalDate wartungsdatum) {
        this.wartungsdatum = wartungsdatum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getMassnahmen() {
        return massnahmen;
    }

    public void setMassnahmen(String massnahmen) {
        this.massnahmen = massnahmen;
    }

    public Wartungsstatus getWartungsstatus() {
        return wartungsstatus;
    }

    public void setWartungsstatus(Wartungsstatus wartungsstatus) {
        this.wartungsstatus = wartungsstatus;
    }

    public Immobilien getImmobilie() {
        return immobilien;
    }

    public void setImmobilie(Immobilien immobilien) {
        this.immobilien = immobilien;
    }
}
