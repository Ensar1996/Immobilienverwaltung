/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Path;
import immobilien.immobilienverwaltung.facade.ReparaturauftragAnzeigeTO;
import immobilien.immobilienverwaltung.core.entities.Reparaturauftrag;
import immobilien.immobilienverwaltung.core.entities.Immobilien;
import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import immobilien.immobilienverwaltung.core.usecases.IReparaturauftraegeAnlegen;
import immobilien.immobilienverwaltung.core.usecases.IReparaturauftraegeVerwalten;
import immobilien.immobilienverwaltung.core.usecases.ImmobilienVerwaltenManager;
import immobilien.immobilienverwaltung.dataaccess.ImmobilienDAO;
import immobilien.immobilienverwaltung.core.entities.type.Wartungsstatus;
import immobilien.immobilienverwaltung.core.usecases.IImmobilienVerwalten;
import immobilien.immobilienverwaltung.core.usecases.ImmobilienVerwalten;
import immobilien.immobilienverwaltung.core.usecases.ReparaturauftraegeVerwalten;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Ensar
 */

@Path("/")
public class ServiceReparaturanfaelligeWohnungen {
    
@EJB IImmobilienVerwalten immobilienverwalten;


@EJB IReparaturauftraegeVerwalten reparaturauftraegeverwalten;

    
@GET
@Path("/reparaturenImmobilienAnzeigen")
@Produces({MediaType.APPLICATION_JSON})
public ImmobilienTOList verfuegbarenImmobilienAnzeigen() {

    List<Immobilien> alleWohnungen = immobilienverwalten.immobilienAnzeigen();
    List<Reparaturauftrag> alleAuftraege = reparaturauftraegeverwalten.alleReparaturauftraege();
    LocalDate vor12Monaten = LocalDate.now().minusMonths(12);

    // Reparaturen pro Immobilie der letzten 12 Monate zählen
    Map<Integer, Long> reparaturAnzahlProImmobilie = alleAuftraege.stream()
    .filter(r -> r.getWartungsdatum() != null &&
                 !r.getWartungsdatum().isBefore(vor12Monaten) &&
                 !r.getWartungsdatum().isAfter(LocalDate.now()))
    .collect(Collectors.groupingBy(
        r -> r.getImmobilie().getImmobiliennummer(),
        Collectors.counting()
    ));

    // Durchschnitt berechnen
    double durchschnitt = reparaturAnzahlProImmobilie.values().stream()
        .mapToLong(Long::longValue)
        .average()
        .orElse(0);

    System.out.println("Durchschnitt Reparaturen: " + durchschnitt);

    // Nur auffällige Wohnungen filtern
    List<Immobilien> auffaelligeWohnungen = alleWohnungen.stream()
        .filter(wohnung -> {
            long anzahl = reparaturAnzahlProImmobilie.getOrDefault(wohnung.getImmobiliennummer(), 0L);
            return durchschnitt > 0 && anzahl > 1.5 * durchschnitt;
        })
        .collect(Collectors.toList());

    System.out.println("Anzahl an Wohnungen insgesamt: " + alleWohnungen.size());
    System.out.println("Anzahl an auffälligen Wohnungen: " + auffaelligeWohnungen.size());
    auffaelligeWohnungen.forEach(w -> 
        System.out.println("→ Anfällige Wohnung: Nr. " + w.getImmobiliennummer())
    );

    // In DTO umwandeln
    List<ImmobilienAnzeige> anzeigen = auffaelligeWohnungen.stream()
        .map(immobilien -> new ImmobilienAnzeige(
            immobilien.getImmobiliennummer(),
            immobilien.getAdresse().toString(),
            immobilien.getGroesse(),
            immobilien.getTyp().toString(),
            immobilien.getBaujahr(),
            immobilien.getZustand().toString(),
            immobilien.getMietpreisProMonat()
        ))
        .collect(Collectors.toList());

    return new ImmobilienTOList(anzeigen.isEmpty() ? null : anzeigen);
}

    }





