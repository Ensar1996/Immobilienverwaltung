/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ensar
 */
@Named
@SessionScoped
public class MieterAnlegenBean implements Serializable {
    public static final long serialVersionUID =54353246L;
     
    private Adresse adresse = new Adresse(); // <- FEHLT!

    private int mieterid;
    private String name;
    private String telefonnummer;
    private String email;
    private Date geburtsdatum;
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
   
  
    String url  = "http://localhost:8080/immobilienverwaltung/restapi";
    
    public String mieterAnlegen() {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/mieter");

    MieterTO mieter = new MieterTO(mieterid, name, adresse, telefonnummer, email, geburtsdatum);
    System.out.println(">>> Methode mieterAnlegen() wird aufgerufen <<<");

    Response response = target.request(MediaType.APPLICATION_JSON)
     .post(Entity.entity(mieter, MediaType.APPLICATION_JSON),Response.class);
    
    int status = response.getStatus();
    
    if (status == 201) {
    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);    
    FacesContext.getCurrentInstance().addMessage(null,
         new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Mieter wurde erfolgreich angelegt."));
    return null;
    } else {
    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Mieter konnte nicht angelegt werden. Serverstatus: " + status));
    return null;
}
}
    
    
}
