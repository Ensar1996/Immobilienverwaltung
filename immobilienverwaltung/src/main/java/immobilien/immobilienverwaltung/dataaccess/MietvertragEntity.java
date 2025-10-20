package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import immobilien.immobilienverwaltung.core.entities.type.Vertragsstatus;
import jakarta.persistence.*;
import immobilien.immobilienverwaltung.dataaccess.ImmobilienEntity;

import java.util.Date;

@Entity
@Table(name = "mietvertrag")
public class MietvertragEntity {

    @Id
    @SequenceGenerator(name = "vertrag_seq", sequenceName = "VERTRAGSNUMMER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vertrag_seq")
    @Column(name = "vertragsnummer")
    private int vertragsnummer;
   

    @Temporal(TemporalType.DATE)
    private Date mietbeginn;

    @Temporal(TemporalType.DATE)
    private Date mietende;

    @Enumerated(EnumType.STRING)
    @Column(name = "vertragsstatus")
    private Vertragsstatus vertragsstatus;

    @Column(name = "monatliche_miete")
    private double monatlicheMiete;

    private double kaution;
    
    @ManyToOne
    @JoinColumn(name = "immobiliennummer", referencedColumnName = "immobiliennummer")
    private ImmobilienEntity immobilie;
    
   


    @ManyToOne
    @JoinColumn(name = "mietnummer", referencedColumnName = "mietnummer")
    private MieterEntity mieter;

 

    public MietvertragEntity() {}

    public MietvertragEntity(Mietvertrag vertrag) {
        
        this.mietbeginn = vertrag.getMietbeginn();
        this.mietende = vertrag.getMietende();
        this.monatlicheMiete = vertrag.getMonatlicheMiete();
        this.kaution = vertrag.getKaution();
        this.vertragsstatus = vertrag.getVertragsstatus(); // Enum wird direkt übernommen
        this.mieter = new MieterEntity(vertrag.getMieter());
        this.immobilie = new ImmobilienEntity(vertrag.getImmobilien());
    
    }

   public Mietvertrag toMietvertrag() {
    return new Mietvertrag(
            vertragsnummer,
            mietbeginn,
            mietende,
            monatlicheMiete,
            kaution,
            vertragsstatus, // Enum wird direkt übergeben
            mieter != null ? mieter.toMieter() : null,
            immobilie != null ? immobilie.toImmobilien() : null
           // immobilie != null ? immobilie.toImmobilie() : null
    );
}


    // Getter & Setter

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

    public MieterEntity getMieterentity() {
        return mieter;
    }

    public void setMieterentity(MieterEntity mieter) {
        this.mieter = mieter;
    }
    
    
     
    public ImmobilienEntity getImmobilieentity() {
        return immobilie;
    }

    public void setImmobilieentity(ImmobilienEntity immobilien) {
        this.immobilie = immobilie;
    } 
}
