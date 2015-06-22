package org.bojarski.sozz.controller.account;

import java.security.Principal;

import org.bojarski.sozz.model.domain.account.Account;
import org.bojarski.sozz.model.domain.account.AccountCreateForm;
import org.bojarski.sozz.model.domain.account.AccountUpdateForm;
import org.bojarski.sozz.model.domain.account.ChangePasswordForm;
import org.bojarski.sozz.model.domain.account.Role;
import org.bojarski.sozz.model.domain.validation.Validation.Extended;
import org.bojarski.sozz.model.views.View.BasicView;
import org.bojarski.sozz.model.views.View.ExtendedView;
import org.bojarski.sozz.service.account.AccountService;
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

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Klasa kontrolera {@link Account}.
 * 
 * @author Arkadiusz Bojarski
 *
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    
    private final AccountService service;
    
    /**
     * Konstruktor zapamiętujący referencję do {@link AccountService}.
     * @param service referencja do serwisu obsługującego konta użytkownika.
     */
    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }
    
    /**
     * Metoda pozwalająca na wyszukiwanie kont użytkownika.
     * @param query napis stanowiący zapytanie według którego mają zostać wyszukane konta użytkowników.
     * @param page numer strony kont użytkowników która ma zostać zwrócona.
     * @param size numer określający ilość kont użytkowników w zwróconej stronie.
     * @param direction {@link Direction} sortowania kont użytkowników w zwróconej stronie.
     * @param property napis będący nazwą pola według którego ma nastąpić sortowanie kont użytkowników.
     * @return {@link ResponseEntity} zawierająca wynikową {@link Page} przechowująca konta użytkownika
     * oraz {@link HttpStatus} oznaczający pomyślne wykonanie akcji.
     *
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Account>> searchAccounts(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="ASC") Direction direction,
            @RequestParam(value="property", defaultValue="username") String property) {
        return new ResponseEntity<Page<Account>>(
                service.search(query,
                        new PageRequest(page, size, direction, property)
                ), HttpStatus.OK);
    }

    /**
     * Metoda pozwalająca na utworzenia nowego konta użytkownika.
     * @param form {@link AccountCreateForm} zawierający dane nowo tworzonego konta.
     * @return odpowiedź zawierająca nowo utworzone konto użytkownika oraz status oznaczający pomyślne wykonanie akcji utworzenia.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonView(ExtendedView.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody @Validated(Extended.class) AccountCreateForm form) {
        return new ResponseEntity<Account>(service.create(form), HttpStatus.CREATED);
    }
    
    /**
     * Metoda pozwalająca na modyfikację istniejącego konta użytkownika.
     * @param id numer identyfikujący modyfikowane konto użytkownika.
     * @param form {@link AccountUpdateForm} zawierający nowe dane modyfikowanego konta.
     * @return odpowiedź zawierająca zmodyfikowane konto użytkownika oraz status oznaczający pomyślne wykonanie akcji.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonView(ExtendedView.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody @Validated(Extended.class) AccountUpdateForm form) {
        return new ResponseEntity<Account>(service.update(id, form), HttpStatus.OK);
    }
    
    /**
     * Metoda zwracająca dane zalogowanego użytkownika wysyłającego zapytanie.
     * @param principal {@link Principal} odpowiadający zalogowanemu użytkownikowi.
     * @return odpowiedź zawierająca konto zalogowanego użytkownika wysyłającego zapytanie oraz status oznaczający pomyślne wykonanie akcji.
     */
    @JsonView(BasicView.class)
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ResponseEntity<?> getCurrent(Principal principal) {
        return new ResponseEntity<Account>(service.getByUsername(principal.getName()), HttpStatus.OK);
    }
    
    /**
     * Metoda pozwalająca na zmianę hasła zalogowanego użytkownika wysyłającego zapytanie.
     * @param principal zleceniodawca wykonania zmiany hasła.
     * @param form {@link ChangePasswordForm} zawierający dane potrzebne do zmiany hasła.
     * @return pusta odpowiedź zawierająca status oznaczający pomyślne wykonanie akcji.
     */
    @JsonView(BasicView.class)
    @RequestMapping(value = "/my/password", method = RequestMethod.PUT)
    public ResponseEntity<?> changePassword(Principal principal, @RequestBody @Validated ChangePasswordForm form) {
        service.changePassword(principal.getName(), form);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * Metoda zwracająca tablicę dostępnych {@link Role} jakie może mieć konto użytkownika.
     * @return odpowiedź zawierająca tablicę dostępnych ról kont użytkownika oraz status oznaczający pomyślne wykonanie akcji. 
     */
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ResponseEntity<?> getAviableAccountRoles() {
        return new ResponseEntity<Role[]>(Role.values(), HttpStatus.OK);
    }
    
}

