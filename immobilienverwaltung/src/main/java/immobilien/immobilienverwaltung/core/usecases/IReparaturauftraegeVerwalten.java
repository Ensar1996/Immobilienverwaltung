/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Reparaturauftrag;
import java.util.List;

/**
 *
 * @author Ensar
 */

public interface IReparaturauftraegeVerwalten {
    
    void reparaturauftragLoeschen(String auftragsnummer);
    
    boolean reparaturauftragBearbeiten(Reparaturauftrag auftrag);
    
    List<Reparaturauftrag> alleReparaturauftraege();
}
