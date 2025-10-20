/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.dataaccess;

import immobilien.immobilienverwaltung.core.entities.Reparaturauftrag;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Ensar
 */

@Stateless
public class ReparaturauftragDAO {

    @PersistenceContext
    private EntityManager em;

    // Anlegen
    public Reparaturauftrag anlegen(Reparaturauftrag auftrag) {
        ReparaturauftragEntity entity = new ReparaturauftragEntity(auftrag);
        em.persist(entity);
        return entity.toReparaturauftrag();
    }

    // Suchen (nach Auftragsnummer)
    public Reparaturauftrag suchen(String auftragsnummer) {
        ReparaturauftragEntity entity = em.find(ReparaturauftragEntity.class, auftragsnummer);
        return entity != null ? entity.toReparaturauftrag() : null;
    }

    // Bearbeiten
    public boolean bearbeiten(Reparaturauftrag auftrag) {
        ReparaturauftragEntity entity = em.find(ReparaturauftragEntity.class, auftrag.getAuftragsnummer());
        if (entity == null) {
            return false;
        }

        entity.setWartungsdatum(auftrag.getWartungsdatum());
        entity.setBeschreibung(auftrag.getBeschreibung());
        entity.setMassnahmen(auftrag.getMassnahmen());
        entity.setWartungsstatus(auftrag.getWartungsstatus());

        // Optional, falls Immobilie geändert werden darf:
        if (auftrag.getImmobilie() != null) {
            entity.setImmobilie(new ImmobilienEntity(auftrag.getImmobilie()));
        }

        return true;
    }

    // Löschen
    public boolean loeschen(String auftragsnummer) {
        ReparaturauftragEntity entity = em.find(ReparaturauftragEntity.class, auftragsnummer);
        if (entity == null)
            return false;

        em.remove(entity);
        return true;
    }

    // Alle Reparaturaufträge
    public List<Reparaturauftrag> alleAuftraege() {
        TypedQuery<ReparaturauftragEntity> query =
                em.createQuery("SELECT r FROM ReparaturauftragEntity r", ReparaturauftragEntity.class);
        return query.getResultList().stream()
                .map(ReparaturauftragEntity::toReparaturauftrag)
                .collect(Collectors.toList());
    }
}
