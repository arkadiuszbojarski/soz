package org.bojarski.sozz.repository.part;

import java.util.Collection;

import org.bojarski.sozz.model.domain.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface PartRepository extends JpaRepository<Part, Long>, QueryDslPredicateExecutor<Part> {
    
    Collection<Part> findByNumberContaining(String number);

    Collection<Part> findByCategoryId(Long category_id);
    
    Collection<Part> findBySupplierId(Long supplier_id);
    
    @Query("select p from Part p inner join p.dimensions d where d.id = :id")
    Collection<Part> findByDimensionsUnitId(@Param("id") Long unit_id);

    Part findOneByNumber(String number);
     
}
