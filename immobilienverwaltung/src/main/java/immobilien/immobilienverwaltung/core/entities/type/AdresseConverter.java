/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package immobilien.immobilienverwaltung.core.entities.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 *
 * @author ahmet
 */
@Converter(autoApply = false)
public class AdresseConverter implements AttributeConverter<Adresse, String>  {
     @Override
    public String convertToDatabaseColumn(Adresse adresse) {
        if (adresse == null) return null;
        return adresse.getStrasse() + " " + adresse.getHausnummer()
             + ", " + adresse.getPlz() + " " + adresse.getStadt();
    }

    @Override
    public Adresse convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;

        try {
            String[] parts = dbData.split(",", 2);
            String[] strasseHausnummer = parts[0].trim().split(" ", 2);
            String[] plzStadt = parts[1].trim().split(" ", 2);

            Adresse adresse = new Adresse();
            adresse.setStrasse(strasseHausnummer[0]);
            adresse.setHausnummer(strasseHausnummer.length > 1 ? strasseHausnummer[1] : "");
            adresse.setPlz(plzStadt[0]);
            adresse.setStadt(plzStadt.length > 1 ? plzStadt[1] : "");

            return adresse;
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Parsen der Adresse: " + dbData, e);
        }
    }
    
}
