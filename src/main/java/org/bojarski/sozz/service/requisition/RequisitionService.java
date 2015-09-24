package org.bojarski.sozz.service.requisition;

import org.bojarski.sozz.model.domain.requisition.Requisition;
import org.bojarski.sozz.model.domain.requisition.RequisitionSearchConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Interfejs serwisu zapotrzebowań.
 * @author Arkadiusz Bojarski
 *
 */
public interface RequisitionService {

    /**
     * Metoda pozwalająca na wyszukanie zapotrzebowań z wykorzystaniem
     * frazy oraz kryteriów wyszukiwania.
     * @param query napis będący frazą wyszukiwania.
     * @param conditions kryteria wyszukiwania zapotrzebowań.
     * @param pageRequest informacje określająca stronę.
     * @return strona zapotrzebowań wyszukanym z wykorzystaniem
     * frazy i kryteriów wyszukiwania.
     */
    Page<Requisition> search(String query, RequisitionSearchConditions conditions, PageRequest pageRequest);

    /**
     * Metoda pozwalająca na utworzenie zapotrzebowania.
     * @param requisition zapotrzebowanie które ma zostać utworzone.
     * @return utworzone zapotrzebowanie.
     */
    Requisition create(Requisition requisition);

    /**
     * Metoda pozwalająca na odczytanie zapotrzebowania o podanym numerze.
     * @param number numer odczytywanego zapotrzebowania.
     * @return zapotrzebowanie o podanym numerze.
     */
    Requisition read(Long number);

    /**
     * Metoda pozwalająca na odczytanie zapotrzebowania 
     * o podanym numerze wraz z jego wszystkimi wersjami.
     * @param number numer odczytywanego zapotrzebowania.
     * @param pageRequest informacje określająca stronę.
     * @return strona zawierająca wersję zapotrzebowania o podanym numerze.
     */
    Page<Requisition> readWithHistory(Long number, PageRequest pageRequest);

    /**
     * Metoda pozwalająca na zmodyfikowanie zapotrzebowania o podanym numerze.
     * @param number numer modyfikowanego zapotrzebowania.
     * @param requisition zapotrzebowania któej stanowi postać
     * jaką ma przyjąć modyfikowane zapotrzebowanie.
     * @return zmodyfikowane zapotrzebowanie.
     */
    Requisition update(Long number, Requisition requisition);

    /**
     * Metoda pozwalająca na usunięcie zapotrzebowania o podanym numerze
     * wraz z wszystkimi jego wersjami.
     * @param number
     */
    void delete(Long number);

}
