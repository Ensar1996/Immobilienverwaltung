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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ensar
 */
@Named
@SessionScoped
public class MietvertragKuendigenBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int vertragsnummer;
    private Date kuendigungsdatum;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi";

   public String mietvertragKuendigen() {
    try {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url)
            .path("/mietvertrag/" + vertragsnummer + "/kuendigen")
            .queryParam("datum", new SimpleDateFormat("yyyy-MM-dd").format(kuendigungsdatum));

        Response response = target.request().put(Entity.text(""));

        if (response.getStatus() == 200) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,  null, "Mietvertrag erfolgreich gekündigt!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler beim Kündigen!", "Status: " + response.getStatus()));
        }
    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exception", e.getMessage()));
        e.printStackTrace(); // wichtig fürs Server-Log
    }

    return null;
}


  
    public int getVertragsnummer() {
        return vertragsnummer;
    }

    public void setVertragsnummer(int vertragsnummer) {
        this.vertragsnummer = vertragsnummer;
    }

    public Date getKuendigungsdatum() {
        return kuendigungsdatum;
    }

    public void setKuendigungsdatum(Date kuendigungsdatum) {
        this.kuendigungsdatum = kuendigungsdatum;
    }
}