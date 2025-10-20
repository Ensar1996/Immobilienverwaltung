/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package immobilien.immobilienverwaltung.facade;
import immobilien.immobilienverwaltung.facade.MieterTO;
import immobilien.immobilienverwaltung.core.entities.type.Adresse;
import immobilien.immobilienverwaltung.core.entities.Mieter;
import immobilien.immobilienverwaltung.core.usecases.ILoggen;
import immobilien.immobilienverwaltung.core.usecases.IMieterinformationenPflegen;
import immobilien.immobilienverwaltung.dataaccess.MieterEntity;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import immobilien.immobilienverwaltung.core.entities.type.Adresse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
public class MieterService {
    

    @EJB private IMieterinformationenPflegen pflegen;
     
    @POST
    @Path("/mieter")
    @Produces({MediaType.APPLICATION_JSON})
    public Response createMieter(MieterTO mieterTO, @Context UriInfo uriInfo) {
    
    Mieter mieter = pflegen.mieterAnlegen(
        mieterTO.name(),
        mieterTO.adresse(),    
        mieterTO.telefonnummer(), 
        mieterTO.email(),
        mieterTO.geburtsdatum()
    );

    if (mieter == null) {
        return Response.status(Response.Status.CONFLICT).entity("Mieter konnte nicht erstellt werden.").build();
    } else {
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(mieter.getMieterid())); 
        return Response.created(uriBuilder.build())
                       .entity("Mieter erfolgreich erstellt.").build();
    }
}

    @GET
    @Path("/mieter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mieter> getAllMieter() {
    return pflegen.allMieter(); 
}


    @DELETE
    @Path("/mieterdaten/{mieterid}")
    public Response deleteMieter (@PathParam("mieterid") int mieterid ) {
    
        if     (pflegen.mieterLoeschen(mieterid))
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.CONFLICT).build();
}

    @PUT
    @Path("/mieter/{mieterid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMieter(@PathParam("mieterid") int mieterid, MieterTO mieterTO) {
        System.out.println(">>> PUT: Aktualisiere Mieter mit ID " + mieterid);

        Mieter mieter = new Mieter(
            mieterid,
            mieterTO.name(),
            mieterTO.adresse(), 
            mieterTO.telefonnummer(),
            mieterTO.email(),
            mieterTO.geburtsdatum()
        );

        boolean erfolg = pflegen.mieterBearbeiten(mieter);

        if (erfolg) {
            return Response.ok("Mieter erfolgreich aktualisiert.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Mieter nicht gefunden.").build();
        }
    }

}