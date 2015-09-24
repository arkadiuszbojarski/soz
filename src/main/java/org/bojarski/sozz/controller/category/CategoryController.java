package org.bojarski.sozz.controller.category;

import org.bojarski.sozz.model.domain.category.Category;
import org.bojarski.sozz.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Klasa kontrolera {@link Category}
 * @author Arkadiusz Bojarski
 *
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    private final CategoryService categoryService;
    
    /**
     * Konstruktor zapamiętujący referencję do {@link CategoryService}.
     * @param categoryService referencja do serwisu obsługującego kategorie.
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Metoda pozwalająca na wyszukiwanie kategorii.
     * @param query napis stanowiący zapytanie według którego mają zostać wyszukane kategorie.
     * @param page numer strony kategorii która ma zostać zwrócona.
     * @param size numer określający ilość kategorii w zwróconej stronie.
     * @param direction {@link Direction} sortowania kategorii w zwróconej stronie.
     * @param property napis będący nazwą pola według którego ma nastąpić sortowanie kategorii.
     * @return {@link ResponseEntity} zawierająca wynikową {@link Page} przechowująca kategorie
     * oraz {@link HttpStatus} oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Category>> searchCategories(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="ASC") Direction direction,
            @RequestParam(value="property", defaultValue="name") String property) {
        return new ResponseEntity<Page<Category>>(
                categoryService.search(query, new PageRequest(page, size, direction, property)), HttpStatus.OK);
    }

    /**
     * Metoda pozwalająca na utworzenia nowej kategorii.
     * @param category nowo tworzona kategoria.
     * @return odpowiedź zawierająca nowo utworzoną kategorie oraz status oznaczający pomyślne wykonanie akcji utworzenia.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCategory(@Validated @RequestBody Category category) {
        return new ResponseEntity<Category>(
                categoryService.create(category), HttpStatus.CREATED);
    }

    /**
     * Metoda pozwalająca na pobranie danych kategorii o podanym numerze identyfikującym.
     * @param id numer identyfikujący kategorie.
     * @return odpowiedź zawierająca kategorie o podanym numerze identyfikującym oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        return new ResponseEntity<Category>(categoryService.read(id), HttpStatus.OK);
    }

    /**
     * Metoda pozwalająca na modyfikację istniejącej kategorii.
     * @param id numer identyfikujący modyfikowaną kategorię.
     * @param category kategoria zawierająca nowe dane modyfikowanej kategorii.
     * @return odpowiedź zawierająca zmodyfikowaną kategorię oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @Validated @RequestBody Category category) {
        return new ResponseEntity<Category>(
                categoryService.update(id, category), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na usunięcie istniejącej kategorii.
     * @param id numer identyfikujący usuwaną kategorię.
     * @return pusta odpowiedź zawierająca status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method =RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
