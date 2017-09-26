package org.bojarski.sozz.controller.requisition;

import java.util.Date;

import org.bojarski.sozz.model.domain.category.Category;
import org.bojarski.sozz.model.domain.drawing.Drawing;
import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.domain.requisition.Requisition;
import org.bojarski.sozz.model.domain.requisition.RequisitionSearchConditions;
import org.bojarski.sozz.model.domain.requisition.Status;
import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.bojarski.sozz.service.requisition.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Klasa kontrolera {@link Requisition}
 * @author Arkadiusz Bojarski
 *
 */
@RestController
@RequestMapping("/api/requisitions")
public class RequisitionController {
    
    private final RequisitionService service;
    
    /**
     * Konstruktor zapamiętujący referencję do {@link RequisitionService}.
     * @param service serwis obsługujący zapotrzebowania.
     */
    @Autowired
    public RequisitionController(RequisitionService service) {
        this.service = service;
    }

    /**
     * Metoda pozwalająca na wyszukiwanie zapotrzebowań.
     * @param query napis stanowiący zapytanie według którego mają zostać wyszukane zapotrzebowania.
     * @param number napis stanowiący fragment numeru według którego mają zostać wyszukane zapotrzebowania.
     * @param after data określająca po jakiej dacie utworzono wyszukane zapotrzebowania.
     * @param before data określająca przed jaką datą utworzono wyszukane zapotrzebowania.
     * @param author napis stanowiący fragment adresu email autora według którego mają zostać wyszukane zapotrzebowania.
     * @param status {@link Status} określający status wyszukanych zapotrzebowań.
     * @param element napis stanowiący fragment elementy według którego mają zostać wyszukane zapotrzebowania.
     * @param drawing napis stanowiący fragment numeru {@link Drawing} według którego mają zostać wyszukane zapotrzebowania.
     * @param part napis stanowiący fragment numeru {@link Part} według którego mają zostać wyszukane zapotrzebowania.
     * @param description napis stanowiący fragment opisu części według którego mają zostać wyszukane zapotrzebowania.
     * @param category napis stanowiący fragment nazwy {@link Category} części według którego mają zostać wyszukane zapotrzebowania. 
     * @param material napis stanowiący fragment materiału części według którego mają zostać wyszukane zapotrzebowania.
     * @param supplier napis stanowiący fragment nazwy {@link Supplier} części według którego mają zostać wyszukane zapotrzebowania.
     * @param page numer strony zapotrzebowań która ma zostać zwrócona.
     * @param size numer określający ilość zapotrzebowań w zwróconej stronie.
     * @param direction {@link Direction} sortowania zapotrzebowań w zwróconej stronie.
     * @param property napis będący nazwą pola według którego ma nastąpić sortowanie zapotrzebowań.
     * @return {@link ResponseEntity} zawierająca wynikową {@link Page} przechowująca zapotrzebowania
     * oraz {@link HttpStatus} oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Requisition>> getRequisitions(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "withNumber", required = false) Long number,
            @RequestParam(value = "after", required = false) Date after,
            @RequestParam(value = "before", required = false) Date before,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "element", required = false) String element,
            @RequestParam(value = "drawing", required = false) String drawing,
            @RequestParam(value = "part", required = false) String part,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "material", required = false) String material,
            @RequestParam(value = "supplier", required = false) String supplier,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="DESC") Direction direction,
            @RequestParam(value="property", defaultValue="start") String property) {
        
        RequisitionSearchConditions conditions = new RequisitionSearchConditions();
        conditions.setNumber(number);
        conditions.setAfter(after);
        conditions.setBefore(before);
        conditions.setAuthor(author);
        conditions.setStatus(status);
        conditions.setElement(element);
        conditions.setDrawing(drawing);
        conditions.setPart(part);
        conditions.setDescription(description);
        conditions.setCategory(category);
        conditions.setMaterial(material);
        conditions.setSupplier(supplier);
        
        return new ResponseEntity<Page<Requisition>>(
                service.search(query, conditions,
                        new PageRequest(page, size, direction, property)
                ), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na utworzenia nowego zapotrzebowania.
     * @param requisition nowo tworzone zapotrzebowanie.
     * @return odpowiedź zawierająca nowo utworzone zapotrzebowanie oraz status oznaczający pomyślne wykonanie akcji utworzenia.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createRequisitions(@RequestBody @Validated Requisition requisition) {
        return new ResponseEntity<Requisition>(
                service.create(requisition), HttpStatus.CREATED);
    }
    
    /**
     * Metoda pozwalająca na pobranie danych zapotrzebowanie o podanym numerze.
     * @param number numer zapotrzebowania.
     * @return odpowiedź zawierająca zapotrzebowanie o podanym numerze oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public ResponseEntity<?> getRequisition(@PathVariable Long number) {
        return new ResponseEntity<Requisition>(service.read(number), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na pobranie wszystkich wersji zapotrzebowania o podanym numerze.
     * @param number numer zapotrzebowania.
     * @param page numer strony zapotrzebowań która ma zostać zwrócona.
     * @param size numer określający ilość zapotrzebowań w zwróconej stronie.
     * @param direction {@link Direction} sortowania zapotrzebowań w zwróconej stronie.
     * @param property napis będący nazwą pola według którego ma nastąpić sortowanie zapotrzebowań.
     * @return {@link ResponseEntity} zawierająca wynikową {@link Page} przechowująca zapotrzebowania
     * oraz {@link HttpStatus} oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{number}/history", method = RequestMethod.GET)
    public ResponseEntity<?> getRequisitionSuppliers(
            @PathVariable Long number,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="ASC") Direction direction,
            @RequestParam(value="property", defaultValue="start") String property) {
        return new ResponseEntity<Page<Requisition>>(
                service.readWithHistory(number,
                        new PageRequest(page, size, direction, property)
                ), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na modyfikację istniejącego zapotrzebowania.
     * @param number numer modyfikowanego zapotrzebowania.
     * @param requisition zapotrzebowanie zawierająca nowe dane modyfikowanego zapotrzebowania.
     * @return odpowiedź zawierająca zmodyfikowane zapotrzebowanie oraz status oznaczający pomyślne wykonanie akcji.
     */
    @PreAuthorize("@defaultCurrentUserService.canModifyRequisition(#number) || hasAuthority('ADMIN')")
    @RequestMapping(value = "/{number}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRequisition(@PathVariable Long number,
            @RequestBody @Validated Requisition requisition) {
        return new ResponseEntity<Requisition>(
                service.update(number, requisition), HttpStatus.OK);
    }
    
    /**
     * Metoda zwracająca tablicę dostępnych {@link Status} jakie może mieć zapotrzebowanie.
     * @return odpowiedź zawierająca tablicę dostępnych statusów zapotrzebowanie oraz status oznaczający pomyślne wykonanie akcji. 
     */
    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public ResponseEntity<?> getAviableRequisitionStatuses() {
        return new ResponseEntity<Status[]>(Status.values(), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na usunięcie istniejącego zapotrzebowania.
     * @param number numer usuwanego zapotrzebowania.
     * @return pusta odpowiedź zawierająca status oznaczający pomyślne wykonanie akcji.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{number}", method =RequestMethod.DELETE)
    public ResponseEntity<?> deleteSupplier(@PathVariable Long number) {
        service.delete(number);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
