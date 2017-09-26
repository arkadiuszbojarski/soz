package org.bojarski.sozz.service.supplier;

import java.util.Collection;
import java.util.Optional;

import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.domain.supplier.QSupplier;
import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.bojarski.sozz.model.domain.supplier.SupplierSearchConditions;
import org.bojarski.sozz.model.exception.AlreadyExistsException;
import org.bojarski.sozz.model.exception.NotFoundException;
import org.bojarski.sozz.model.exception.UsedException;
import org.bojarski.sozz.repository.part.PartRepository;
import org.bojarski.sozz.repository.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;

/**
 * Implementacja interfejsu serwisu dostawców.
 * @author Arkadiusz Bojarski
 *
 */
@Service
@Transactional(readOnly = true)
public class DefaultSupplierService implements SupplierService {

    private final SupplierRepository supplierRepository;

    private final PartRepository partRepository;

    private final SupplierUtil supplierUtil;

    /**
     * Konstruktor przechowujący referencję do obiektów repozytorium dostawców,
     * repozytorium części oraz klasy pomocniczej dla dostawców.
     * @param supplierRepository
     * @param partRepository
     * @param supplierUtil
     */
    @Autowired
    public DefaultSupplierService(SupplierRepository supplierRepository,
            PartRepository partRepository, SupplierUtil supplierUtil) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.supplierUtil = supplierUtil;
    }
    
    @Override
    @Transactional(readOnly= false)
    public Supplier create(Supplier supplier) {
        Supplier existing = supplierRepository.findOneByName(supplier.getName());
        
        if(existing != null) {
            throw new AlreadyExistsException(Messages.SUPPLIER_EXISTS, Messages.NAME, Messages.SUPPLIER_EXISTS_DEFAULT);
        }
        
        return supplierRepository.save(new Supplier(supplier.getName(), supplier.getContact()));
    }
    
    @Override
    public Supplier read(Long id) {
        Optional<Supplier> existing = Optional.ofNullable(supplierRepository.findOne(id));
        return existing.orElseThrow(() -> new NotFoundException(Messages.SUPPLIER_NOT_FOUND, id.toString(), Messages.SUPPLIER_NOT_FOUND_DEFAULT));
    }
    
    @Override
    @Transactional(readOnly = false)
    public Supplier update(Long id, Supplier supplier) {
        Supplier updated = read(id);
        Supplier existing = supplierRepository.findOneByName(supplier.getName());
        
        if(existing != null && updated != existing) {
            throw new AlreadyExistsException(Messages.SUPPLIER_EXISTS, Messages.NAME, Messages.SUPPLIER_EXISTS_DEFAULT);
        }
        
        updated.setName(supplier.getName());
        updated.setContact(supplier.getContact());
        
        return supplierUtil.readOrCreate(updated);
    }

    @Override
    public Page<Supplier> search(String query, SupplierSearchConditions conditions, PageRequest pageRequest) {
        QSupplier supplier = QSupplier.supplier;
        BooleanBuilder where = new BooleanBuilder();
        
        if(query != null) {
            where.and(
                    supplier.name.containsIgnoreCase(query)
                    .or(supplier.contact.email.containsIgnoreCase(query))
                    .or(supplier.contact.web_site.containsIgnoreCase(query))
                    .or(supplier.contact.phone.containsIgnoreCase(query))
                    .or(supplier.contact.address.country.containsIgnoreCase(query))
                    .or(supplier.contact.address.city.containsIgnoreCase(query))
                    .or(supplier.contact.address.street.containsIgnoreCase(query)));
        }
        
        if(conditions.getName() != null) {
            where.and(supplier.name.containsIgnoreCase(conditions.getName()));
        }
        
        if(conditions.getEmail() != null) {
            where.and(supplier.contact.email.containsIgnoreCase(conditions.getEmail()));
        }
        
        if(conditions.getWeb() != null) {
            where.and(supplier.contact.web_site.containsIgnoreCase(conditions.getWeb()));
        }
        
        if(conditions.getPhone() != null) {
            where.and(supplier.contact.phone.containsIgnoreCase(conditions.getPhone()));
        }
        
        if(conditions.getCountry() != null) {
            where.and(supplier.contact.address.country.containsIgnoreCase(conditions.getCountry()));
        }
        
        if(conditions.getCity() != null) {
            where.and(supplier.contact.address.city.containsIgnoreCase(conditions.getCity()));
        }
        
        if(conditions.getStreet() != null) {
            where.and(supplier.contact.address.street.containsIgnoreCase(conditions.getStreet()));
        }
        
        return where.hasValue() ? supplierRepository.findAll(where, pageRequest) : supplierRepository.findAll(pageRequest);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        read(id);
        Collection<Part> usingParts = partRepository.findBySupplierId(id);
        if(!usingParts.isEmpty()) {
            throw new UsedException(Messages.SUPPLIER_USED_BY_PART, id);
        }
        
        supplierRepository.delete(id);
    }

}
