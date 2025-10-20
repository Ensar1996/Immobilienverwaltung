/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.Serializable;

/**
 *
 * @author Ensar
 */
@Named
@SessionScoped
public class ReparaturauftragBearbeitenBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ReparaturauftragTO aktuellerAuftrag;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi/reparaturauftrag";

    public ReparaturauftragTO getAktuellerAuftrag() {
        return aktuellerAuftrag;
    }

    public void setAktuellerAuftrag(ReparaturauftragTO aktuellerAuftrag) {
        this.aktuellerAuftrag = aktuellerAuftrag;
    }

    // Methode wird beim Klick auf "Bearbeiten" aufgerufen
    public void vorbereiten(ReparaturauftragTO auftrag) {
        this.aktuellerAuftrag = auftrag;
    }

    // Methode zum Speichern der Änderungen (PUT)
    public void speichern() {
        System.out.println(">>> speichern() wurde aufgerufen");

        if (aktuellerAuftrag == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "aktuellerAuftrag ist null."));
            return;
        }

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url).path(String.valueOf(aktuellerAuftrag.getAuftragsnummer()));


        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(aktuellerAuftrag, MediaType.APPLICATION_JSON));

        int status = response.getStatus();
        String responseBody = response.readEntity(String.class);

        System.out.println(">>> HTTP-Status: " + status);
        System.out.println(">>> Response-Body: " + responseBody);

        if (status == 200 || status == 204) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Reparaturauftrag aktualisiert.", null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Aktualisierung fehlgeschlagen. Status: " + status));
        }
        
        

        client.close();
    }
}