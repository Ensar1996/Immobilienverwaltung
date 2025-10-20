/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Immobilien;
import immobilien.immobilienverwaltung.dataaccess.VerfuegbarenImmobilienDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author ahmet
 */
@Stateless
public class VerfuegbarenImmobilienManager {
    @EJB private VerfuegbarenImmobilienDAO verfuegbarenimmobilienDao;
    
    public List<Immobilien> verfuegbarenImmobilienAnzeigen(Immobilien immobilien){
        return verfuegbarenimmobilienDao.verfuegbarenImmobilienAnzeigen(immobilien);
    }
}
