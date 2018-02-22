package org.bojarski.sozz.controller;

import org.bojarski.sozz.model.Category;
import org.bojarski.sozz.model.Part;
import org.bojarski.sozz.model.PartSearchConditions;
import org.bojarski.sozz.model.Supplier;
import org.bojarski.sozz.service.PartService;
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
 * Klasa kontrolera {@link Part}
 * @author Arkadiusz Bojarski
 *
 */
@RestController
@RequestMapping("/api/parts")
public class PartController {
    
    private final PartService service;
    
    /**
     * Konstruktor zapamiętujący referencję do {@link PartService}
     * @param service serwis obsługujący części.
     */
    @Autowired
    public PartController(PartService service) {
        this.service = service;
    }
    
    /**
     * Metoda pozwalająca na wyszukiwanie części.
     * @param query napis stanowiący zapytanie według którego mają zostać wyszukane części.
     * @param number napis stanowiący fragment numeru według którego mają zostać wyszukane części.
     * @param description napis stanowiący fragment opisu według którego mają zostać wyszukane części.
     * @param category napis stanowiący fragment nazwy {@link Category} według którego mają zostać wyszukane części. 
     * @param material napis stanowiący fragment materiału według którego mają zostać wyszukane części.
     * @param supplier napis stanowiący fragment nazwy {@link Supplier} według którego mają zostać wyszukane części.
     * @param page numer strony części która ma zostać zwrócona.
     * @param size numer określający ilość części w zwróconej stronie.
     * @param direction {@link Direction} sortowania części w zwróconej stronie.
     * @param property napis będący nazwą pola według którego ma nastąpić sortowanie części.
     * @return {@link ResponseEntity} zawierająca wynikową {@link Page} przechowująca części
     * oraz {@link HttpStatus} oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Part>> getParts(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "number", required = false) String number,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "material", required = false) String material,
            @RequestParam(value = "supplier", required = false) String supplier,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="ASC") Direction direction,
            @RequestParam(value="property", defaultValue="number") String property) {
        
        PartSearchConditions conditions = new PartSearchConditions();
        
        conditions.setNumber(number);
        conditions.setDescription(description);
        conditions.setCategory(category);
        conditions.setMaterial(material);
        conditions.setSupplier(supplier);
        
        return new ResponseEntity<Page<Part>>(
                service.search(query, conditions,
                        new PageRequest(page, size, direction, property)
                ), HttpStatus.OK);
    }

    /**
     * Metoda pozwalająca na utworzenia nowej części.
     * @param part nowo tworzona część.
     * @return odpowiedź zawierająca nowo utworzoną część oraz status oznaczający pomyślne wykonanie akcji utworzenia.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createPart(@RequestBody @Validated Part part) {
        return new ResponseEntity<Part>(
                service.create(part), HttpStatus.CREATED);
    }

    /**
     * Metoda pozwalająca na pobranie danych części o podanym numerze identyfikującym.
     * @param id numer identyfikujący część.
     * @return odpowiedź zawierająca część o podanym numerze identyfikującym oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPart(@PathVariable Long id) {
        return new ResponseEntity<Part>(service.read(id), HttpStatus.OK);
    }

    /**
     * Metoda pozwalająca na modyfikację istniejącej części.
     * @param id numer identyfikujący modyfikowaną część.
     * @param part część zawierająca nowe dane modyfikowanej części.
     * @return odpowiedź zawierająca zmodyfikowaną część oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePart(@PathVariable Long id,
            @RequestBody @Validated Part part) {
        return new ResponseEntity<Part>(
                service.update(id, part), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na usunięcie istniejącej części.
     * @param id numer identyfikujący usuwaną część.
     * @return pusta odpowiedź zawierająca status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method =RequestMethod.DELETE)
    public ResponseEntity<?> deletePart(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}