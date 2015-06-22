package org.bojarski.sozz.repository.requisition;

import java.util.Collection;

import org.bojarski.sozz.model.domain.requisition.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface RequisitionRepository extends JpaRepository<Requisition, Long>, QueryDslPredicateExecutor<Requisition> {

    @Query("select max(number)+1 from Requisition")
    Long getLastSequence();
    
    Collection<Requisition> findByUnitId(Long id);

    Collection<Requisition> findByDrawingId(Long id);
    
    Collection<Requisition> findByPartId(Long id);

    Requisition findOneByNumberAndEndIsNull(Long number);
    
    void deleteByNumber(Long number);
    
}
