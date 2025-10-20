/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Mieter;
import immobilien.immobilienverwaltung.core.entities.type.Adresse;
import jakarta.ejb.Stateless;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import static jakarta.servlet.jsp.jstl.core.Config.set;
import static java.lang.reflect.Array.set;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */

@Stateless
public class MieterinformationenPflegen implements IMieterinformationenPflegen{
    
    @EJB MieterManager mieterManager;
    
    @Override
    public Mieter mieterAnlegen(String name, Adresse adresse, String telefonnummer,  String email, Date geburtsdatum){
    Mieter mieter = new Mieter();
    mieter.setAdresse(adresse);
    mieter.setName(name);
    mieter.setTelefonnummer(telefonnummer);
    mieter.setEmail(email);
    mieter.setGeburtsdatum(geburtsdatum);
    
    
    return mieterManager.mieterAnlegen(mieter);
}
    @Override
    public Mieter mieterSuchen(int mieterid) {
        return mieterManager.mieterSuchen(mieterid);
    }

    @Override
    public boolean mieterLoeschen(int mieterid) {
    Mieter mieter = mieterManager.mieterSuchen(mieterid);

    if (mieter == null) {
        return false; // Mieter existiert nicht
    }

    return mieterManager.mieterLoeschen(mieter);
}
    
    @Override
    public boolean mieterBearbeiten(Mieter mieter) {
    return mieterManager.mieterBearbeiten(mieter);
}

    
    
     @Override
     public List <Mieter> allMieter() {
           
     return mieterManager.mieterSuchen(null);
        
    }
    
            
    
    
    
}
