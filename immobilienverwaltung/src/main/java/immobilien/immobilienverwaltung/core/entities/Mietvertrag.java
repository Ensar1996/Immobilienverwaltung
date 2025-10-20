
//**
package immobilien.immobilienverwaltung.core.entities;

import java.util.Date;
import immobilien.immobilienverwaltung.core.entities.type.Vertragsstatus;
import java.io.Serializable;

/**
 *
 * @author Ensar
 */

public class Mietvertrag {

    private int vertragsnummer;
    private Date mietbeginn;
    private Date mietende;
    private double monatlicheMiete;
    private double kaution;
    private Vertragsstatus vertragsstatus;
    private Mieter mieter;
    private Immobilien immobilien;
   
   
    

    public Mietvertrag(int vertragsnummer, Date mietbeginn, Date mietende, double monatlicheMiete,
                   double kaution, Vertragsstatus vertragsstatus, Mieter mieter, Immobilien immobilien) {
    this.vertragsnummer = vertragsnummer;
    this.mietbeginn = mietbeginn;
    this.mietende = mietende;
    this.monatlicheMiete = monatlicheMiete;
    this.kaution = kaution; 
    this.vertragsstatus = vertragsstatus;
    this.mieter = mieter;
    this.immobilien = immobilien;
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

    
    public Vertragsstatus getVertragsstatus() {
        return vertragsstatus;
    }

     public void setVertragsstatus(Vertragsstatus vertragsstatus) {
        this.vertragsstatus = vertragsstatus;
    } 

    public Mieter getMieter() {
        return mieter;
    }

    public void setMieter(Mieter mieter) {
        this.mieter = mieter;
    }
    
    
    public Immobilien getImmobilien () {
        return immobilien;
}
     public void setImmobilien(Immobilien immobilien) {
        this.immobilien = immobilien;
    }

    
}


