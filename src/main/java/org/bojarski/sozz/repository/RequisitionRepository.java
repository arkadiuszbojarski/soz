package org.bojarski.sozz.repository;

import java.util.Collection;

import org.bojarski.sozz.model.Requisition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Interfejs repozytorium zapotrzebowań.
 * @author Arkadiusz Bojarski
 *
 */
public interface RequisitionRepository extends JpaRepository<Requisition, Long>, QueryDslPredicateExecutor<Requisition>{

    /**
     * Metoda zwracająca kolejny dostępny numer dla zapotrzebowania.
     * @return dostępny dla zapotrzebowania numer.
     */
    @Query("select max(number)+1 from Requisition")
    Long getLastSequence();
    
    /**
     * Metoda pozwalająca na wyszukanie zapotrzebowań o podanym id jednostki.
     * @param id jednostki zwracanych zapotrzebowań.
     * @return kolekcja zapotrzebowań o podanym id jednostki.
     */
    Collection<Requisition> findByUnitId(Long id);

    /**
     * Metoda pozwalająca na wyszukanie zapotrzebowań o podanym id rysunku.
     * @param id rysunku zwracanych zapotrzebowań.
     * @return kolekcja zapotrzebowań o podanym id rysunku.
     */
    Collection<Requisition> findByDrawingId(Long id);
    
    /**
     * Metoda pozwalająca na wyszukanie zapotrzebowań o podanym id części.
     * @param id części zwracanych zapotrzebowań.
     * @return kolekcja zapotrzebowań o podanym id części.
     */
    Collection<Requisition> findByPartId(Long id);

    /**
     * Metoda zwracająca najbardziej aktualną wersję zapotrzebowania
     * o podanym numerze.
     * @param number zwracanego zapotrzebowania.
     * @return najbardziej aktualna wersja zapotrzebowania o podanym numerze.
     */
    Requisition findOneByNumberAndEndIsNull(Long number);
    
    /**
     * Metoda zwracająca określoną stronę wersji zapotrzebowań o podanym numerze.
     * @param number zwracanego zapotrzebowania i jego wersji.
     * @param pageable parametry określające stronę.
     * @return strona zapotrzebowań o podanym numerze.
     */
    Page<Requisition> findAllByNumber(Long number, Pageable pageable);
    
    /**
     * Metoda pozwalająca na usunięcie zapotrzebowania wraz z wszystkimi
     * jego wersjami.
     * @param number numer usuwanego zapotrzebowania i jego wersji.
     */
    void deleteByNumber(Long number);
    
}
