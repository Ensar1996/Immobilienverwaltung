/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import immobilien.immobilienverwaltung.dataaccess.MietvertragDAO;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Ensar
 */

@Stateless
public class MietvertragManager {

    @EJB
    private MietvertragDAO mietvertragDAO;

    public Mietvertrag anlegen(Mietvertrag vertrag) {
        return mietvertragDAO.anlegen(vertrag);
    }

    public Mietvertrag suchen(int vertragsnummer) {
        return mietvertragDAO.suchen(vertragsnummer);
    }

    public List<Mietvertrag> alleVertraege() {
        return mietvertragDAO.alleVertraege();
    }

    public boolean loeschen(int vertragsnummer) {
        return mietvertragDAO.loeschen(vertragsnummer);
    }

    
    public Mietvertrag verlaengern(int vertragsnummer, Date neuesEnddatum) {
    return mietvertragDAO.verlaengern(vertragsnummer, neuesEnddatum);
}

    public Mietvertrag kuendigen(int vertragsnummer, Date kuendigungsdatum) {
    return mietvertragDAO.kuendigen(vertragsnummer, kuendigungsdatum);
}

    
    
}