/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
import immobilien.immobilienverwaltung.core.entities.Immobilien;
import immobilien.immobilienverwaltung.core.entities.type.Immobilientyp;
import immobilien.immobilienverwaltung.core.entities.type.Vertragsstatus;
import immobilien.immobilienverwaltung.core.entities.type.Zustand;
import immobilien.immobilienverwaltung.core.usecases.IImmobilienVerwalten;
import jakarta.ejb.EJB;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ahmet
 */
@Path("/")
public class ServiceImmobilienVerwalten {
    
    @EJB private IImmobilienVerwalten immobilienVerwalten;
    
    @POST
    @Path("/immobilienAnlegen")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response immobilienAnlegen(ImmobilienTO immobilienTO, @Context UriInfo uriInfo){
        
        Immobilientyp typ;
        Zustand zustand;
        
         try {
        typ = Immobilientyp.valueOf(immobilienTO.typ().toUpperCase()); //Parse
    } catch (IllegalArgumentException | NullPointerException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Ungültige Rolle: " + immobilienTO.typ())
                .build();
    }
          try {
        zustand = Zustand.valueOf(immobilienTO.zustand().toUpperCase()); //Parse
    } catch (IllegalArgumentException | NullPointerException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Ungültige Rolle: " + immobilienTO.zustand())
                .build();
    }
         
       Immobilien immobilien = immobilienVerwalten.immobilienAnlegen(
               immobilienTO.grosse(),
               immobilienTO.adresse(),
               typ,
               immobilienTO.baujahr(), 
               zustand,
               immobilienTO.mietpreisProMonat());
            if (immobilien == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
           else {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(immobilien.getImmobiliennummer()));
            return Response.created(uriBuilder.build()).build();
        }

        
    }
    
    
    @GET
    @Path("/immobilienAnzeigen")
    @Produces({MediaType.APPLICATION_JSON})
    public ImmobilienTOList immobilienAnzeigen(){
        
        List<Immobilien> immobilienListe = immobilienVerwalten.immobilienAnzeigen();
        
        ImmobilienTOList immobilienTOList;
        
        
        if(!immobilienListe.isEmpty()){
            immobilienTOList = new ImmobilienTOList(
            
                    immobilienListe.stream().map(immobilien -> new ImmobilienAnzeige(
                    immobilien.getImmobiliennummer(),
                    immobilien.getAdresse().toString(),
                    immobilien.getGroesse(),
                            immobilien.getTyp().toString(),
                            immobilien.getBaujahr(),
                            immobilien.getZustand().toString(),
                            immobilien.getMietpreisProMonat()
                            
                    )
                    ).collect(Collectors.toList()
                    ));
        }else{
            immobilienTOList = new ImmobilienTOList(null);
        
          
    }
    
    return immobilienTOList;
}
    
    
     @GET
     @Path("/getOneImmobilien/{immobiliennummer}")
     @Produces({MediaType.APPLICATION_JSON})
     public Immobilien getOneImmobilien(@PathParam("immobiliennummer") int immobiliennummer){
         return immobilienVerwalten.getOneImmobilien(immobiliennummer);
         
     }
     
     @PUT
     @Path("/immobilienUpdate")
     @Consumes({MediaType.APPLICATION_JSON})
     public Response immobilienUpdate(ImmobilienTO immobilienTO){
         
        Immobilientyp typ;
        Zustand zustand;
        
         try {
        typ = Immobilientyp.valueOf(immobilienTO.typ().toUpperCase()); //Parse
    } catch (IllegalArgumentException | NullPointerException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Ungültige Rolle: " + immobilienTO.typ())
                .build();
    }
          try {
        zustand = Zustand.valueOf(immobilienTO.zustand().toUpperCase()); //Parse
    } catch (IllegalArgumentException | NullPointerException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Ungültige Rolle: " + immobilienTO.zustand())
                .build();
    }
          
          if(immobilienVerwalten.immobilienUpdate(
                  immobilienTO.immobiliennummer(),
                  immobilienTO.grosse(), 
                  immobilienTO.adresse(),
                  typ,
                  immobilienTO.baujahr(), 
                  zustand, 
                  immobilienTO.mietpreisProMonat())){
         
         return Response.status(Response.Status.OK).build();
        }else {
            return Response.status(Response.Status.CONFLICT)
        .entity("Fehler beim Speichern der Immobilie").build();

        }
     
     } 
     
     
     @DELETE
     @Path("/immobilienLoeschen/{immobiliennummer}")
     public Response immobilienLoeschen(@PathParam("immobiliennummer") int immobiliennummer){
         
         Immobilien immobilie = immobilienVerwalten.getOneImmobilien(immobiliennummer);
         
         if(immobilie.getMietvertrag() != null && immobilie.getMietvertrag().getVertragsstatus() == Vertragsstatus.AKTIV) {
             return Response.status(Response.Status.CONFLICT).entity("Diese Immobilie ist mit einem aktiven Mietvertrag verbunden daher darf nicht gelöscht werden!").build();
         }
         
         immobilienVerwalten.immobilienLoeschen(immobiliennummer);
         return Response.status(Response.Status.OK).build();
         
     }
}
