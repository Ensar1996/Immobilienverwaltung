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
public class ReparaturanfaelligeWohnungenBean implements Serializable {
    
    private static final Long serialVersionUID = 2L;
    
     private int immobiliennummer;
     List<ReparaturanfaelligeWohnungenBean.ReparaturenImmobilienAnzeigenTabelle> immobilienListe; 
     private final String url = "http://localhost:8080/immobilienverwaltung/restapi";
     
     
     public class ReparaturenImmobilienAnzeigenTabelle{
         private int immobiliennummer;
         private String adresse;
         private double grosse;
         private String typ;
         private int baujahr;
         private String zustand;
         private double mietpreisProMonat;
         
         public ReparaturenImmobilienAnzeigenTabelle(){
           
        } 
         
         public ReparaturenImmobilienAnzeigenTabelle(int immobiliennummer, String adresse, double grosse, String typ, int baujahr, String zustand, double mietpreisProMonat) {
            this.immobiliennummer = immobiliennummer;
            this.adresse = adresse;
            this.grosse = grosse;
            this.typ = typ;
            this.baujahr = baujahr;
            this.zustand = zustand;
            this.mietpreisProMonat = mietpreisProMonat;
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
     }
         
     
     public int getImmobiliennummer() {
        return immobiliennummer;
    }

    public void setImmobiliennummer(int immobiliennummer) {
        this.immobiliennummer = immobiliennummer;
    }


    public void setImmobilienListe(List<ReparaturanfaelligeWohnungenBean.ReparaturenImmobilienAnzeigenTabelle> immobilienListe) {
        this.immobilienListe = immobilienListe;
    }
     
    
    
    
    public void aktualisiereImmobilienListe() {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/reparaturenImmobilienAnzeigen");

    ImmobilienTOList immobilienList = target
            .request(MediaType.APPLICATION_JSON)
            .get(ImmobilienTOList.class);

    List<ImmobilienAnzeige> getverfuegbarenImmobilienList = immobilienList.immobilienList();

    immobilienListe = new ArrayList<>();

    if (getverfuegbarenImmobilienList != null) {
        for (ImmobilienAnzeige immobilienanzeige : getverfuegbarenImmobilienList) {
            immobilienListe.add(new ReparaturenImmobilienAnzeigenTabelle(
                    immobilienanzeige.immobiliennummer(),
                    immobilienanzeige.adresse(),
                    immobilienanzeige.grosse(),
                    immobilienanzeige.typ(),
                    immobilienanzeige.baujahr(),
                    immobilienanzeige.zustand(),
                    immobilienanzeige.mietpreisProMonat()));
        }
    }
}

      public List<ReparaturanfaelligeWohnungenBean.ReparaturenImmobilienAnzeigenTabelle> getImmobilienListe() {
    aktualisiereImmobilienListe(); // Holt aktuelle Daten
    return immobilienListe;
}
    
    
   
    @PostConstruct
    public void init() {
        reparaturenImmobilienAnzeigen();
    }
    
    
    
     
    public String reparaturenImmobilienAnzeigen(){
        
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/reparaturenImmobilienAnzeigen");
    
    ImmobilienTOList immobilienList = target
            .request(MediaType.APPLICATION_JSON)
            .get(ImmobilienTOList.class);
    
    List<ImmobilienAnzeige> getverfuegbarenImmobilienList = immobilienList.immobilienList();
    
    immobilienListe = new ArrayList();
    
    if(getverfuegbarenImmobilienList != null){
        for(ImmobilienAnzeige immobilienanzeige : getverfuegbarenImmobilienList){
            immobilienListe.add(new ReparaturenImmobilienAnzeigenTabelle(
               immobilienanzeige.immobiliennummer(),
                    immobilienanzeige.adresse(),
                    immobilienanzeige.grosse(),
                    immobilienanzeige.typ(),
                    immobilienanzeige.baujahr(),
                    immobilienanzeige.zustand(),
                    immobilienanzeige.mietpreisProMonat()));
        }
    }else{
        return null;
    }
      return "/reparaturanfaelligewohnungen.xhtml?faces-redirect=true";
    }
    
    
    
    
}
