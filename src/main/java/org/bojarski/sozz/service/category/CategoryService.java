package org.bojarski.sozz.service.category;

import org.bojarski.sozz.model.domain.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Interfejs serwisu kategorii.
 * @author Arkadiusz Bojarski
 *
 */
public interface CategoryService {

    /**
     * Metoda pozwalająca na utworzenie nowej kategorii.
     * @param category kategoria do utworzenia.
     * @return utworzona kategoria.
     */
    public Category create(Category category);
    
    /**
     * Metoda pozwalająca an odczytanie kategorii o podanym id.
     * @param id kategorii do odczytania.
     * @return kategoria o podanym id.
     */
    public Category read(Long id);
    
    /**
     * Metoda pozwalająca na zmodyfikowanie kategorii o podanym id.
     * @param id kategorii do zmodyfikowania.
     * @param category postać jaką powinno przyjąć zmodyfikowana kategoria.
     * @return zmodyfikowana kategoria.
     */
    public Category update(Long id, Category category);
    
    /**
     * Metoda pozwalająca na wyszukanie kategorii.
     * @param query napis będący frazą wyszukiwania.
     * @param pageRequest informacje określająca stronę.
     * @return strona wyników wyszukiwania kategorii utworzona na podstawie podanej frazy.
     */
    public Page<Category> search(String query, PageRequest pageRequest);
    
    /**
     * Metoda pozwalająca na usunięcie kategorii o podanym id.
     * @param id usuwanej kategorii.
     */
    public void delete(Long id);
    
}
