/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;

import immobilien.immobilienverwaltung.core.entities.Immobilien;
import immobilien.immobilienverwaltung.core.entities.Mieter;
import immobilien.immobilienverwaltung.facade.MietvertragTO;
import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import immobilien.immobilienverwaltung.core.usecases.IMietvertraegeVerwalten;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import immobilien.immobilienverwaltung.core.entities.type.Vertragsstatus;
import immobilien.immobilienverwaltung.core.usecases.MieterManager;
import immobilien.immobilienverwaltung.dataaccess.ImmobilienDAO;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */

@Path("/")
@Stateless
public class MietvertragService {
    
    @EJB
    MieterManager mieterManager;
    
    @EJB
    ImmobilienDAO immobilienDAO;


    @EJB
    private IMietvertraegeVerwalten verwalten;

@POST
@Path("/mietvertrag")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response createMietvertrag(MietvertragCreateTO to, @Context UriInfo uriInfo) {
    Mieter mieter = mieterManager.mieterSuchen(to.mieterId());

    
    Immobilien immobilie = immobilienDAO.getOneImmobilien(to.immobiliennummer());
    if (immobilie == null) {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("Immobilie mit ID " + to.immobiliennummer() + " nicht gefunden.")
                       .build();
    }

    Mietvertrag vertrag = new Mietvertrag(
        to.vertragsnummer(),
        to.mietbeginn(),
        to.mietende(),
        to.monatlicheMiete(),
        to.kaution(),
        Vertragsstatus.valueOf(to.vertragsstatus()),
        mieter,
        immobilie // ✅ jetzt korrekt übergeben
    );

    Mietvertrag gespeichert = verwalten.mietvertragAnlegen(vertrag);

    if (gespeichert == null) {
        return Response.status(Response.Status.CONFLICT)
                       .entity("Mietvertrag konnte nicht erstellt werden.")
                       .build();
    } else {
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(gespeichert.getVertragsnummer()));
        return Response.created(uriBuilder.build())
                       .entity("Mietvertrag erfolgreich erstellt.")
                       .build();
    }
}


    @GET
    @Path("/mietvertrag")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MietvertragTO> getAllVertraege() {
        return verwalten.alleMietvertraege()
        .stream()
        .map(vertrag -> new MietvertragTO(
            vertrag.getVertragsstatus().name(),
            vertrag.getVertragsnummer(),
            vertrag.getMietbeginn(),
            vertrag.getMietende(),
            vertrag.getMonatlicheMiete(),
            vertrag.getKaution(),
            vertrag.getMieter() != null ? vertrag.getMieter().getName() : null,
            vertrag.getImmobilien() != null ? vertrag.getImmobilien().getImmobiliennummer() : -1 
        ))
        .toList();
    }

    @DELETE
    @Path("/mietvertrag/{vertragsnummer}")
    public Response deleteMietvertrag(@PathParam("vertragsnummer") int vertragsnummer) {
        boolean geloescht = verwalten.mietvertragLoeschen(vertragsnummer);
        return geloescht 
               ? Response.ok().build()
               : Response.status(Response.Status.NOT_FOUND).entity("Nicht gefunden.").build();
    }

    @PUT
    @Path("/mietvertrag/{vertragsnummer}/kuendigen")
    public Response kuendigen(@PathParam("vertragsnummer") int vertragsnummer,
                               @QueryParam("datum") String datumStr) {
        Date kuendigungsdatum = Date.from(java.time.LocalDate.parse(datumStr).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        Mietvertrag gekuendigt = verwalten.mietvertragKuendigen(vertragsnummer, kuendigungsdatum);
        return gekuendigt != null
               ? Response.ok().entity("Vertrag gekündigt.").build()
               : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/mietvertrag/{vertragsnummer}/verlaengern")
    public Response verlaengern(@PathParam("vertragsnummer") int vertragsnummer,
                                @QueryParam("datum") String datumStr) {
        Date neuesEnddatum = Date.from(java.time.LocalDate.parse(datumStr).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        Mietvertrag verlaengert = verwalten.mietvertragVerlaengern(vertragsnummer, neuesEnddatum);
        return verlaengert != null
               ? Response.ok().entity("Vertrag verlängert.").build()
               : Response.status(Response.Status.NOT_FOUND).build();
    }
}
