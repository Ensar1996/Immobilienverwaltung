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
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */

@Named
@SessionScoped
public class MieterAnzeigenBean implements Serializable {
     public static final long serialVersionUID =54353246L;
     
   private List<MieterTO> mieterListe;
   
   
String url  = "http://localhost:8080/immobilienverwaltung/restapi";

public String mieterSuchen() {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/mieter");

    try {
        GenericType<List<MieterTO>> listType = new GenericType<List<MieterTO>>() {};
        mieterListe = target.request(MediaType.APPLICATION_JSON).get(listType);
        return "/mieteranzeigen.xhtml?faces-redirect=true";

    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Mieter konnten nicht geladen werden: " + e.getMessage()));
        return null;
    }
}
        public List<MieterTO> getMieterListe() {
        return mieterListe;
        }
    
}
