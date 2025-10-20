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
public class ReparaturauftragTO {

    private String auftragsnummer;
    @JsonbDateFormat("yyyy-MM-dd")   
    private LocalDate wartungsdatum;
    private String beschreibung;
    private String massnahmen;
    private String wartungsstatus;
    private String zustand; 
    private int immobiliennummer; 

    
    public ReparaturauftragTO() {}

    
    public ReparaturauftragTO(String auftragsnummer, LocalDate wartungsdatum, String beschreibung,
                               String massnahmen, String wartungsstatus, String zustand, int immobiliennummer) {
        this.auftragsnummer = auftragsnummer;
        this.wartungsdatum = wartungsdatum;
        this.beschreibung = beschreibung;
        this.massnahmen = massnahmen;
        this.wartungsstatus = wartungsstatus;
        this.zustand = zustand;
        this.immobiliennummer = immobiliennummer;
    }

    // Getter & Setter

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

    public String getWartungsstatus() {
        return wartungsstatus;
    }

    public void setWartungsstatus(String wartungsstatus) {
        this.wartungsstatus = wartungsstatus;
    }
    
      public int getImmobiliennummer() {
        return immobiliennummer;
    }

    public void setImmobiliennummer(int immobiliennummer) {
        this.immobiliennummer = immobiliennummer;
    }

    public String getZustand() {
        return zustand;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }
}