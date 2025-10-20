/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.entities;

import immobilien.immobilienverwaltung.core.entities.type.Rolle;
import java.io.Serializable;

/**
 *
 * @author ahmet
 */
public class Benutzer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String benutzerkennung;
    private String passwort;
    private String vorname;
    private String telefonnummer;
    private Rolle rolle;
    
    public Benutzer(){
        
    }


    public Benutzer(String benutzerkennung, String passwort, String vorname, String telefonnummer, Rolle rolle) {
        this.benutzerkennung = benutzerkennung;
        this.passwort = passwort;
        this.vorname = vorname;
        this.telefonnummer = telefonnummer;
        this.rolle = rolle;
    }
    

    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public void setBenutzerkennung(String benutzerkennung) {
        this.benutzerkennung = benutzerkennung;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public void setName(String vorname) {
        this.vorname = vorname;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }  
}
