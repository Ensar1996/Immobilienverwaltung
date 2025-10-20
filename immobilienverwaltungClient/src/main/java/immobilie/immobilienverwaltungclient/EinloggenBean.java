/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import java.io.Serializable;

/**
 *
 * @author ahmet
 */

@Named
@SessionScoped
public class EinloggenBean implements Serializable {
    public static final long serialVersionUID =5435324634636L;
    
    private String benutzerkennung;
    private String passwort;
    private String rolle;
    String url  = "http://localhost:8080/immobilienverwaltung/restapi";

    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public String getPasswort() {
        return passwort;
    }
  
    public void setBenutzerkennung(String benutzerkennung) {
        this.benutzerkennung = benutzerkennung;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    @Inject
    private SessionUser sessionUser;
    
    public String einloggen() {
    Client client = ClientBuilder.newClient();

    // 1. Login-Prüfung
    String loginUrl = url + "/login/" + benutzerkennung + "/" + passwort;
    String loginResponse = client.target(loginUrl).request(MediaType.TEXT_PLAIN).get(String.class);

    if (loginResponse.equals("true")) {
        // 2. Rolle vom Server abrufen
        String rolleUrl = url + "/rolle/" + benutzerkennung;
        String serverRolle = client.target(rolleUrl).request(MediaType.TEXT_PLAIN).get(String.class);

        if (!serverRolle.equalsIgnoreCase(rolle)) {
            // Rollenkonflikt
            FacesContext.getCurrentInstance().addMessage(null,
                new jakarta.faces.application.FacesMessage(jakarta.faces.application.FacesMessage.SEVERITY_ERROR,
                    "Zugriff verweigert: Rolle stimmt nicht mit Benutzerrolle überein.", null));
            return null; // bleibe auf der Login-Seite
        }
        
       

        // 3. Erfolgreich eingeloggt
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("benutzerkennung", benutzerkennung);
        session.setAttribute("passwort", passwort);
        session.setAttribute("rolle", rolle); // eingegebene Rolle, passt zur echten Rolle

        return "/homepage.xhtml";
    } else {
        // Falsches Passwort oder Benutzername
        return "/loginError.xhtml";
    }
}

    
    
    
}
