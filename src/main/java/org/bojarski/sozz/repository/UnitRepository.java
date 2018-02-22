package org.bojarski.sozz.repository;

import java.util.Collection;

import org.bojarski.sozz.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Interfejs repozytorium jednostki miary.
 * @author Arkadiusz Bojarski
 *
 */
public interface UnitRepository extends JpaRepository<Unit, Long>, QueryDslPredicateExecutor<Unit> {
    
    /**
     * Metoda wyszukująca jednostkę miary o podanej nazwie.
     * @param name nazwa jednostki według której ma nastąpić wyszukiwanie.
     * @return jednostka o podanej nazwie jeżeli istnieje lub null.
     */
    Unit findOneByName(String name);
    
    /**
     * Metoda wyszukująca jednostkę miary o podanym fragmencie nazwy.
     * @param name fragment nazwy jednostki według którego ma nastąpić wyszukiwanie.
     * @return kolekcja jednostek o nazwach zawierających podany fragment.
     */
    Collection<Unit> findByNameContaining(String name);

}