package org.bojarski.sozz.repository.supplier;

import java.util.Collection;

import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Interfejs repozytorium dostawców.
 * @author Arkadiusz Bojarski
 *
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long>, QueryDslPredicateExecutor<Supplier> {

    /**
     * Metoda pozwalająca na wyszukanie dostawców których nazwa
     * zawiera podany fragment.
     * @param name napis będący fragmentem który zawierać będą zwróceni dostawcy.
     * @return kolekcja dostawców których nazwa zawiera podany fragment.
     */
    Collection<Supplier> findByNameContaining(String name);
    
    /**
     * Metoda zwracająca dostawcę o podanej nazwie.
     * @param name napis będący nazwą zwracanego dostawcy.
     * @return dostawca o podanej nazwie.
     */
    Supplier findOneByName(String name);
    
}
