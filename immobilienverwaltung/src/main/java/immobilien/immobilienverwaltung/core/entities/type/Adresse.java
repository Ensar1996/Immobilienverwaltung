/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.entities.type;

import jakarta.persistence.Convert;
import java.io.Serializable;


/**
 *
 * @author ensar
 */
@Convert
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    
  private String strasse;
  private String hausnummer;
  private String plz;
  private String stadt;
    
     public Adresse(){
         
     }
  
    public Adresse(String strasse, String hausnummer, String plz, String stadt) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.stadt = stadt;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }
  
  @Override
  public String toString(){
      return strasse + " " + hausnummer + "," + plz + " " + stadt;
  }
  
    
}
