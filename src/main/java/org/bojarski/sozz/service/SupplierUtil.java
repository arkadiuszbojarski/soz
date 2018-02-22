package org.bojarski.sozz.service;

import java.util.Optional;

import org.bojarski.sozz.model.Supplier;
import org.bojarski.sozz.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Klasa zawierająca metody pomocnicze dla dostawców.
 * @author Arkadiusz Bojarski
 *
 */
@Component
public class SupplierUtil {

    private final SupplierRepository supplierRepository;

    /**
     * Konstruktor zapamiętujący referencję do repozytorium dostawców.
     * @param supplierRepository
     */
    @Autowired
    public SupplierUtil(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    
    /**
     * Metoda pozwalająca na odczytanie lub utworzenie dostawcy w bazie.
     * @param supplier dostawca który ma być odczytany lub utworzony w bazie.
     * @return dostawca odczytany lub utworzony.
     */
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
