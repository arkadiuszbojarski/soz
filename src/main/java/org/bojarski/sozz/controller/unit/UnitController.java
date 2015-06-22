package org.bojarski.sozz.controller.unit;

import org.bojarski.sozz.model.domain.unit.Unit;
import org.bojarski.sozz.service.unit.UnitService;
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
 * Klasa kontrolera {@link Unit}.
 * @author Arkadiusz Bojarski
 *
 */
@RestController
@RequestMapping("/api/units")
public class UnitController {
    
    private final UnitService service;
    
    /**
     * Konstruktor zapamiętujący referencję do {@link UnitService}.
     * @param service serwis obsługujący jednostki miary.
     */
    @Autowired
    public UnitController(UnitService service) {
        this.service = service;
    }

    /**
     * Metoda pozwalająca na wyszukiwanie jednostek miary.
     * @param query napis stanowiący zapytanie według którego mają zostać wyszukane jednostki miary.
     * @param page numer strony jednostek miary która ma zostać zwrócona.
     * @param size numer określający ilość jednostek miary w zwróconej stronie.
     * @param direction {@link Direction} sortowania jednostek miary w zwróconej stronie.
     * @param property napis będący nazwą pola według którego ma nastąpić sortowanie jednostki miary.
     * @return {@link ResponseEntity} zawierająca wynikową {@link Page} przechowująca jednostki miary
     * oraz {@link HttpStatus} oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Unit>> searhForUnits(
            @RequestParam(value="query", required = false) String query,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="ASC") Direction direction,
            @RequestParam(value="property", defaultValue="name") String property) {
        return new ResponseEntity<Page<Unit>>(
                service.search(query, new PageRequest(page, size, direction, property)), HttpStatus.OK);
    }

    /**
     * Metoda pozwalająca na utworzenia nowej jednostki miary.
     * @param unit nowo tworzona jednostka miary.
     * @return odpowiedź zawierająca nowo utworzoną jednostkę miary oraz status oznaczający pomyślne wykonanie akcji utworzenia.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createNewUnit(@Validated @RequestBody Unit unit) {        
        return new ResponseEntity<Unit>(
                service.create(unit), HttpStatus.CREATED);
    }

    /**
     * Metoda pozwalająca na pobranie danych jednostki miary o podanym numerze identyfikującym.
     * @param id numer identyfikujący jednostkę miary.
     * @return odpowiedź zawierająca jednostkę miary o podanym numerze identyfikującym oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUnit(@PathVariable Long id) {
        return new ResponseEntity<Unit>(service.read(id), HttpStatus.OK);
    }

    /**
     * Metoda pozwalająca na modyfikację istniejącej jednostki miary.
     * @param id numer identyfikujący modyfikowaną jednostkę miary.
     * @param unit jednostka miary zawierająca nowe dane modyfikowanej jednostki.
     * @return odpowiedź zawierająca zmodyfikowaną jednostkę miary oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUnit(@PathVariable Long id, @Validated @RequestBody Unit unit) {
        return new ResponseEntity<Unit>(
                service.update(id, unit), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na usunięcie istniejącej jednostki miary.
     * @param id numer identyfikujący usuwaną jednostkę miary.
     * @return pusta odpowiedź zawierająca status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUnit(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
