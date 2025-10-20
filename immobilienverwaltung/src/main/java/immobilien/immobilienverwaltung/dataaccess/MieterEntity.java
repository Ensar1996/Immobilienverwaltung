/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Mieter;
import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import immobilien.immobilienverwaltung.core.entities.type.Adresse;
import immobilien.immobilienverwaltung.core.entities.type.AdresseConverter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */
@Entity
public class MieterEntity {
    
    @Id
    @SequenceGenerator(name = "mieter_seq", sequenceName = "MIETNUMMER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mieter_seq")
    @Column(name = "mietnummer")
    private int mieterid;

    @Column(name = "name")
    private String name;

    @Convert(converter = AdresseConverter.class)
    @Column(length = 255)
    private Adresse adresse;
    
    private String telefonnummer;
    private String email;

    @Temporal(TemporalType.DATE)
    private Date geburtsdatum;
    
    @OneToMany(mappedBy = "mieter", cascade = CascadeType.REMOVE)
    private List<MietvertragEntity> mietvertraege;


    
    public MieterEntity() {
        
    }

    public MieterEntity(Mieter mieter) {
        this.mieterid = mieter.getMieterid();
        this.name = mieter.getName();
        this.adresse = mieter.getAdresse();
        this.telefonnummer = mieter.getTelefonnummer();
        this.email = mieter.getEmail();
        this.geburtsdatum = mieter.getGeburtsdatum();
        
    }
    
    

   public Mieter toMieter() {
        Mieter mieter = new Mieter(
                this.mieterid,
                this.name,
                this.adresse,
                this.telefonnummer,
                this.email,
                this.geburtsdatum
        );
   
        return mieter;
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
