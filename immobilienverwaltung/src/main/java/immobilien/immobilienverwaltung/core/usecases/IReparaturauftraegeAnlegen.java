/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Reparaturauftrag;
import jakarta.ejb.Local;

/**
 *
 * @author Ensar
 */

@Local
public interface IReparaturauftraegeAnlegen {
    Reparaturauftrag reparaturauftraegeAnlegen(Reparaturauftrag auftrag);
}
