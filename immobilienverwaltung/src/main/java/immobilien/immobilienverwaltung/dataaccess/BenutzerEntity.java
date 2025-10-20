/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
import immobilien.immobilienverwaltung.core.entities.type.Rolle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


/**
 *
 * @author ahmet
 */
@Entity
public class BenutzerEntity {
    @Id
    @Column(name = "benutzerkennung")
    private String benutzerkennung;
    @Column(name = "passwort")
    private String passwort;
    @Column(name = "vorname")
    private String vorname;
    @Column(name = "telefonnummer")
    private String telefonnummer;
    @Enumerated(EnumType.STRING)
    @Column(name = "rolle")
    private Rolle rolle;

    
    public BenutzerEntity(Benutzer benutzer) {
        this.benutzerkennung = benutzer.getBenutzerkennung();
        this.passwort = benutzer.getPasswort();
        this.vorname = benutzer.getVorname();
        this.telefonnummer = benutzer.getTelefonnummer();
        this.rolle = benutzer.getRolle();
    }
    //für JPA
    public BenutzerEntity(){
        
    }
    public Benutzer toBenutzer(){
        return new Benutzer(
        this.benutzerkennung,
        this.passwort,
        this.vorname,
        this.telefonnummer,
        this.rolle
        );
    }
    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setBenutzerkennung(String benutzerkennung) {
        this.benutzerkennung = benutzerkennung;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }
    
    
}

