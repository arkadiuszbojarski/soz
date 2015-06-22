package org.bojarski.sozz.repository.supplier;

import java.util.Collection;

import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface SupplierRepository extends JpaRepository<Supplier, Long>, QueryDslPredicateExecutor<Supplier> {

    Collection<Supplier> findByNameContaining(String name);
    
    Supplier findOneByName(String name);
    
}
