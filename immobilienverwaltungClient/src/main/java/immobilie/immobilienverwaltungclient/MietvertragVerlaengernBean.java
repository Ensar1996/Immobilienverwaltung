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
public class MietvertragVerlaengernBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int vertragsnummer;
    private Date neuesEnddatum;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi";

    public String mietvertragVerlaengern() {
        try {
            Client client = ClientBuilder.newClient();

            // Datum in yyyy-MM-dd formatieren
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String datumStr = formatter.format(neuesEnddatum);

            // API-Aufruf vorbereiten
            WebTarget target = client.target(url)
                .path("/mietvertrag/" + vertragsnummer + "/verlaengern")
                .queryParam("datum", datumStr);

            // PUT-Request senden
            Response response = target
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.text(""));

            if (response.getStatus() == 200) {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Mietvertrag wurde erfolgreich verlängert."));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Verlängerung fehlgeschlagen. Status: " + response.getStatus()));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exception", "Fehler beim Verlängern: " + e.getMessage()));
            return null;
        }
    }

    // Getter & Setter
    public int getVertragsnummer() {
        return vertragsnummer;
    }

    public void setVertragsnummer(int vertragsnummer) {
        this.vertragsnummer = vertragsnummer;
    }

    public Date getNeuesEnddatum() {
        return neuesEnddatum;
    }

    public void setNeuesEnddatum(Date neuesEnddatum) {
        this.neuesEnddatum = neuesEnddatum;
    }
}
