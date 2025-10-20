/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.usecases;

import immobilien.immobilienverwaltung.core.entities.Reparaturauftrag;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author Ensar
 */


@Stateless
public class ReparaturauftraegeAnlegen implements IReparaturauftraegeAnlegen {

    @EJB
    private ReparaturauftragManager reparaturauftragManager;

    @Override
    public Reparaturauftrag reparaturauftraegeAnlegen(Reparaturauftrag auftrag) {
        return reparaturauftragManager.anlegen(auftrag);
    }
}