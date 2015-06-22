package org.bojarski.sozz.repository.drawing;

import java.util.Collection;

import org.bojarski.sozz.model.domain.drawing.Drawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface DrawingRepository extends JpaRepository<Drawing, Long>, QueryDslPredicateExecutor<Drawing> {

    Drawing findOneByNumber(String number);

    Collection<Drawing> findByNumberContaining(String number);
    
}
