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
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.io.Serializable;
import java.util.List;


/**
 * 
 *
 * @author Ensar
 */


@Named
@SessionScoped
public class ReparaturauftragAnzeigenBean implements Serializable {
    private static final long serialVersionUID = 54353247L;

    private List<ReparaturauftragTO> auftraege;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi";

    public String auftraegeSuchen() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url).path("/reparaturauftrag");

        try {
            GenericType<List<ReparaturauftragTO>> listType = new GenericType<List<ReparaturauftragTO>>() {};
            auftraege = target.request(MediaType.APPLICATION_JSON).get(listType);
            return "/reparaturauftragverwalten.xhtml?faces-redirect=true";

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Reparaturaufträge konnten nicht geladen werden: " + e.getMessage()));
            return null;
        }
    }

    public List<ReparaturauftragTO> getAuftraege() {
        return auftraege;
    }

    public void setAuftraege(List<ReparaturauftragTO> auftraege) {
        this.auftraege = auftraege;
    }
}
