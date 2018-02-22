package org.bojarski.sozz.service;

import org.bojarski.sozz.dto.AccountCreateForm;
import org.bojarski.sozz.dto.AccountUpdateForm;
import org.bojarski.sozz.dto.ChangePasswordForm;
import org.bojarski.sozz.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Interfejs serwisu konta użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
public interface AccountService {

    /**
     * Metoda zwracająca stronę kont użytkownika których nazwa zawiera podany fragment.
     * @param username napis będący fragmentem nazwy zwracanych kont użytkownika.
     * @param pageRequest informacje określające stronę kont użytkownika.
     * @return strona kont użytkownika których nazwa zawiera podany fragment.
     */
    Page<Account> search(String username, PageRequest pageRequest);
    
    /**
     * Metoda pozwalająca na utworzenie nowego konta użytkownika.
     * @param accountCreateForm formularza nowego konta użytkownika na podstawie
     * którego powstanie nowe konto użytkownika.
     * @return nowo utworzone konto użytkownika.
     */
    Account create(AccountCreateForm accountCreateForm);
    
    /**
     * Metoda zwracająca konto o podanym id.
     * @param id zwracanego konta użytkownika.
     * @return konto użytkownika o podanym id.
     */
    Account read(Long id);

    /**
     * Metoda pozwalająca na zmianę hasła użytkownika o podanej nazwie.
     * @param name napis będący nazwą konta użytkownika którego hasło ulega zmianie.
     * @param form formularza zmiany hasła użytkownika na podstawie któego
     * hasło konta użytkownika zostanie zmienione.
     */
    void changePassword(String name, ChangePasswordForm form);
    
    /**
     * Metoda pozwalająca na edycję konta użytkownika.
     * @param id edytowanego konta użytkownika.
     * @param form formularz edycji konta użytkownika na podstawie
     * którego konto zostanie zmienione.
     * @return zmodyfikowane konto użytkownika.
     */
    Account update(Long id, AccountUpdateForm form);
    
    /**
     * Metoda pozwalająca na wyszukanie użytkownika po nazwie.
     * @param username napis będący nazwą użytkownika zwracanego konta.
     * @return konto użytkownika o podanej nazwie.
     */
    Account getByUsername(String username);
    
}
