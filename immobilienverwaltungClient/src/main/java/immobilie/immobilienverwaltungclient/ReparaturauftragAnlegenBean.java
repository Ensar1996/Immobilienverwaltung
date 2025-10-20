/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ensar
 */
@Named
@SessionScoped
public class ReparaturauftragAnlegenBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String auftragsnummer;
    
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate wartungsdatum;
    private String beschreibung;
    private String massnahmen;
    private String wartungsstatus;
    private int immobiliennummer;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi";

    public String reparaturauftragAnlegen() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url).path("/reparaturauftrag");

        ReparaturauftragCreateTO auftrag = new ReparaturauftragCreateTO(
            auftragsnummer,
            wartungsdatum,
            beschreibung,
            massnahmen,
            wartungsstatus,
            immobiliennummer
        );

        Response response = target.request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(auftrag, MediaType.APPLICATION_JSON), Response.class);

        if (response.getStatus() == 201) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Reparaturauftrag erfolgreich angelegt!", null));
            return null; // oder redirect
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", 
                "Reparaturauftrag konnte nicht angelegt werden. Status: " + response.getStatus()));
            return null;
        }
    }

    // 🔽 Getter & Setter

    public String getAuftragsnummer() {
        return auftragsnummer;
    }

    public void setAuftragsnummer(String auftragsnummer) {
        this.auftragsnummer = auftragsnummer;
    }

    public LocalDate getWartungsdatum() {
        return wartungsdatum;
    }

    public void setWartungsdatum(LocalDate wartungsdatum) {
        this.wartungsdatum = wartungsdatum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getMassnahmen() {
        return massnahmen;
    }

    public void setMassnahmen(String massnahmen) {
        this.massnahmen = massnahmen;
    }

    public String getWartungsstatus() {
        return wartungsstatus;
    }

    public void setWartungsstatus(String wartungsstatus) {
        this.wartungsstatus = wartungsstatus;
    }

    public int getImmobiliennummer() {
        return immobiliennummer;
    }

    public void setImmobiliennummer(int immobiliennummer) {
        this.immobiliennummer = immobiliennummer;
    }
    
    public List<String> getWartungsstatusListe() {
    return List.of("OFFEN", "IN_ARBEIT", "ABGESCHLOSSEN");
    }
    
}