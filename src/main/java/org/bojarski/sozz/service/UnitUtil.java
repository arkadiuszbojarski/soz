package org.bojarski.sozz.service;

import java.util.Optional;

import org.bojarski.sozz.model.Unit;
import org.bojarski.sozz.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Klasa pomocnicza narzędzi jednostek miary zawierająca metody pomocnicze.
 * @author Arkadiusz Bojarski
 *
 */
@Component
public class UnitUtil {
    
    private final UnitRepository unitRepository;
    
    @Autowired
    public UnitUtil(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }
    
    /**
     * Metoda pozwalająca na odczytanie z bazy istniejącej lub utworzenie nowej jednostki.
     * W pierwszej kolejności metoda próbuje odczytać istniejącą jednostkę według jej id lub nazwy
     * jeżeli nie istnieje jednostka o podanym id lub nazwie w bazie jest tworzona nowa jednostka.  
     * @param unit jednostka która ma być odczytana lub utworzona w bazie.
     * @return jednostka istniejąca w bazie lub nowo utworzona.
     */
    public Unit readOrCreate(Unit unit) {
        if(unit != null) {

            if (unit.getId() != null) {
                Optional<Unit> existingUnit = Optional
                        .ofNullable(unitRepository.findOne(unit.getId()));

                if (existingUnit.isPresent()) {
                    return existingUnit.get();
                }
            }

            if(unit.getName() != null) {
                Optional<Unit> sameNameUnit = Optional
                        .ofNullable(unitRepository.findOneByName(unit.getName()));

                if(sameNameUnit.isPresent()) {
                    return sameNameUnit.get();
                }
            }

            return unitRepository.save(new Unit(unit.getName()));
        }
        
        return null;
    }
    
}
