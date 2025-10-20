/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;

/**
 *
 * @author ahmet
 */

@Named
@SessionScoped
public class BenutzerAnlegenBean implements Serializable {
     public static final long serialVersionUID =54353246L;
     
    private String benutzerkennung;
    private String passwort;
    private String name;
    private String telefonnummer;
    private String rolle;
    
    
    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public void setBenutzerkennung(String benutzerkennung) {
        this.benutzerkennung = benutzerkennung;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefonnumer() {
        return telefonnummer;
    }

    public void setTelefonnumer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
    
    public void clearForm() {
    benutzerkennung = "";
    passwort = "";
    name = "";
    telefonnummer = "";
    rolle = "";
}
     
    @Inject
    private BenutzerAnzeigenBean benutzerAnzeigenBean;
    
    String url  = "http://localhost:8080/immobilienverwaltung/restapi";
    public String benutzerAnlegen() {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/benutzerAnlegen");

    BenutzerTO benutzer = new BenutzerTO(benutzerkennung,passwort,name,telefonnummer,rolle);

    Response response = target.request(MediaType.APPLICATION_JSON)
     .post(Entity.entity(benutzer, MediaType.APPLICATION_JSON),Response.class);
    
    int status = response.getStatus();
    
    if(status == 201){
         benutzerAnzeigenBean.benutzerAnzeigen();
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Benutzer wurde erfolgreich angelegt."));
        clearForm();
        return benutzerAnzeigenBean.benutzerAnzeigen();
        
    }

   if (status == 400){
         FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Benutzer konnte nicht angelegt werden. Möglicherweise existiert er bereits."));
        
    }
   return null;
    }
    
}
