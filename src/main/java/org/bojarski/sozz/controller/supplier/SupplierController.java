 package org.bojarski.sozz.controller.supplier;

import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.bojarski.sozz.model.domain.supplier.SupplierSearchConditions;
import org.bojarski.sozz.service.supplier.SupplierService;
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
 * Klasa kontrolera {@link Supplier}
 * @author Arkadiusz Bojarski
 *
 */
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    private final SupplierService supplierService;
    
    /**
     * Konstruktor zapamiętujący referencję do {@link SupplierService}
     * @param supplierService serwis obsługujący dostawców.
     */
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    
    /**
     * Metoda pozwalająca na wyszukiwanie dostawców.
     * @param query napis stanowiący zapytanie według którego mają zostać wyszukani dostawcy.
     * @param name napis stanowiący fragment nazwy według którego mają zostać wyszukani dostawcy.
     * @param email napis stanowiący fragment adresu email według którego mają zostać wyszukani dostawcy.
     * @param web napis stanowiący fragment strony www według którego mają zostać wyszukani dostawcy.
     * @param phone napis stanowiący fragment numeru telefonu według którego mają zostać wyszukani dostawcy.
     * @param country napis stanowiący fragment nazwy kraju według którego mają zostać wyszukani dostawcy.
     * @param city napis stanowiący fragment miasta według którego mają zostać wyszukani dostawcy.
     * @param street napis stanowiący fragment ulicy według którego mają zostać wyszukani dostawcy.
     * @param page numer strony dostawców która ma zostać zwrócona.
     * @param size numer określający ilość dostawców w zwróconej stronie.
     * @param direction {@link Direction} sortowania dostawców w zwróconej stronie.
     * @param property napis będący nazwą pola według którego ma nastąpić sortowanie dostawców.
     * @return {@link ResponseEntity} zawierająca wynikową {@link Page} przechowująca dostawców
     * oraz {@link HttpStatus} oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Supplier>> getSuppliers(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value="name", required = false) String name,
            @RequestParam(value="email", required = false) String email,
            @RequestParam(value="web", required = false) String web,
            @RequestParam(value="phone", required = false) String phone,
            @RequestParam(value="country", required = false) String country,
            @RequestParam(value="city", required = false) String city,
            @RequestParam(value="street", required = false) String street,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="ASC") Direction direction,
            @RequestParam(value="property", defaultValue="name") String property) {
        SupplierSearchConditions conditions = new SupplierSearchConditions();
        conditions.setName(name);
        conditions.setEmail(email);
        conditions.setWeb(web);
        conditions.setPhone(phone);
        conditions.setCountry(country);
        conditions.setCity(city);
        conditions.setStreet(street);
        return new ResponseEntity<Page<Supplier>>(
                supplierService.search(query, conditions,
                        new PageRequest(page, size, direction, property)
                ), HttpStatus.OK);
    }

    /**
     * Metoda pozwalająca na utworzenia nowego dostawcy.
     * @param supplier nowo tworzony dostawca.
     * @return odpowiedź zawierająca nowo utworzonego dostawcę oraz status oznaczający pomyślne wykonanie akcji utworzenia.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createSupplier(@Validated @RequestBody Supplier supplier) {
        return new ResponseEntity<Supplier>(supplierService.create(supplier), HttpStatus.CREATED);
    }

    /**
     * Metoda pozwalająca na pobranie danych dostawcy o podanym numerze.
     * @param number numer identyfikujący dostawcę.
     * @return odpowiedź zawierająca dostawcę o podanym numerze identyfikującym oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSupplier(@PathVariable Long id) {
        return new ResponseEntity<Supplier>(supplierService.read(id), HttpStatus.OK);
    }

    /**
     * Metoda pozwalająca na modyfikację istniejącego dostawcy.
     * @param number numer identyfikujący modyfikowanego dostawcę.
     * @param supplier dostawca zawierający nowe dane modyfikowanego dostawcy.
     * @return odpowiedź zawierająca zmodyfikowanego dostawcę oraz status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSupplier(@PathVariable Long id, @Validated @RequestBody Supplier supplier) {
        return new ResponseEntity<Supplier>(
                supplierService.update(id, supplier), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na usunięcie istniejącego dostawcy.
     * @param number numer identyfikujący usuwanego dostawcę.
     * @return pusta odpowiedź zawierająca status oznaczający pomyślne wykonanie akcji.
     */
    @RequestMapping(value = "/{id}", method =RequestMethod.DELETE)
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        supplierService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
