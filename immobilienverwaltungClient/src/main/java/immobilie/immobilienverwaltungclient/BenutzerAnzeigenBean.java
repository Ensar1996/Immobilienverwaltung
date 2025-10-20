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
public class BenutzerAnzeigenBean implements Serializable {
    public static final long serialVersionUID =54353246L;
    
    private String benutzerkennung;
    private List<BenutzerAnzeigenTabelle> benutzerListe;

    private final String url = "http://localhost:8080/immobilienverwaltung/restapi";
    
    public class BenutzerAnzeigenTabelle{
    private String benutzerkennung;
    private String vorname;
    private String telefonnummer;
    private String rolle;

    public BenutzerAnzeigenTabelle(){
        
    }
    public BenutzerAnzeigenTabelle(String benutzerkennung, String vorname, String telefonnummer, String rolle){
        this.benutzerkennung = benutzerkennung;
        this.vorname = vorname;
        this.telefonnummer = telefonnummer;
        this.rolle = rolle;
    }
    
    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public void setBenutzerkennung(String benutzerkennung) {
        this.benutzerkennung = benutzerkennung;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
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
    }
    

    public List<BenutzerAnzeigenTabelle> getBenutzerListe() {
        return benutzerListe;
    }

    public void setBenutzerListe(List<BenutzerAnzeigenTabelle> benutzerListe) {
        this.benutzerListe = benutzerListe;
    }

       
    public String getBenutzerkennung() {
        return benutzerkennung;
    }

    public void setBenutzerkennung(String benutzerkennung) {
        this.benutzerkennung = benutzerkennung;
    }


    @PostConstruct
    public void init() {
        benutzerAnzeigen();
    }
    
    public String benutzerAnzeigen(){
       
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url).path("/benutzerAnzeigen");
    

    BenutzerTOList benutzerTOList =  target
            .request(MediaType.APPLICATION_JSON)
            .get(BenutzerTOList.class);
    
    List<BenutzerTO> getList = benutzerTOList.bList();
            
   
     benutzerListe = new ArrayList<>(); 
     
     if(getList != null){
         for(BenutzerTO benutzerTO : getList){
         benutzerListe.add(new BenutzerAnzeigenTabelle(
                 benutzerTO.benutzerkennung(), 
                 benutzerTO.vorname(), 
                 benutzerTO.telefonnummer(),
                 benutzerTO.rolle()));
         
     }
     }else{
         return null;
     }
        return "/benutzeranzeigen.xhtml";
    }
    
}
