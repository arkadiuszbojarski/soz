package org.bojarski.sozz.service.part;

import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.domain.part.PartSearchConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Interfejs serwisu części.
 * @author Arkadiusz Bojarski
 *
 */
public interface PartService {

    /**
     * Metoda pozwalająca na utworzenie nowej części.
     * @param part tworzona część.
     * @return utworzona część.
     */
    public Part create(Part part);
    
    /**
     * Metoda pozwalająca na odczytanie części o podanym id.
     * @param id część która ma zostać odczytana.
     * @return odczytana część.
     */
    public Part read(Long id);
    
    /**
     * Metoda pozwalająca an zmodyfikowanie część o podanym id.
     * @param id modyfikowanej części.
     * @param part część będąca postacią jaką ma przyjąć modyfikowana część.
     * @return zmodyfikowana część.
     */
    public Part update(Long id, Part part);
    
    /**
     * Metoda pozwalająca na wyszukanie części przy użyciu frazy oraz kryteriów wyszukiwania.
     * @param query napis będący frazą wyszukiwania.
     * @param conditions kryteria wyszukiwania części.
     * @param pageRequest informację określające stronę.
     * @return strona części wyszukanych z wykorzystaniem frazy i kryteriów wyszukiwania. 
     */
    public Page<Part> search(String query, PartSearchConditions conditions, PageRequest pageRequest);
    
    /**
     * Metoda pozwalająca na usunięcie części o podanym id.
     * @param id usuwanej części.
     */
    public void delete(Long id);
    
}
