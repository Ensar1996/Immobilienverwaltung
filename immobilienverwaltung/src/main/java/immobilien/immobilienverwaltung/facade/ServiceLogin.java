/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;

import immobilien.immobilienverwaltung.core.usecases.ILoggen;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author ahmet
 */
@Path("/")
public class ServiceLogin {
    
    @EJB private ILoggen benutzerLogin;
    
    @GET
    @Path("/login/{benutzerkennung}/{passwort}")
    @Consumes ("text/plain")
    @Produces({MediaType.TEXT_PLAIN})
     public String einlogin(@PathParam("benutzerkennung") String benutzerkennung,@PathParam("passwort") String passwort ){
         
         if(benutzerLogin.einloggen(benutzerkennung, passwort)){
             return "true";
         }
         return "false";
     }      
    
     @GET
     @Path("/rolle/{benutzerkennung}")
     @Produces(MediaType.TEXT_PLAIN)
     public String getRolle(@PathParam("benutzerkennung") String benutzerkennung) {
        
         return benutzerLogin.getRolleVonBenutzer(benutzerkennung);
         
    }
     
}
