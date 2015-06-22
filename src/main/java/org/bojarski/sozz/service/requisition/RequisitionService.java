package org.bojarski.sozz.service.requisition;

import org.bojarski.sozz.model.domain.requisition.Requisition;
import org.bojarski.sozz.model.domain.requisition.RequisitionSearchConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RequisitionService {

    Page<Requisition> search(String query, RequisitionSearchConditions conditions, PageRequest pageRequest);

    Requisition create(Requisition requisition);

    Requisition read(Long number);

    Page<Requisition> readWithHistory(Long number, PageRequest pageRequest);

    Requisition update(Long number, Requisition requisition);

    void delete(Long number);

}
