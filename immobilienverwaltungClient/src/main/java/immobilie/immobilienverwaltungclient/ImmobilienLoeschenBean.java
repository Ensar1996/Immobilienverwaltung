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

import jakarta.ws.rs.client.WebTarget;

import jakarta.ws.rs.core.Response;

import java.io.Serializable;
 
/**

*

* @author ahmet

*/
 
@Named

@SessionScoped

public class ImmobilienLoeschenBean implements Serializable {

    private static final long serialVersionUID =2153246L;

    private int immobiliennummer;

    String url  = "http://localhost:8080/immobilienverwaltung/restapi";
 
    public int getImmobiliennummer() {

        return immobiliennummer;

    }
 
    public void setImmobiliennummer(int immobiliennummer) {

        this.immobiliennummer = immobiliennummer;

    }

    @Inject

    private ImmobilienAnzeigenBean immobilienAnzeigen;

     public String immobilienLoeschen(){

    Client client = ClientBuilder.newClient();

    WebTarget target = client.target(url).path("/immobilienLoeschen/{immobiliennummer}").resolveTemplate("immobiliennummer", immobiliennummer);

     Response response = target.request().delete();

    if (response.getStatus() == 500 ) {

    String fehlermeldung = response.readEntity(String.class);

    FacesContext.getCurrentInstance().addMessage(null,

        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler -> Diese Immobilie ist mit einem aktiven Mietvertrag verbunden daher darf nicht gelöscht werden!", fehlermeldung));

    return "immobilienbearbeitenundloeschen.xhtml?faces-redirect=true";

}
 
      if(response.getStatus() == 204){

          immobilienAnzeigen.immobilienAnzeigen();

          return "immobilienbearbeitenundloeschen.xhtml?faces-redirect=true";

      }

      return null;

    }

}
 