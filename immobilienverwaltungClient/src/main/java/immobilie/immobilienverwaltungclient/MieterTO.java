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
public class MieterTO {
    private int mieterid;
    private String name;
    private String telefonnummer;
    private Adresse adresse;
    private String email;
    private Date geburtsdatum;

    public MieterTO() {}

    public MieterTO(int mieterid, String name, Adresse adresse, String telefonnummer, String email, Date geburtsdatum) {
        this.mieterid = mieterid;
        this.name = name;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
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
    
    public Adresse getAdresse() {
        return adresse;
    }


    public String getTelefonnummer() {
        return telefonnummer;
    }

    public String getEmail() {
        return email;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setName(String name) {
        this.name = name;
    }

     public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
     
    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
    
}
