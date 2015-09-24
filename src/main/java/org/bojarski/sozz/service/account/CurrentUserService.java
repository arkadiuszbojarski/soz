package org.bojarski.sozz.service.account;

/**
 * Interfejs serwisu aktualnego użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
public interface CurrentUserService {
    
    /**
     * Metoda pozwalająca określić czy aktualny użytkownik
     * może edytować zapotrzebowania o podanym numerze.
     * @param number numer zapotrzebowania.
     * @return wartość logiczna określająca czy aktualny użytkownik może
     * zapotrzebowanie o podanym numerze.
     */
    boolean canModifyRequisition(Long number);
    
}
