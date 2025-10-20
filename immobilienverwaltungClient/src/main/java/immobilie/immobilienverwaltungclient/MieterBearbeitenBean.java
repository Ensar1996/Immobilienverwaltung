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
public class MieterBearbeitenBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private MieterTO aktuellerMieter;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi/mieter";

    public MieterTO getAktuellerMieter() {
        return aktuellerMieter;
    }

    public void setAktuellerMieter(MieterTO aktuellerMieter) {
        this.aktuellerMieter = aktuellerMieter;
    }

    // Diese Methode wird vom Bearbeiten-Button aufgerufen
    public void vorbereiten(MieterTO mieter) {
        this.aktuellerMieter = mieter;
    }

    // Speichern über PUT-Request
    public void speichern() {
    System.out.println(">>> speichern() wurde aufgerufen");

    if (aktuellerMieter == null) {
        System.out.println(">>> aktuellerMieter ist NULL");
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "aktuellerMieter ist null."));
        return;
    }

    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path(String.valueOf(aktuellerMieter.getMieterid()));

    Response response = target
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(aktuellerMieter, MediaType.APPLICATION_JSON));

    int status = response.getStatus();
    String responseBody = response.readEntity(String.class);

    System.out.println(">>> HTTP-Status: " + status);
    System.out.println(">>> Response-Body: " + responseBody);

    if (status == 200 || status == 204) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Mieter wurde erfolgreich aktualisiert.", null));
    } else {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Aktualisierung fehlgeschlagen. Status: " + status));
    }

    client.close();
}

}
