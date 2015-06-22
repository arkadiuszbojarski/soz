package org.bojarski.sozz.service.unit;

import java.util.Collection;
import java.util.Optional;

import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.domain.requisition.Requisition;
import org.bojarski.sozz.model.domain.unit.QUnit;
import org.bojarski.sozz.model.domain.unit.Unit;
import org.bojarski.sozz.model.exception.AlreadyExistsException;
import org.bojarski.sozz.model.exception.NotFoundException;
import org.bojarski.sozz.model.exception.UsedException;
import org.bojarski.sozz.repository.part.PartRepository;
import org.bojarski.sozz.repository.requisition.RequisitionRepository;
import org.bojarski.sozz.repository.unit.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;

/**
 * Podstawowa implementacja serwisu jednostek miary.
 * @author Arkadiusz Bojarski
 */
@Service
@Transactional(readOnly = true, rollbackFor = {NotFoundException.class,
        UsedException.class})
public class DefaultUnitService implements UnitService {

    private final UnitRepository unitRepository;

    private final PartRepository partRepository;
    
    private final RequisitionRepository requisitionRepository;
    
    /**
     * Konstruktor przechowujący repozytorium jednostek, repozytorium części, repozytorium zapotrzebowań oraz narzędzia jednostek.
     * @param unitRepository repozytorium jednostek wykorzystywane jako źródło encji jednostek miary
     * @param partRepository repozytorium części wykorzystywane jako źródło encji części
     * @param requisitionRepository repozytorium zapotrzebowań wykorzystywane jako źródło encji zapotrzebowań
     */
    @Autowired
    public DefaultUnitService(UnitRepository unitRepository, PartRepository partRepository,
            RequisitionRepository requisitionRepository) {
        this.unitRepository = unitRepository;
        this.partRepository = partRepository;
        this.requisitionRepository = requisitionRepository;
    }
    
    @Override
    @Transactional(readOnly= false)
    public Unit create(Unit unit) {
        Optional<Unit> existing = Optional.ofNullable(unitRepository.findOneByName(unit.getName()));
        if(existing.isPresent()) {
            throw new AlreadyExistsException(Messages.UNIT_EXISTS, Messages.NAME, Messages.UNIT_EXISTS_DEFAULT);
        }
        
        return unitRepository.save(new Unit(unit.getName()));
    }
    
    @Override
    public Unit read(Long id) {
        Optional<Unit> existing = Optional.ofNullable(unitRepository.findOne(id));
        return existing.orElseThrow(() -> new NotFoundException(
                Messages.UNIT_NOT_FOUND,
                id.toString(),
                Messages.UNIT_NOT_FOUND_DEFAULT));
    }
    
    @Override
    @Transactional(readOnly = false)
    public Unit update(Long id, Unit unit) {
        Unit updated = read(id);
        Unit existing = unitRepository.findOneByName(unit.getName());
        if(existing != null && updated != existing) {
            throw new AlreadyExistsException(
                    Messages.UNIT_EXISTS,
                    Messages.NAME,
                    Messages.UNIT_EXISTS_DEFAULT);
        }
        
        updated.setName(unit.getName());
        
        return unitRepository.save(updated);
    } 

    @Override
    public Page<Unit> search(String query, PageRequest pageRequest) {
        QUnit unit = QUnit.unit;
        BooleanBuilder where = new BooleanBuilder();
        
        if(query != null) {
            where.and(unit.name.containsIgnoreCase(query));
        }
        
        return where.hasValue() ? unitRepository.findAll(where, pageRequest) : unitRepository.findAll(pageRequest);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        read(id);
        
        Collection<Part> usingParts = partRepository.findByDimensionsUnitId(id);
        if(!usingParts.isEmpty()) {
            throw new UsedException(Messages.UNIT_USED_BY_PART, id);
        }
        
        Collection<Requisition> usingRequisitions = requisitionRepository.findByUnitId(id);
        if(!usingRequisitions.isEmpty()) {
            throw new UsedException(Messages.UNIT_USED_BY_REQUISITION, id);
        }
        
        unitRepository.delete(id);
    }

}
