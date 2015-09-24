package org.bojarski.sozz.repository.category;

import java.util.Collection;

import org.bojarski.sozz.model.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Interfejs repozytorium kategorii.
 * @author Arkadiusz Bojarski
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long>, QueryDslPredicateExecutor<Category> {
    
    /**
     * Metoda wyszukująca kategorie o podanym fragmencie nazwy.
     * @param name napis na podstawie którego ma nastąpić wyszukanie kategorii o nazwie zawierającej podany napis.
     * @return kolekcja kategorii zawierająca fragment nazwy.
     */
    Collection<Category> findByNameContaining(String name);
    
    /**
     * Metoda wyszukująca kategorie o podanej nazwie.
     * @param name napis na podstawie którego ma nastąpić wyszukanie kategorii o nazwie takiej jak podany napis.
     * @return kategoria o podanej nazwie.
     */
    Category findOneByName(String name);
    
}
