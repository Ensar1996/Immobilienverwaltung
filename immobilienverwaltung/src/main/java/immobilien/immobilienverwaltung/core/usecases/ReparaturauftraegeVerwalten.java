
package immobilien.immobilienverwaltung.core.usecases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import immobilien.immobilienverwaltung.core.entities.Reparaturauftrag;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Ensar
 */
@Stateless
public class ReparaturauftraegeVerwalten implements IReparaturauftraegeVerwalten {

  
    @EJB
    private ReparaturauftragManager reparaturauftragManager;

    @Override
    public boolean reparaturauftragBearbeiten(Reparaturauftrag auftrag) {
    return reparaturauftragManager.bearbeiten(auftrag);
    }

    @Override
    public void reparaturauftragLoeschen(String auftragsnummer) {
        reparaturauftragManager.loeschen(auftragsnummer);
    }

    @Override
    public List<Reparaturauftrag> alleReparaturauftraege() {
        return reparaturauftragManager.alleAuftraege();
    }
}
