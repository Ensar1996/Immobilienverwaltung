/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Reparaturauftrag;
import immobilien.immobilienverwaltung.dataaccess.ReparaturauftragDAO;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;


/**
 *
 * @author Ensar
 */


@Stateless
public class ReparaturauftragManager {

    @EJB
    private ReparaturauftragDAO reparaturauftragDAO;

    public Reparaturauftrag anlegen(Reparaturauftrag auftrag) {
        return reparaturauftragDAO.anlegen(auftrag);
    }

    public Reparaturauftrag suchen(String auftragsnummer) {
        return reparaturauftragDAO.suchen(auftragsnummer);
    }

    public List<Reparaturauftrag> alleAuftraege() {
        return reparaturauftragDAO.alleAuftraege();
    }

    public boolean loeschen(String auftragsnummer) {
        return reparaturauftragDAO.loeschen(auftragsnummer);
    }

    public boolean bearbeiten(Reparaturauftrag auftrag) {
        return reparaturauftragDAO.bearbeiten(auftrag);
    }
}

