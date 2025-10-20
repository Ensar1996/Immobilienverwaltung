/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmet
 */
@Named
@SessionScoped
public class ImmobilienAnzeigenBean implements Serializable{
     private static final Long serialVersionUID = 2L;
    
     private int immobiliennummer;
     List<ImmobilienAnzeigenTabelle> immobilienListe; 
     private final String url = "http://localhost:8080/immobilienverwaltung/restapi";
     
     public class ImmobilienAnzeigenTabelle{
         private int immobiliennummer;
         private String adresse;
         private double grosse;
         private String typ;
         private int baujahr;
         private String zustand;
         private double mietpreisProMonat;
         private int vertragsnummer;
         
         
         public ImmobilienAnzeigenTabelle(){
           
        } 

        public ImmobilienAnzeigenTabelle(int immobiliennummer, String adresse, double grosse, String typ, int baujahr, String zustand, double mietpreisProMonat, int vertragsnummer) {
            this.immobiliennummer = immobiliennummer;
            this.adresse = adresse;
            this.grosse = grosse;
            this.typ = typ;
            this.baujahr = baujahr;
            this.zustand = zustand;
            this.mietpreisProMonat = mietpreisProMonat;
            this.vertragsnummer = vertragsnummer;
        }
        

        public int getImmobiliennummer() {
            return immobiliennummer;
        }

        public void setImmobiliennummer(int immobiliennummer) {
            this.immobiliennummer = immobiliennummer;
        }

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
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

        public int getVertragsnummer() {
            return vertragsnummer;
        }

        public void setVertragsnummer(int vertragsnummer) {
            this.vertragsnummer = vertragsnummer;
        }
        
         
         
     }

    public int getImmobiliennummer() {
        return immobiliennummer;
    }

    public void setImmobiliennummer(int immobiliennummer) {
        this.immobiliennummer = immobiliennummer;
    }

    public List<ImmobilienAnzeigenTabelle> getImmobilienListe() {
    immobilienAnzeigen(); 
    return immobilienListe;
}

    public void setImmobilienListe(List<ImmobilienAnzeigenTabelle> immobilienListe) {
        this.immobilienListe = immobilienListe;
    }
     
   
    @PostConstruct
    public void init() {
        immobilienAnzeigen();
    }
    
    
    public String immobilienAnzeigen(){
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/immobilienAnzeigen");
    
    ImmobilienTOList immobilienList = target
            .request(MediaType.APPLICATION_JSON)
            .get(ImmobilienTOList.class);
    
    List<ImmobilienAnzeige> getImmobilienList = immobilienList.immobilienList();
    
    immobilienListe = new ArrayList();
    
    if(getImmobilienList != null){
        for(ImmobilienAnzeige immobilienanzeige : getImmobilienList){
            immobilienListe.add(new ImmobilienAnzeigenTabelle(
                 immobilienanzeige.immobiliennummer(),
                    immobilienanzeige.adresse(),
                    immobilienanzeige.grosse(),
                    immobilienanzeige.typ(),
                    immobilienanzeige.baujahr(),
                    immobilienanzeige.zustand(),
                    immobilienanzeige.mietpreisProMonat(),
                    immobilienanzeige.vertragsnummer()));
        }
    }else{
        return null;
    }
      return "/immobilienanzeigen.xhtml?faces-redirect=true";
    }
    
    
}
