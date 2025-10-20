/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;

/**
 *
 * @author ahmet
 */
@Named
@SessionScoped
public class BenutzerBearbeitenBean implements Serializable {
    
    public static final long serialVersionUID =5435324688L;
     
    private String benutzerkennung;
    private String passwort;
    private String name;
    private String telefonnummer;
    private String rolle;
    
    private BenutzerTO aktuellerBenutzer;

    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public void setBenutzerkennung(String benutzerkennung) {
        this.benutzerkennung = benutzerkennung;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public BenutzerTO getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }

    public void setAktuellerBenutzer(BenutzerTO aktuellerBenutzer) {
        this.aktuellerBenutzer = aktuellerBenutzer;
    }
    String url  = "http://localhost:8080/immobilienverwaltung/restapi";

    @Inject
    private BenutzerAnzeigenBean benutzerAnzeigenBean; 
    
    public String getOneBenutzer(String benutzerkennung){
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/getOneBenutzer").path(benutzerkennung); 
    BenutzerTO benutzerTO =  target
            .request(MediaType.APPLICATION_JSON)
            .get(BenutzerTO.class);
   //this.aktuellerBenutzer = benutzerTO;
    this.benutzerkennung  = benutzerTO.benutzerkennung();
    this.passwort = benutzerTO.passwort();
    this.name = benutzerTO.vorname();
    this.telefonnummer = benutzerTO.telefonnummer();
    this.rolle = benutzerTO.rolle();
    
    return "benutzerbearbeiten2.xhtml?faces-redirect=true";
    
    }
    
    public String benutzerUpdate(){
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/benutzerUpdate");

    BenutzerTO benutzer = new BenutzerTO(benutzerkennung,passwort,name,telefonnummer,rolle);

    Response response = target.request(MediaType.APPLICATION_JSON)
     .put(Entity.entity(benutzer, MediaType.APPLICATION_JSON),Response.class);
    
    int status = response.getStatus();
    
    if(status == 200){
          benutzerAnzeigenBean.benutzerAnzeigen();
          return "benutzeranzeigen.xhtml?faces-redirect=true";
      }
      return null;
    }
    }
    

