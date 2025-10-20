/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import immobilien.immobilienverwaltung.core.entities.type.Vertragsstatus;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */


@Stateless
public class MietvertraegeVerwalten implements IMietvertraegeVerwalten {

    @EJB
    MietvertragManager mietvertragManager;

    @Override
    public Mietvertrag mietvertragAnlegen(Mietvertrag vertrag) {
        return mietvertragManager.anlegen(vertrag);
    }

    @Override
    public Mietvertrag mietvertragVerlaengern(int vertragsnummer, Date neuesEnddatum) {
       return mietvertragManager.verlaengern(vertragsnummer, neuesEnddatum); 

       
    }

    @Override
    public Mietvertrag mietvertragKuendigen(int vertragsnummer, Date kuendigungsdatum) {
    return mietvertragManager.kuendigen(vertragsnummer, kuendigungsdatum); 
}

    @Override
    public Mietvertrag mietvertragSuchen(int vertragsnummer) {
        return mietvertragManager.suchen(vertragsnummer);
    }

    @Override
    public List<Mietvertrag> alleMietvertraege() {
        return mietvertragManager.alleVertraege();
    }

    @Override
    public boolean mietvertragLoeschen(int vertragsnummer) {
        return mietvertragManager.loeschen(vertragsnummer);
    }
}

