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
public class ImmobilienBearbeitenBean implements Serializable {
  
        public static final long serialVersionUID =5435324688L;

     private  int immobiliennummer;   
     private Adresse adresse = new Adresse();
     private double grosse;
     private String typ;
     private int baujahr;
     private String zustand;
     private double mietpreisProMonat;

     private ImmobilienTO immobilienTO;
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

    public ImmobilienTO getImmobilienTO() {
        return immobilienTO;
    }

    public void setImmobilienTO(ImmobilienTO immobilienTO) {
        this.immobilienTO = immobilienTO;
    
    }

    public int getImmobiliennummer() {
        return immobiliennummer;
    }

    public void setImmobiliennummer(int immobiliennummer) {
        this.immobiliennummer = immobiliennummer;
    }
     
    @Inject
    private ImmobilienAnzeigenBean immobilienAnzeigen;
    
    
    
      String url  = "http://localhost:8080/immobilienverwaltung/restapi";

    public String getOneImmobilien(int immobiliennummer){
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/getOneImmobilien").path(String.valueOf(immobiliennummer)); 
   
     ImmobilienTO immoTO =  target
            .request(MediaType.APPLICATION_JSON)
            .get(ImmobilienTO.class);
    this.immobiliennummer = immoTO.immobiliennummer();
    this.adresse = immoTO.adresse();
    this.grosse = immoTO.grosse();
    this.typ = immoTO.typ();
    this.baujahr = immoTO.baujahr();
    this.zustand = immoTO.zustand();
    this.mietpreisProMonat = immoTO.mietpreisProMonat();
        return "immobilienbearbeiten.xhtml?faces-redirect=true";
    }
    
    public String immobilienUpdate(){
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/immobilienUpdate");
    
    ImmobilienTO immoTO = new ImmobilienTO(immobiliennummer,adresse,grosse,typ,baujahr,zustand, mietpreisProMonat);
    
     Response response = target.request(MediaType.APPLICATION_JSON)
     .put(Entity.entity(immoTO, MediaType.APPLICATION_JSON),Response.class);
     
      
    int status = response.getStatus();
    
    if(status == 200){
        immobilienAnzeigen.immobilienAnzeigen();
          return "immobilienbearbeitenundloeschen.xhtml?faces-redirect=true";
      }
      return null;
    }
}
