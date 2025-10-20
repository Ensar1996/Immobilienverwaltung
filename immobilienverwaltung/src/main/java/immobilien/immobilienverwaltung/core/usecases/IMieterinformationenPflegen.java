/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Mieter;
import immobilien.immobilienverwaltung.core.entities.type.Adresse;
import jakarta.ejb.Local;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */
@Local
public interface IMieterinformationenPflegen {

    Mieter mieterAnlegen(String name, Adresse adresse, String telefonnummer, String email, Date geburtsdatum);
    
    Mieter mieterSuchen(int mieterid);

    boolean mieterLoeschen(int mieterid);
    
    boolean mieterBearbeiten(Mieter mieter);

    List<Mieter> allMieter();
   
}
