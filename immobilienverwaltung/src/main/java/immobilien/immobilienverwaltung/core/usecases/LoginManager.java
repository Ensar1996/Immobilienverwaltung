/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Benutzer;
import immobilien.immobilienverwaltung.dataaccess.BenutzerDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author ahmet
 */
@Stateless
public class LoginManager {
    
    @EJB BenutzerDAO bDao;
    
    public Benutzer findBenutzer (String benutzerkennung){
        return bDao.findBenutzer(benutzerkennung);
    }
}
