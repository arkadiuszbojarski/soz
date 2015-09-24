package org.bojarski.sozz.repository.drawing;

import java.util.Collection;

import org.bojarski.sozz.model.domain.drawing.Drawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Interfejs repozytorium rysunków technicznych.
 * @author Arkadiusz Bojarski
 *
 */
public interface DrawingRepository extends JpaRepository<Drawing, Long>, QueryDslPredicateExecutor<Drawing> {

    /**
     * Metoda zwracająca rysunek techniczny o podanym numerze.
     * @param number napis będący numerem rysunku technicznego który na być zwrócony.
     * @return rysunek techniczny o podanym numerze.
     */
    Drawing findOneByNumber(String number);

    /**
     * Metoda zwracająca kolekcję rysunków technicznych których
     * numer zawiera podany fragment.
     * @param number napis będący fragmentem jaki mają zawierać numery
     * zwróconych rysunków technicznych.
     * @return kolekcja rysunków technicznych których numery
     * zawierają podany fragment.
     */
    Collection<Drawing> findByNumberContaining(String number);
    
}
