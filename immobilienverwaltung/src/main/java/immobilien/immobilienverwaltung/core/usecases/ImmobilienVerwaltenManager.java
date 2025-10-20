/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Immobilien;
import immobilien.immobilienverwaltung.dataaccess.ImmobilienDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author ahmet
 */
@Stateless
public class ImmobilienVerwaltenManager {
    
    @EJB private ImmobilienDAO immobilienDao;
    
    public Immobilien immobilienAnlegen(Immobilien immobilien){
        return immobilienDao.immobilienAnlegen(immobilien);
    }
    public boolean immobilienLoeschen(int immobiliennummer){
        return immobilienDao.immobilienLoeschen(immobiliennummer) ;
    }
    public boolean immibilienUpdate(Immobilien immobilien){
        return immobilienDao.immobilienUpdate(immobilien);
    }
    public List<Immobilien> immobilienAnzeigen(Immobilien immobilien){
        return immobilienDao.immobilienAnzeigen(immobilien);
    }
    public Immobilien getOneImmobilien(int immobiliennummer){
        return immobilienDao.getOneImmobilien(immobiliennummer);
    }
    
}
