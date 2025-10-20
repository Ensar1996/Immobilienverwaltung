/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Mieter;
import immobilien.immobilienverwaltung.dataaccess.MieterDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */ 

@Stateless
public class MieterManager {
    
    @EJB MieterDAO mieterDAO; 
    
    
    public Mieter mieterAnlegen(Mieter mieter) {
        return mieterDAO.anlegen(mieter);
    }

    public Mieter mieterSuchen(int mieterid) {
        return mieterDAO.suchen(mieterid);
    }

    public boolean mieterLoeschen(Mieter mieter) {
        return mieterDAO.loeschen(mieter);
    }

     public boolean mieterBearbeiten(Mieter mieter) {
        return mieterDAO.bearbeiten(mieter);
    }

  public List<Mieter> mieterSuchen(Mieter mieter) {
    return mieterDAO.alleMieterAnzeigen();
    
}
  
}
