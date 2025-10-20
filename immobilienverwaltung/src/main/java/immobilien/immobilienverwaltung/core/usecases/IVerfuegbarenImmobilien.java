/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Immobilien;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author ahmet
 */
@Local
public interface IVerfuegbarenImmobilien {
    public List<Immobilien> verfuegbarenImmobilienAnzeigen();
}
