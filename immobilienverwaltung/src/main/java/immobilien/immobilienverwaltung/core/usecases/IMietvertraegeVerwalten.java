/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;
import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import java.util.Date;
import java.util.List;

import jakarta.ejb.Local;

/**
 *
 * @author Ensar
 */


@Local
public interface IMietvertraegeVerwalten {

    Mietvertrag mietvertragAnlegen(Mietvertrag vertrag);

    Mietvertrag mietvertragVerlaengern(int vertragsnummer, Date neuesEnddatum);

    Mietvertrag mietvertragKuendigen(int vertragsnummer, Date kuendigungsdatum);

    Mietvertrag mietvertragSuchen(int vertragsnummer);

    List<Mietvertrag> alleMietvertraege();

    boolean mietvertragLoeschen(int vertragsnummer);
}

