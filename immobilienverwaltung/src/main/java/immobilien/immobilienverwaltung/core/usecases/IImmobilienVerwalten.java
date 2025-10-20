/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Immobilien;
import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import immobilien.immobilienverwaltung.core.entities.type.Adresse;
import immobilien.immobilienverwaltung.core.entities.type.Immobilientyp;
import immobilien.immobilienverwaltung.core.entities.type.Zustand;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author ahmet
 */
@Local
public interface IImmobilienVerwalten {
    public Immobilien immobilienAnlegen(double groesse, Adresse adresse, Immobilientyp typ, int baujahr, Zustand zustand, double mietpreisProMonat);
    public boolean immobilienLoeschen(int immobiliennummer);
    public boolean immobilienUpdate(int immobiliennummer, double groesse, Adresse adresse, Immobilientyp typ, int baujahr, Zustand zustand, double mietpreisProMonat);
    public List<Immobilien> immobilienAnzeigen();
    public Immobilien getOneImmobilien(int immobiliennummer);
    
}
