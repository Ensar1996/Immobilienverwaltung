 package immobilien.immobilienverwaltung.core.entities;

import java.util.Date;
import java.util.List;
import immobilien.immobilienverwaltung.core.entities.type.Adresse;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ensar
 */


public class Mieter {
   
    private int mieterid;
    private String name;
    private Adresse adresse;
    private String telefonnummer;
    private String email;
    private Date geburtsdatum;
    
   
    public Mieter(int id, String name, Adresse adresse, String telefonnummer, String email, Date geburtsdatum) {
        this.mieterid = id;
        this.name = name;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
    }
    
    public Mieter() {
    // leerer Konstruktor für Bean-Initialisierung etc.
}


    
    public int getMieterid() {
    return mieterid;
}

    public void setMieterid(int mieterid) {
    this.mieterid = mieterid;
}

    public String getName() {
    return name;
}

    public void setName(String name) {
    this.name = name;
}
    
    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTelefonnummer() {
    return telefonnummer;
}

    public void setTelefonnummer(String telefonnummer) {
    this.telefonnummer = telefonnummer;
}

    public String getEmail() {
    return email;
}

    public void setEmail(String email) {
    this.email = email;
}

    public Date getGeburtsdatum() {
    return geburtsdatum;
}

public void setGeburtsdatum(Date geburtsdatum) {
    this.geburtsdatum = geburtsdatum;
}

 

    
    
}

    
    

