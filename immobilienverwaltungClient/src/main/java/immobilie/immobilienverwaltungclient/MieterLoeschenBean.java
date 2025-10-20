package immobilie.immobilienverwaltungclient;

import immobilie.immobilienverwaltungclient.MieterAnzeigenBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;

/**
 *
 * @author Ensar
 */


@Named
@SessionScoped
public class MieterLoeschenBean implements Serializable {
    private static final long serialVersionUID = 54353247L;

   
    
    private final String url = "http://localhost:8080/immobilienverwaltung/restapi";

    
    public String mieterLoeschen(int mieterid) {
    System.out.println(">>> Mieter löschen: " + mieterid);

    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/mieterdaten").path(String.valueOf(mieterid));

    Response response = target.request().delete();
    int status = response.getStatus();

    if (status == 200) {
        // Erfolgsmeldung
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Mieter wurde erfolgreich gelöscht", null));
       

        // Mieter auch aus der Client-Liste entfernen
        MieterAnzeigenBean anzeigenBean = FacesContext.getCurrentInstance()
                .getApplication()
                .evaluateExpressionGet(FacesContext.getCurrentInstance(),
                        "#{mieterAnzeigenBean}", MieterAnzeigenBean.class);

        // Suche & entferne aus Liste
        anzeigenBean.getMieterListe().removeIf(m -> m.getMieterid() == mieterid);

    } else {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Löschen fehlgeschlagen. Status: " + status));
    }

    return null;
}
}
