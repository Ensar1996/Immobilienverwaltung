/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this templateee
 */
package immobilien.immobilienverwaltung.facade;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
import immobilien.immobilienverwaltung.core.entities.type.Rolle;
import immobilien.immobilienverwaltung.core.usecases.IBenutzerVerwalten;
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
public class ServiceBenutzerVerwalten {
    
    @EJB private IBenutzerVerwalten  benutzerVerwalten;
    
    
    @POST
    @Path("/benutzerAnlegen")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
     public Response benutzerAnlegen(BenutzerVerwaltenTO benutzerVerwaltenTO, @Context UriInfo uriInfo){
         
         Rolle rolle;
    try {
        rolle = Rolle.valueOf(benutzerVerwaltenTO.rolle().toUpperCase()); //Parse
    } catch (IllegalArgumentException | NullPointerException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Ungültige Rolle: " + benutzerVerwaltenTO.rolle())
                .build();
    }
         Benutzer benutzer = benutzerVerwalten.benutzerAnlegen(
                benutzerVerwaltenTO.benutzerkennung(),
                benutzerVerwaltenTO.passwort(),
                benutzerVerwaltenTO.vorname(),
                benutzerVerwaltenTO.telefonnummer(),
                rolle
        );
         
          if (benutzer == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
           else {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path((benutzer.getBenutzerkennung()));
            return Response.created(uriBuilder.build()).build();
        }
     }

     @GET
     @Path("/benutzerAnzeigen")
     @Produces({MediaType.APPLICATION_JSON})
     public BenutzerTOList benutzerAnzeigen(){
         
         List<Benutzer> benutzerListe = benutzerVerwalten.benutzerAnzeigen();
         
       BenutzerTOList listTO;
       
       if(!benutzerListe.isEmpty()){
           listTO = new BenutzerTOList(
           benutzerListe.stream()
                   .map(benutzer ->
                   new BenutzerVerwaltenTO(benutzer.getBenutzerkennung()
                   ,""
                   ,benutzer.getVorname()
                   ,benutzer.getTelefonnummer()
                   ,benutzer.getRolle().toString()
                   )
                   
                   
                   ).collect(Collectors.toList())
           
           );
       }else{
           listTO = new BenutzerTOList(null);
       }
       return listTO;
         
     }
     
     @DELETE
     @Path("/benutzerLoeschen/{benutzerkennung}")
     public Response benutzerLoeschen(@PathParam("benutzerkennung") String benutzerkennung){
         
         if(benutzerVerwalten.benutzerLoeschen(benutzerkennung)){
             return Response.status(Response.Status.OK).build();
         }else{
             return null;
         }
     }
     
     
     @GET
     @Path("/getOneBenutzer/{benutzerkennung}")
     @Produces({MediaType.APPLICATION_JSON})
     public Benutzer getOneBenutzer(@PathParam("benutzerkennung") String benutzerkennung){
         return benutzerVerwalten.getOneBenutzer(benutzerkennung);
         
     }
     
    @PUT
    @Path("/benutzerUpdate")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response benutzerUpdate(BenutzerVerwaltenTO benutzerVerwaltenTO){
        
        Rolle rolle;
    try {
        rolle = Rolle.valueOf(benutzerVerwaltenTO.rolle().toUpperCase()); //Parse
    } catch (IllegalArgumentException | NullPointerException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Ungültige Rolle: " + benutzerVerwaltenTO.rolle())
                .build();
    }
        
        if(benutzerVerwalten.benutzerUpdate(benutzerVerwaltenTO.benutzerkennung(),
                benutzerVerwaltenTO.passwort(),
                benutzerVerwaltenTO.vorname(),
               benutzerVerwaltenTO.telefonnummer(),rolle)){
            return Response.status(Response.Status.OK).build();
        }else {
            return Response.status(Response.Status.CONFLICT).build(); 
        }
    }
    
     
                 
}           
                           
      
  

