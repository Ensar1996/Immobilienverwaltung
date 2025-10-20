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
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;

/**
 *
 * @author Ensar
 */

@Named
@SessionScoped
public class ReparaturauftragLoeschenBean implements Serializable {

    private static final long serialVersionUID = 2345345345L;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi";

    public String loescheReparaturauftrag(String auftragsnummer) {
        System.out.println(">>> Auftrag löschen: " + auftragsnummer);

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url).path("/reparaturauftrag").path(auftragsnummer);

        Response response = target.request().delete();
        int status = response.getStatus();

        if (status == 204 || status == 200) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Reparaturauftrag gelöscht", null));

            // Aktualisiere die Anzeige-Bean, wenn vorhanden
            ReparaturauftragAnzeigenBean anzeigenBean = FacesContext.getCurrentInstance()
                    .getApplication()
                    .evaluateExpressionGet(FacesContext.getCurrentInstance(),
                            "#{reparaturauftragAnzeigenBean}", ReparaturauftragAnzeigenBean.class);

            anzeigenBean.getAuftraege().removeIf(a -> a.getAuftragsnummer().equals(auftragsnummer));

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Löschen fehlgeschlagen. Status: " + status));
        }

        return null;
    }
}