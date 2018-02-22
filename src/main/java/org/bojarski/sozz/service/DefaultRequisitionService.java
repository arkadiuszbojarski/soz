package org.bojarski.sozz.service;

import java.util.Optional;

import org.bojarski.sozz.exception.NotFoundException;
import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.QRequisition;
import org.bojarski.sozz.model.Requisition;
import org.bojarski.sozz.model.RequisitionSearchConditions;
import org.bojarski.sozz.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;

/**
 * Implementacja interfejsu serwisu zapotrzebowań.
 * @author Arkadiusz Bojarski
 *
 */
@Service
@Transactional(readOnly = true)
public class DefaultRequisitionService implements RequisitionService {
    
    private final RequisitionRepository requisitionRepository;
    
    private final RequisitionUtil requisitionUtil;
       
    /**
     * Konstruktor przechowujący referencję do obiektów
     * repozytorium zapotrzebowań i klasy pomocniczej dla zapotrzebowań.
     * @param requisitionRepository
     * @param requisitionUtil
     */
    @Autowired
    public DefaultRequisitionService(RequisitionRepository requisitionRepository, RequisitionUtil requisitionUtil) {
        this.requisitionRepository = requisitionRepository;
        this.requisitionUtil = requisitionUtil;
    }

    @Override
    public Page<Requisition> search(String query, RequisitionSearchConditions conditions, PageRequest pageRequest) {
        QRequisition requisition = QRequisition.requisition;
        BooleanBuilder where = new BooleanBuilder();
        
        if(query != null) {
            where.and(requisition.number.stringValue().containsIgnoreCase(query)
                    .or(requisition.author.email.containsIgnoreCase(query))
                    .or(requisition.status.stringValue().containsIgnoreCase(query))
                    .or(requisition.element.containsIgnoreCase(query))
                    .or(requisition.comment.containsIgnoreCase(query))
                    .or(requisition.part.number.containsIgnoreCase(query))
                    .or(requisition.part.description.containsIgnoreCase(query))
                    .or(requisition.part.category.name.containsIgnoreCase(query))
                    .or(requisition.part.material.containsIgnoreCase(query))
                    .or(requisition.part.supplier.name.containsIgnoreCase(query))
                    .or(requisition.part.supplier.contact.email.containsIgnoreCase(query))
                    .or(requisition.part.supplier.contact.web_site.containsIgnoreCase(query))
                    .or(requisition.part.supplier.contact.phone.containsIgnoreCase(query))
                    .or(requisition.part.supplier.contact.address.country.containsIgnoreCase(query))
                    .or(requisition.part.supplier.contact.address.city.containsIgnoreCase(query))
                    .or(requisition.part.supplier.contact.address.street.containsIgnoreCase(query))
                    .or(requisition.drawing.number.containsIgnoreCase(query)));
        }
        
        if(conditions.getNumber() != null) {
            where.and(requisition.number.stringValue().containsIgnoreCase(conditions.getNumber().toString()));
        }
        
        if(conditions.getAfter() != null) {
            where.and(requisition.start.after(conditions.getAfter()));
        }
        
        if(conditions.getBefore() != null) {
            where.and(requisition.start.before(conditions.getBefore()));
        }
        
        if(conditions.getAuthor() != null) {
            where.and(requisition.author.email.containsIgnoreCase(conditions.getAuthor()));
        }
        
        if(conditions.getStatus() != null) {
            where.and(requisition.status.eq(conditions.getStatus()));
        }
        
        if(conditions.getElement() != null) {
            where.and(requisition.element.containsIgnoreCase(conditions.getElement()));
        }
        
        if(conditions.getDrawing() != null) {
            where.and(requisition.drawing.number.containsIgnoreCase(conditions.getDrawing()));
        }
        
        if(conditions.getPart() != null) {
            where.and(requisition.part.number.containsIgnoreCase(conditions.getPart()));
        }
            
        if(conditions.getDescription() != null) {
            where.and(requisition.part.description.containsIgnoreCase(conditions.getDescription()));
        }
            
        if(conditions.getCategory() != null) {
            where.and(requisition.part.category.name.containsIgnoreCase(conditions.getCategory()));
        }
            
        if(conditions.getMaterial() != null) {
            where.and(requisition.part.material.containsIgnoreCase(conditions.getMaterial()));
        }

        if(conditions.getSupplier() != null) {
            where.and(requisition.part.supplier.name.containsIgnoreCase(conditions.getSupplier()));
        }

        where.and(requisition.end.isNull());
        
        return (Page<Requisition>) requisitionRepository.findAll(where, pageRequest);
    }

    @Override
    @Transactional(readOnly = false)
    public Requisition create(Requisition requisition) {
        return requisitionRepository.save(requisitionUtil.create(requisition));
    }

    @Override
    public Requisition read(Long number) {
        QRequisition requisition = QRequisition.requisition;
        BooleanBuilder where = new BooleanBuilder();
        where.and(requisition.number.eq(number));
        where.and(requisition.end.isNull());
        return Optional.ofNullable(requisitionRepository.findOne(where))
                .orElseThrow(() -> new NotFoundException(Messages.REQUISITION_NOT_FOUND, number.toString(), Messages.REQUISITION_NOT_FOUND_DEFAULT));
    }

    @Override
    public Page<Requisition> readWithHistory(Long number, PageRequest pageRequest) {
        return (Page<Requisition>) requisitionRepository.findAllByNumber(number, pageRequest);
    }

    @Override
    @Transactional(readOnly = false)
    public Requisition update(Long number, Requisition requisition) {
        Requisition oldRequisition = read(number);
        return requisitionUtil.update(oldRequisition, requisition);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long number) {
        requisitionRepository.deleteByNumber(number);
    }

}
