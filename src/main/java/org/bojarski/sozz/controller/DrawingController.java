package org.bojarski.sozz.controller;

import org.bojarski.sozz.model.Drawing;
import org.bojarski.sozz.service.DrawingService;
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
 * Klasa kontrolera {@link Drawing}
 * @author Arkadiusz Bojarski
 *
 */
@RestController
@RequestMapping("/api/drawings")
public class DrawingController {
    
    private final DrawingService service;
    
    /**
     * Konstruktor zapamiętujący referencję do {@link DrawingService}
     * @param service serwis obsługujący rysunki techniczne.
     */
    @Autowired
    public DrawingController(DrawingService service) {
        this.service = service;
    }
    
    /**
     * Metoda pozwalająca na wyszukiwanie rysunków technicznych.
     * @param query napis stanowiący zapytanie według którego mają zostać wyszukane rysunki techniczne.
     * @param page numer strony rysunków technicznych która ma zostać zwrócona.
     * @param size numer określający ilość rysunków technicznych w zwróconej stronie.
     * @param direction {@link Direction} sortowania rysunków technicznych w zwróconej stronie.
     * @param property napis będący nazwą pola według którego ma nastąpić sortowanie rysunków technicznych.
     * @return {@link ResponseEntity} zawierająca wynikową {@link Page} przechowująca rysunki techniczne
     * oraz {@link HttpStatus} oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Drawing>> searchDrawings(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="ASC") Direction direction,
            @RequestParam(value="property", defaultValue="number") String property) {
        return new ResponseEntity<Page<Drawing>>(
                service.search(query, new PageRequest(page, size, direction, property)), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na utworzenia nowego rysunku technicznego.
     * @param drawing nowo tworzony rysunek techniczny.
     * @return odpowiedź zawierająca nowo utworzony rysunek techniczne oraz status oznaczający pomyślne wykonanie akcji utworzenia.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createDrawing(@Validated @RequestBody Drawing drawing) {
        return new ResponseEntity<Drawing>(
                service.create(drawing), HttpStatus.CREATED);
    }
    
    /**
     * Metoda pozwalająca na pobranie danych rysunku technicznego o podanym numerze identyfikującym.
     * @param id numer identyfikujący rysunek techniczny.
     * @return odpowiedź zawierająca rysunek techniczny o podanym numerze identyfikującym oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDrawing(@PathVariable Long id) {
        return new ResponseEntity<Drawing>(service.read(id), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na modyfikację istniejącego rysunku technicznego.
     * @param id numer identyfikujący modyfikowany rysunek techniczny.
     * @param drawing rysunek techniczny zawierająca nowe dane modyfikowanego rysunku.
     * @return odpowiedź zawierająca zmodyfikowany rysunek techniczny oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDrawing(@PathVariable Long id, @Validated Drawing drawing) {
        return new ResponseEntity<Drawing>(
                service.update(id, drawing), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na usunięcie istniejącego rysunku technicznego.
     * @param id numer identyfikujący usuwany rysunek techniczny.
     * @return pusta odpowiedź zawierająca status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method =RequestMethod.DELETE)
    public ResponseEntity<?> deleteDrawing(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
