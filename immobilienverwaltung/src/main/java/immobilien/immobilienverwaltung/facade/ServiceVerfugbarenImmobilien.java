/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.facade;



import immobilien.immobilienverwaltung.core.entities.type.Vertragsstatus;
import immobilien.immobilienverwaltung.core.entities.Immobilien;
import immobilien.immobilienverwaltung.core.entities.Mietvertrag;
import immobilien.immobilienverwaltung.core.usecases.IImmobilienVerwalten;
import immobilien.immobilienverwaltung.core.usecases.IMietvertraegeVerwalten;
import immobilien.immobilienverwaltung.core.usecases.IVerfuegbarenImmobilien;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author ahmet
 */
@Path("/")
public class ServiceVerfugbarenImmobilien {
    
    
    @EJB IVerfuegbarenImmobilien verfuegbarenImmobilien;
    
    @EJB IImmobilienVerwalten immobilienverwalten;
    
    @EJB IMietvertraegeVerwalten mietvertragManager;
    
    private Vertragsstatus status;

    
    
@GET
@Path("/verfuegbarenImmobilienAnzeigen")
@Produces({MediaType.APPLICATION_JSON})
public ImmobilienTOList verfuegbarenImmobilienAnzeigen() {

    List<Immobilien> alleImmobilien = immobilienverwalten.immobilienAnzeigen();
    List<Mietvertrag> alleVertraege = mietvertragManager.alleMietvertraege();
    LocalDate heute = LocalDate.now();

  Set<Integer> aktuellVermieteteImmobilien = alleVertraege.stream()
    .filter(v -> v.getMietbeginn() != null && v.getImmobilien() != null)
    .filter(v -> {
        LocalDate beginn = v.getMietbeginn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ende = (v.getMietende() != null)
            ? v.getMietende().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            : LocalDate.MAX;

        return !beginn.isAfter(heute)
            && !ende.isBefore(heute)
            && v.getVertragsstatus() != Vertragsstatus.GEKUENDIGT;
    })
    .map(v -> v.getImmobilien().getImmobiliennummer())
    .collect(Collectors.toSet());



    List<Immobilien> verfuegbar = alleImmobilien.stream()
    .filter(i -> !aktuellVermieteteImmobilien.contains(i.getImmobiliennummer()))
    .collect(Collectors.toList());


    System.out.println("Verfügbare Immobilien: " + verfuegbar.size());

    List<ImmobilienAnzeige> anzeigen = verfuegbar.stream()
        .map(immobilien -> new ImmobilienAnzeige(
            immobilien.getImmobiliennummer(),
            immobilien.getAdresse().toString(),
            immobilien.getGroesse(),
            immobilien.getTyp().toString(),
            immobilien.getBaujahr(),
            immobilien.getZustand().toString(),
            immobilien.getMietpreisProMonat()
        ))
        .collect(Collectors.toList());

    return new ImmobilienTOList(anzeigen.isEmpty() ? null : anzeigen);
}


}
