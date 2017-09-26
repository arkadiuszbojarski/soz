package org.bojarski.sozz.service.drawing;

import org.bojarski.sozz.model.domain.drawing.Drawing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Interfejs serwisu rysunków technicznych.
 * @author Arkadiusz Bojarski
 *
 */
public interface DrawingService {
    
    /**
     * Metoda pozwalająca na utworzenie nowego rysunku.
     * @param drawing rysunek techniczny który ma zostać utworzony.
     * @return utworzony rysunek techniczny.
     */
    Drawing create(Drawing drawing);
    
    /**
     * Metoda pozwalająca na odczytanie rysunku technicznego o podanym id.
     * @param id rysunku technicznego
     * @return rysunek techniczny o podanym id.
     */
    Drawing read(Long id);
    
    /**
     * Metoda pozwalająca na zmodyfikowanie rysunku technicznego o podanym id.
     * @param id modyfikowanego rysunku technicznego.
     * @param drawing zmodyfikowana postać rysunku technicznego.
     * @return zmodyfikowany rysunek techniczny.
     */
    Drawing update(Long id, Drawing drawing);

    /**
     * Metoda pozwalająca na wyszukanie rysunków technicznych.
     * @param query napis będący frazą wyszukiwania rysunków technicznych.
     * @param pageRequest informację określająca stronę.
     * @return strona rysunków technicznych wyszukanych na podstawie podanej frazy.
     */
    Page<Drawing> search(String query, PageRequest pageRequest);
    
    /**
     * Metoda pozwalająca na usunięcie rysunku technicznego o podanym id.
     * @param id usuwanego rysunku technicznego.
     */
    void delete(Long id);

}
