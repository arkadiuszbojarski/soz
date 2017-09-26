package org.bojarski.sozz.service.unit;

import org.bojarski.sozz.model.domain.unit.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Interfejs serwisu jednostek miary.
 * @author Arkadiusz Bojarski
 */
public interface UnitService {
    
    /**
     * Metoda pozwalająca na utworzenie nowej jednostki.
     * @param unit jednostka która ma zostać utworzona.
     * @return utworzona lub odczytana jednostka.
     */
    public Unit create(Unit unit);
    
    /**
     * Metoda zwracająca jednostkę o podanym numerze id.
     * @param id numer identyfikujący jednostkę.
     * @return jednostka o podanym numerze identyfikującym.
     */
    public Unit read(Long id);
    
    /**
     * Metoda pozwalająca na modyfikację jednostki o podanym numerze id.
     * @param id numer identyfikujący jednostkę modyfikowaną.
     * @param unit jednostka której postać ma przyjąć jednostka modyfikowana.
     * @return jednostka o zmodyfikowanej postaci.
     */
    public Unit update(Long id, Unit unit);
    
    /**
     * Metoda pozwalająca na wyszukanie jednostek o nazwie zawierającej podany fragment.
     * @param pageRequest napis będący poszukiwanym w nazwach jednostek fragmentem. 
     * @return kolekcja jednostek posiadających nazwy zawierające podany fragment.
     */
    public Page<Unit> search(String query, PageRequest pageRequest);
    
    /**
     * Metoda pozwalająca na usunięcie jednostki o podanym numerze id.
     * @param id numer identyfikujący jednostkę usuwaną.
     */
    public void delete(Long id);

}
