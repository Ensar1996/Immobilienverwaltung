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
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */


@Named
@SessionScoped
public class MietvertragAnlegenBean implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private Date mietbeginn;
    private Date mietende;
    private double monatlicheMiete;
    private double kaution;
    private String vertragsstatus;
    private int mieterId;
    private int immobiliennummer;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi";

    public String mietvertragAnlegen() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url).path("/mietvertrag");

        MietvertragCreateTO mietvertrag = new MietvertragCreateTO(
            vertragsstatus,
            mietbeginn,
            mietende,
            monatlicheMiete,
            kaution,
            mieterId,                               
            immobiliennummer
        );

        Response response = target.request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(mietvertrag, MediaType.APPLICATION_JSON), Response.class);

        if (response.getStatus() == 201) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Mietvertrag erfolgreich angelegt!", null));
            return null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Mietvertrag konnte nicht angelegt werden. Serverstatus: " + response.getStatus()));
            return null;
        }
    }

    // Getter & Setter

    
    public Date getMietbeginn() {
        return mietbeginn;
    }

    public void setMietbeginn(Date mietbeginn) {
        this.mietbeginn = mietbeginn;
    }

    public Date getMietende() {
        return mietende;
    }

    public void setMietende(Date mietende) {
        this.mietende = mietende;
    }

    public double getMonatlicheMiete() {
        return monatlicheMiete;
    }

    public void setMonatlicheMiete(double monatlicheMiete) {
        this.monatlicheMiete = monatlicheMiete;
    }

    public double getKaution() {
        return kaution;
    }

    public void setKaution(double kaution) {
        this.kaution = kaution;
    }

    public String getVertragsstatus() {
        return vertragsstatus;
    }

    public void setVertragsstatus(String vertragsstatus) {
        this.vertragsstatus = vertragsstatus;
    }

    public int getMieterId() {
        return mieterId;
    }

    public void setMieterId(int mieterId) {
        this.mieterId = mieterId;
    }
    
    public List<String> getVertragsstatusList() {
    return List.of("AKTIV", "GEKÜNDIGT", "AUSSTEHEND");
}
    
   public int getImmobiliennummer() {
    return immobiliennummer;
}

    public void setImmobiliennummer(int immobiliennummer) {
    this.immobiliennummer = immobiliennummer;
} 
}
