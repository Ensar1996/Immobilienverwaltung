/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Immobilien;
import immobilien.immobilienverwaltung.core.entities.type.Adresse;
import immobilien.immobilienverwaltung.core.entities.type.AdresseConverter;
import immobilien.immobilienverwaltung.core.entities.type.Immobilientyp;
import immobilien.immobilienverwaltung.core.entities.type.Zustand;
import immobilien.immobilienverwaltung.dataaccess.MietvertragEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 *
 * @author ahmet
 */
@Entity
@Table(name = "immobilien")
public class ImmobilienEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "immobiliennummer")
    @SequenceGenerator(name = "immobiliennummer", sequenceName = "immobiliennummer", allocationSize = 1)
    private int immobiliennummer;
    private double groesse; // in m2;
    @Convert(converter = AdresseConverter.class)
    @Column(length = 255)
    private Adresse adresse;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "typ")
    private Immobilientyp typ;
    private int baujahr;
    @Enumerated(EnumType.STRING)
    private Zustand zustand;
    @Column(name = "mietpreis_pro_monat")
    private double mietpreisProMonat;
    
    
    public ImmobilienEntity(){
        
    }
    
    public ImmobilienEntity(Immobilien immobilien){
        this.immobiliennummer = immobilien.getImmobiliennummer();
        this.groesse = immobilien.getGroesse();
        this.adresse = immobilien.getAdresse();
        this.typ = immobilien.getTyp();
        this.baujahr = immobilien.getBaujahr();
        this.zustand = immobilien.getZustand();
        this.mietpreisProMonat = immobilien.getMietpreisProMonat();
        
        
    }
    
    public Immobilien toImmobilien(){
        return new Immobilien(
        this.immobiliennummer,
                this.groesse,
                this.adresse,
                this.typ,
                this.baujahr,
                this.zustand,
                this.mietpreisProMonat
        );
        
        
        
        
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
    

    
  
    
    }
