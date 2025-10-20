/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;


import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
public class ImmobilienAnlegenBean implements Serializable {
     public static final long serialVersionUID =54353246L;
     
    private Adresse adresse = new Adresse();
     
     
     private double grosse;
     private String typ;
     private int baujahr;
     private String zustand;
     private double mietpreisProMonat;

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public double getGrosse() {
        return grosse;
    }

    public void setGrosse(double grosse) {
        this.grosse = grosse;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public String getZustand() {
        return zustand;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

    public double getMietpreisProMonat() {
        return mietpreisProMonat;
    }

    public void setMietpreisProMonat(double mietpreisProMonat) {
        this.mietpreisProMonat = mietpreisProMonat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
     
    @Inject
    private ImmobilienAnzeigenBean immobilienanzeigenbean;
    
    public void clearForm(){
        adresse= new Adresse();
        grosse=0;
        typ="";
        baujahr=0;
        zustand ="";
        mietpreisProMonat=0;
    }
     
    String url  = "http://localhost:8080/immobilienverwaltung/restapi";
    public String immobilienAnlegen(){
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/immobilienAnlegen");
    
    
    ImmobilienTO immobilienTO = new ImmobilienTO(-1, adresse, grosse, typ , baujahr, zustand, mietpreisProMonat);
        
     Response response = target.request(MediaType.APPLICATION_JSON)
     .post(Entity.entity(immobilienTO, MediaType.APPLICATION_JSON),Response.class);
             
    int  status = response.getStatus();
    
    if(status == 201){
        immobilienanzeigenbean.immobilienAnzeigen();
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg",""));
        clearForm();
        return immobilienanzeigenbean.immobilienAnzeigen();
        
    }

    return null;
}
}

