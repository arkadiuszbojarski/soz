package org.bojarski.sozz.service.part;

import java.util.Collection;
import java.util.Optional;

import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.domain.part.PartSearchConditions;
import org.bojarski.sozz.model.domain.part.QPart;
import org.bojarski.sozz.model.domain.requisition.Requisition;
import org.bojarski.sozz.model.exception.AlreadyExistsException;
import org.bojarski.sozz.model.exception.NotFoundException;
import org.bojarski.sozz.model.exception.UsedException;
import org.bojarski.sozz.repository.part.PartRepository;
import org.bojarski.sozz.repository.requisition.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;

@Service
@Transactional(readOnly = true)
public class DefaultPartService implements PartService {

    private final PartRepository partRepository;
    
    private final RequisitionRepository requisitionRepository;
    
    private final PartUtil partUtil;
    
    @Autowired
    public DefaultPartService(PartRepository partRepository, RequisitionRepository requisitionRepository, PartUtil partUtil) {
        this.partRepository = partRepository;
        this.requisitionRepository = requisitionRepository;
        this.partUtil = partUtil;
    }
    
    @Override
    @Transactional(readOnly= false)
    public Part create(Part part) {
        Part existing = partRepository.findOneByNumber(part.getNumber());
        if(existing != null) {
            throw new AlreadyExistsException(Messages.PART_EXISTS, Messages.NUMBER, Messages.PART_EXISTS_DEFAULT);
        }
        
        return partRepository.save(partUtil.recreate(part));
    }
    
    @Override
    public Part read(Long id) {
        Optional<Part> existing = Optional.ofNullable(partRepository.findOne(id));
        return existing.orElseThrow(() -> new NotFoundException(Messages.PART_NOT_FOUND, id.toString(), Messages.PART_NOT_FOUND_DEFAULT));
    }
        
    @Override
    @Transactional(readOnly = false)
    public Part update(Long id, Part part) {
        Part updated = read(id);
        Part existing = partRepository.findOneByNumber(part.getNumber());
        
        if(existing != null && updated != existing) {
            throw new AlreadyExistsException(Messages.PART_EXISTS, Messages.NUMBER, Messages.PART_EXISTS_DEFAULT);
        }
        
        return partUtil.updateOrCreate(updated, part);
    }

    @Override
    public Page<Part> search(String query, PartSearchConditions conditions, PageRequest pageRequest) {
        QPart part = QPart.part;
        BooleanBuilder where = new BooleanBuilder();
        
        if(query != null){
            where.and(
                    part.number.containsIgnoreCase(query)
                    .or(part.description.containsIgnoreCase(query))
                    .or(part.category.name.containsIgnoreCase(query))
                    .or(part.material.containsIgnoreCase(query))
                    .or(part.supplier.name.containsIgnoreCase(query))
                    .or(part.supplier.contact.email.containsIgnoreCase(query))
                    .or(part.supplier.contact.web_site.containsIgnoreCase(query))
                    .or(part.supplier.contact.phone.containsIgnoreCase(query))
                    .or(part.supplier.contact.address.country.containsIgnoreCase(query))
                    .or(part.supplier.contact.address.city.containsIgnoreCase(query))
                    .or(part.supplier.contact.address.street.containsIgnoreCase(query)));
        }
        
        if(conditions.getNumber() != null) {
            where.and(part.number.containsIgnoreCase(conditions.getNumber()));
        }
        
        if(conditions.getDescription() != null) {
            where.and(part.description.containsIgnoreCase(conditions.getDescription()));
        }
        
        if(conditions.getCategory() != null) {
            where.and(part.category.name.containsIgnoreCase(conditions.getCategory()));
        }
        
        if(conditions.getMaterial() != null) {
            where.and(part.material.containsIgnoreCase(conditions.getMaterial()));
        }

        if(conditions.getSupplier() != null) {
            where.and(part.supplier.name.containsIgnoreCase(conditions.getSupplier()));
        }
        
        return where.hasValue() ? partRepository.findAll(where, pageRequest) : partRepository.findAll(pageRequest);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        read(id);
        
        Collection<Requisition> usingRequisitions = requisitionRepository.findByPartId(id);
        if(!usingRequisitions.isEmpty()) {
            throw new UsedException(Messages.PART_USED_BY_REQUISITION, id);
        }
        
        partRepository.delete(id);
    }

}
