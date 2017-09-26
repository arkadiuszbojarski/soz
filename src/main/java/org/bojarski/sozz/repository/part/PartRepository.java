package org.bojarski.sozz.repository.part;

import java.util.Collection;

import org.bojarski.sozz.model.domain.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

/**
 * Interfejs repozytorium części.
 * @author Arkadiusz Bojarski
 *
 */
public interface PartRepository extends JpaRepository<Part, Long>, QueryDslPredicateExecutor<Part> {
    
    /**
     * Metoda pozwalająca na wyszukanie części których numer katalogowy
     * zawiera podany fragment.
     * @param number napis będący fragmentem jaki mają zawierać numery katalogowe
     * zwróconych części.
     * @return kolekcja części których numery katalogowe zawierają podany fragment.
     */
    Collection<Part> findByNumberContaining(String number);

    /**
     * Metoda pozwalająca na wyszukanie części o podanym id kategorii.
     * @param category_id id kategorii zwracanych części.
     * @return kolekcja części o podanym id kategorii.
     */
    Collection<Part> findByCategoryId(Long category_id);
    
    /**
     * Metoda pozwalająca na wyszukanie części o podanym id dostawcy.
     * @param supplier_id id dostawcy zwracanych części.
     * @return kolekcja części o podanym id dostawcy.
     */
    Collection<Part> findBySupplierId(Long supplier_id);
    
    /**
     * Metoda pozwalająca na wyszukanie części których jeden z wymiarów
     * ma jednostkę o podanym id. 
     * @param unit_id id jednostki miary co najmniej jednego z wymiarów zwracanych części.
     * @return
     */
    @Query("select p from Part p inner join p.dimensions d where d.id = :id")
    Collection<Part> findByDimensionsUnitId(@Param("id") Long unit_id);

    /**
     * Metoda pozwalająca na zwrócenie części o podanym numerze katalogowym.
     * @param number napis będący numerem katalogowym zwracanej części.
     * @return część o podanym numerze katalogowym.
     */
    Part findOneByNumber(String number);
     
}
