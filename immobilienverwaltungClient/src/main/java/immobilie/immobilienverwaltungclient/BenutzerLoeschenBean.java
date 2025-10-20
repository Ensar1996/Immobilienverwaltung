/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import immobilie.immobilienverwaltungclient.BenutzerAnzeigenBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;

/**
 *
 * @author ahmet
 */
@Named
@SessionScoped
public class BenutzerLoeschenBean implements Serializable {
         public static final long serialVersionUID =215435983246L;

          private String benutzerkennung;
          String url  = "http://localhost:8080/immobilienverwaltung/restapi";

    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public void setBenutzerkennung(String benutzerkennung) {
        this.benutzerkennung = benutzerkennung;
    }
    
    @Inject
    private BenutzerAnzeigenBean benutzerAnzeigenBean; 
          
    public String benutzerLoeschen(){
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/benutzerLoeschen/{benutzerkennung}").resolveTemplate("benutzerkennung", benutzerkennung);
      
      Response response = target.request().delete();
      
      if(response.getStatus() == Response.Status.CONFLICT.getStatusCode()){
          String meldung = response.readEntity(String.class);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", meldung));
          return null;
      }
      if(response.getStatus() == 204){
          benutzerAnzeigenBean.benutzerAnzeigen();
          return "benutzerloeschen.xhtml?faces-redirect=true";
      }
      return null;
    }
         
}
