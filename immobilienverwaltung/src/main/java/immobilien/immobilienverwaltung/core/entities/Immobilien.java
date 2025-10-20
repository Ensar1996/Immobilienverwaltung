/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.entities;

import immobilien.immobilienverwaltung.core.entities.type.Adresse;
import immobilien.immobilienverwaltung.core.entities.type.Immobilientyp;
import immobilien.immobilienverwaltung.core.entities.type.Vertragsstatus;
import immobilien.immobilienverwaltung.core.entities.type.Zustand;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmet
 */
public class Immobilien implements Serializable {
     private static final long serialVersionUID = 1L;
     
     private int immobiliennummer;
     private double groesse; // in m2;
     private Adresse adresse;
     private Immobilientyp typ;
     private int baujahr;
     private Zustand zustand;
     private double mietpreisProMonat;
     private Mietvertrag mietvertrag;
     private Vertragsstatus vertragsstatus;
     private List<Reparaturauftrag> reparaturauftraege = new ArrayList<>();
     private Mieter mieter;
     

    public Immobilien(int immobiliennummer, double groesse, Adresse adresse, Immobilientyp typ, int baujahr, Zustand zustand, double mietpreisProMonat) {
        this.immobiliennummer = immobiliennummer;
        this.groesse = groesse;
        this.adresse = adresse;
        this.typ = typ;
        this.baujahr = baujahr;
        this.zustand = zustand;
        this.mietpreisProMonat = mietpreisProMonat;
        
    }
     
     
    public Immobilien (){
        
    }
     
     
    public int getImmobiliennummer() {
        return immobiliennummer;
    }

    public void setImmobiliennummer(int immobiliennummer) {
        this.immobiliennummer = immobiliennummer;
    }

    public double getGroesse() {
        return groesse;
    }

    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Immobilientyp getTyp() {
        return typ;
    }

    public void setTyp(Immobilientyp typ) {
        this.typ = typ;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public Zustand getZustand() {
        return zustand;
    }

    public void setZustand(Zustand zustand) {
        this.zustand = zustand;
    }

    public double getMietpreisProMonat() {
        return mietpreisProMonat;
    }

    public void setMietpreisProMonat(double mietpreisProMonat) {
        this.mietpreisProMonat = mietpreisProMonat;
    }

    public Mietvertrag getMietvertrag() {
        return mietvertrag;
    }
    
    public Vertragsstatus getVertragsstatus() {
    return vertragsstatus;
}


    public void setMietvertrag(Mietvertrag mietvertrag) {
        this.mietvertrag = mietvertrag;
    }

    public List<Reparaturauftrag> getReparaturauftraege() {
        return reparaturauftraege;
    }

    public void setReparaturauftraege(List<Reparaturauftrag> reparaturauftraege) {
        this.reparaturauftraege = reparaturauftraege;
    }

    public Mieter getMieter() {
        return mieter;
    }

    public void setMieter(Mieter mieter) {
        this.mieter = mieter;
    }

    
    
    
}
