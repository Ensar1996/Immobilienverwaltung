/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilie.immobilienverwaltungclient;

import java.util.Date;


/**
 *
 * @author Ensar
 */


public class MietvertragTO {

    private String vertragsstatus;
    private int vertragsnummer;
    private Date mietbeginn;
    private Date mietende;
    private double monatlicheMiete;
    private double kaution;
    private String mieterName;
    private int immobiliennummer;

    // Standard-Konstruktor (wird z. B. für JSON benötigt)
    public MietvertragTO() {}

    public MietvertragTO(String vertragsstatus, int vertragsnummer, Date mietbeginn, Date mietende,
                         double monatlicheMiete, double kaution, String mieterName, int immobiliennummer) {
        this.vertragsstatus = vertragsstatus;
        this.vertragsnummer = vertragsnummer;
        this.mietbeginn = mietbeginn;
        this.mietende = mietende;
        this.monatlicheMiete = monatlicheMiete;
        this.kaution = kaution;
        this.mieterName = mieterName;
        this.immobiliennummer = immobiliennummer;
          } 

    // Getter & Setter

    public String getVertragsstatus() {
        return vertragsstatus;
    }

    public void setVertragsstatus(String vertragsstatus) {
        this.vertragsstatus = vertragsstatus;
    }

    public int getVertragsnummer() {
        return vertragsnummer;
    }

    public void setVertragsnummer(int vertragsnummer) {
        this.vertragsnummer = vertragsnummer;
    }

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

    public String getMieterName() {
        return mieterName;
    }

    public void setMieterName(String mieterName) {
        this.mieterName = mieterName;
    }
    
    public int getImmobiliennummer() {
    return immobiliennummer;
}

    public void setImmobiliennummer(int immobiliennummer) {
    this.immobiliennummer = immobiliennummer;
}
}
