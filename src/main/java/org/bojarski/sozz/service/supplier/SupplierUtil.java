package org.bojarski.sozz.service.supplier;

import java.util.Optional;

import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.bojarski.sozz.repository.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierUtil {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierUtil(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    
    public Supplier readOrCreate(Supplier supplier) {
        if(supplier != null) {
            
            if(supplier.getId() != null) {
                Optional<Supplier> existingSupplier = Optional
                        .ofNullable(supplierRepository.findOne(supplier.getId()));

                if(existingSupplier.isPresent()) {
                    return existingSupplier.get();
                }
            }

            if(supplier.getName() != null) {
                Optional<Supplier> sameNameSupplier = Optional
                        .ofNullable(supplierRepository.findOneByName(supplier.getName()));

                if(sameNameSupplier.isPresent()) {
                    return sameNameSupplier.get();
                }
            }


            Supplier newSupplier = new Supplier(supplier.getName(), supplier.getContact());
            return supplierRepository.save(newSupplier);
        }
        
        return null;
    }
}
