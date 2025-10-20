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
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ahmet
 */
@Stateless
public class ImmobilienVerwalten implements IImmobilienVerwalten {
    
    @EJB private ImmobilienVerwaltenManager immobilienVerwaltenManager;
    @EJB private MietvertragManager mietvertragVerwaltenManager;
   
    

    @Override
    public Immobilien immobilienAnlegen( double groesse, Adresse adresse, Immobilientyp typ, int baujahr, Zustand zustand, double mietpreisProMonat) {
        
        Immobilien immobilien = new Immobilien();
        immobilien.setGroesse(groesse);
        immobilien.setAdresse(adresse);
        immobilien.setTyp(typ);
        immobilien.setBaujahr(baujahr);
        immobilien.setZustand(zustand);
        immobilien.setMietpreisProMonat(mietpreisProMonat);
       // immobilien.setMietvertrag(mietvertrag);
        return immobilienVerwaltenManager.immobilienAnlegen(immobilien);
    }

    @Override
    public boolean immobilienLoeschen(int immobiliennummer) {
        
        if(immobiliennummer == 0){
            return false;
        }
        
        return immobilienVerwaltenManager.immobilienLoeschen(immobiliennummer);
    }

    @Override
    public boolean immobilienUpdate(int immobiliennummer, double groesse, Adresse adresse, Immobilientyp typ, int baujahr, Zustand zustand, double mietpreisProMonat) {
        Immobilien immobilien = immobilienVerwaltenManager.getOneImmobilien(immobiliennummer);
        
        if(immobilien == null){
            return false;
        }
        immobilien.setImmobiliennummer(immobiliennummer);
        immobilien.setGroesse(groesse);
        immobilien.setAdresse(adresse);
        immobilien.setTyp(typ);
        immobilien.setBaujahr(baujahr);
        immobilien.setZustand(zustand);
        immobilien.setMietpreisProMonat(mietpreisProMonat);
       return immobilienVerwaltenManager.immibilienUpdate(immobilien);
    }

    @Override
    public List<Immobilien> immobilienAnzeigen() {
        Immobilien getImmobilien = new Immobilien();
         return immobilienVerwaltenManager.immobilienAnzeigen(getImmobilien);
    }

    @Override
    public Immobilien getOneImmobilien(int immobiliennummer) {
        return immobilienVerwaltenManager.getOneImmobilien(immobiliennummer);
    }
    
}
