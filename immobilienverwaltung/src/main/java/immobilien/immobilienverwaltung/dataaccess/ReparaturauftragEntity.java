/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Reparaturauftrag;
import immobilien.immobilienverwaltung.core.entities.type.LocalDateAttributeConverter;
import immobilien.immobilienverwaltung.core.entities.type.Wartungsstatus;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Ensar
 */

    @Entity
    @Table(name = "reparaturauftrag")
    public class ReparaturauftragEntity {

    @Id
    @Column(name = "auftragsnummer")
    private String auftragsnummer;

    @JsonbDateFormat("yyyy-MM-dd")
    @Column(name = "wartungsdatum")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate wartungsdatum;

    @Column(name = "beschreibung", length = 2000)
    private String beschreibung;

    @Column(name = "massnahmen", length = 2000)
    private String massnahmen;

    @Enumerated(EnumType.STRING)
    @Column(name = "wartungsstatus")
    private Wartungsstatus wartungsstatus;

    @ManyToOne
    @JoinColumn(name = "immobiliennummer", referencedColumnName = "immobiliennummer")
    private ImmobilienEntity immobilie;

    public ReparaturauftragEntity() {}

    public ReparaturauftragEntity(Reparaturauftrag auftrag) {
        this.auftragsnummer = auftrag.getAuftragsnummer();
        this.wartungsdatum = auftrag.getWartungsdatum();
        this.beschreibung = auftrag.getBeschreibung();
        this.massnahmen = auftrag.getMassnahmen();
        this.wartungsstatus = auftrag.getWartungsstatus();
        this.immobilie = new ImmobilienEntity(auftrag.getImmobilie());
    }

    public Reparaturauftrag toReparaturauftrag() {
        return new Reparaturauftrag(
                auftragsnummer,
                wartungsdatum,
                beschreibung,
                massnahmen,
                wartungsstatus,
                immobilie != null ? immobilie.toImmobilien() : null
        );
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

    public Wartungsstatus getWartungsstatus() {
        return wartungsstatus;
    }

    public void setWartungsstatus(Wartungsstatus wartungsstatus) {
        this.wartungsstatus = wartungsstatus;
    }

    public ImmobilienEntity getImmobilie() {
        return immobilie;
    }

    public void setImmobilie(ImmobilienEntity immobilie) {
        this.immobilie = immobilie;
    }
}