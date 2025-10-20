
package immobilien.immobilienverwaltung.facade;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import immobilien.immobilienverwaltung.facade.ReparaturauftragCreateTO;

import immobilien.immobilienverwaltung.facade.ReparaturauftragAnzeigeTO;
import immobilien.immobilienverwaltung.core.entities.Reparaturauftrag;
import immobilien.immobilienverwaltung.core.entities.Immobilien;
import immobilien.immobilienverwaltung.core.usecases.IReparaturauftraegeAnlegen;
import immobilien.immobilienverwaltung.core.usecases.IReparaturauftraegeVerwalten;
import immobilien.immobilienverwaltung.core.usecases.ImmobilienVerwaltenManager;
import immobilien.immobilienverwaltung.dataaccess.ImmobilienDAO;
import immobilien.immobilienverwaltung.core.entities.type.Wartungsstatus;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

/**
 *
 * @author Ensar
 */


@Path("/")
@Stateless
public class ReparaturauftragService {

    @EJB
    private IReparaturauftraegeAnlegen anlegen;

    @EJB
    private IReparaturauftraegeVerwalten verwalten;

    @EJB
    private ImmobilienDAO immobilienDAO;

    @POST
    @Path("/reparaturauftrag")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReparaturauftrag(ReparaturauftragCreateTO to, @Context UriInfo uriInfo) {
        Immobilien immobilie = immobilienDAO.getOneImmobilien(to.immobiliennummer());

        if (immobilie == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Immobilie mit Nummer " + to.immobiliennummer() + " nicht gefunden.")
                           .build();
        }

        Reparaturauftrag auftrag = new Reparaturauftrag(
            to.auftragsnummer(),
            to.wartungsdatum(),
            to.beschreibung(),
            to.massnahmen(),
            Wartungsstatus.valueOf(to.wartungsstatus().toUpperCase()),
            immobilie
        );

        Reparaturauftrag gespeichert = anlegen.reparaturauftraegeAnlegen(auftrag);

        if (gespeichert == null) {
            return Response.status(Response.Status.CONFLICT)
                           .entity("Reparaturauftrag konnte nicht erstellt werden.")
                           .build();
        } else {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(gespeichert.getAuftragsnummer());
            return Response.created(builder.build())
                           .entity("Reparaturauftrag erfolgreich erstellt.")
                           .build();
        }
    }

    @GET
    @Path("/reparaturauftrag")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReparaturauftragAnzeigeTO> getAllReparaturauftraege() {
        return verwalten.alleReparaturauftraege()
                .stream()
                .map(r -> new ReparaturauftragAnzeigeTO(
                    r.getAuftragsnummer(),
                    r.getWartungsdatum(),
                    r.getBeschreibung(),
                    r.getMassnahmen(),
                    r.getWartungsstatus().name(),
                    r.getImmobilie().getZustand().name()
                ))
                .toList();
    }

    @DELETE
    @Path("/reparaturauftrag/{auftragsnummer}")
    public Response delete(@PathParam("auftragsnummer") String nummer) {
        try {
            verwalten.reparaturauftragLoeschen(nummer);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Nicht gefunden.").build();
        }
    }


    @PUT
    @Path("/reparaturauftrag/{auftragsnummer}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReparaturauftrag(
        @PathParam("auftragsnummer") String auftragsnummer,
        ReparaturauftragCreateTO to) {

    System.out.println(">>> PUT: Aktualisiere Reparaturauftrag mit Nummer " + auftragsnummer);

    Immobilien immobilie = immobilienDAO.getOneImmobilien(to.immobiliennummer());
    if (immobilie == null) {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("Immobilie nicht gefunden.")
                       .build();
    }

    Reparaturauftrag auftrag = new Reparaturauftrag(
        auftragsnummer, // <-- vom PathParam, nicht aus dem TO
        to.wartungsdatum(),
        to.beschreibung(),
        to.massnahmen(),
        Wartungsstatus.valueOf(to.wartungsstatus().toUpperCase()),
        immobilie
    );

    boolean aktualisiert = verwalten.reparaturauftragBearbeiten(auftrag);
    if (aktualisiert) {
        return Response.ok("Reparaturauftrag erfolgreich aktualisiert.").build();
    } else {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("Reparaturauftrag nicht gefunden.")
                       .build();
    }
}

}
