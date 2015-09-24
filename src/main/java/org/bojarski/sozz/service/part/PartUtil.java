package org.bojarski.sozz.service.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.bojarski.sozz.model.domain.category.Category;
import org.bojarski.sozz.model.domain.dimension.Dimension;
import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.bojarski.sozz.repository.part.PartRepository;
import org.bojarski.sozz.service.category.CategoryUtil;
import org.bojarski.sozz.service.supplier.SupplierUtil;
import org.bojarski.sozz.service.unit.UnitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Klasa zawierająca metody pomocnicze dla części.
 * @author Arkadiusz Bojarski
 *
 */
@Component
public class PartUtil {

    @Autowired
    private PartRepository partRepository;
    
    @Autowired
    private UnitUtil unitUtil;
    
    @Autowired
    private CategoryUtil categoryUtil;
    
    @Autowired
    private SupplierUtil supplierUtil;
    
    /**
     * Metoda pozwalająca na odczytanie lub utworzenie w bazie części.
     * @param part część która ma być odczytana lub utworzona w bazie.
     * @return część odczytana lub utworzona w bazie.
     */
    public Part readOrCreate(Part part) {
        if(part != null) {
            
            if(part.getId() != null) {
                Optional<Part> existingPart = Optional
                        .ofNullable(partRepository.findOne(part.getId()));

                if(existingPart.isPresent()) {
                    return existingPart.get();
                }
            }
            
            if(part.getNumber() != null) {
                Optional<Part> existingPart = Optional
                        .ofNullable(partRepository.findOneByNumber(part.getNumber()));
                
                if(existingPart.isPresent()) {
                    return existingPart.get();
                }
            }

            Category category = categoryUtil.readOrCreate(part.getCategory());
            Supplier supplier = supplierUtil.readOrCreate(part.getSupplier());
            Collection<Dimension> dimensions = new ArrayList<Dimension>();

            if(part.getDimensions() != null) {
                part.getDimensions().forEach((dimension) -> {
                    dimension.setUnit(unitUtil.readOrCreate(dimension.getUnit()));
                    dimensions.add(dimension);
                });
            }

            Part newPart = new Part(
                    part.getNumber(),
                    part.getDescription(),
                    category,
                    dimensions,
                    part.getMaterial(),
                    supplier);


            return partRepository.save(newPart);
        }

        return null;
    }
    
    /**
     * Metoda pozwalająca na zmodyfikowanie lub utworzenie części w bazie.
     * @param part część która ma być zmodyfikowana lub utworzona w bazie.
     * @param modified część będąca zmodyfikowaną postacią jaką ma przyjąć część.
     * @return zmodyfikowana lub utworzona część.
     */
    public Part updateOrCreate(Part part, Part modified) {
        if(part != null) {
            
            Category category = categoryUtil.readOrCreate(modified.getCategory());
            Supplier supplier = supplierUtil.readOrCreate(modified.getSupplier());
            Collection<Dimension> dimensions = new ArrayList<Dimension>();
            if(modified.getDimensions() != null) {
                modified.getDimensions().forEach((dimension) -> {
                    dimension.setUnit(unitUtil.readOrCreate(dimension.getUnit()));
                    dimensions.add(dimension);
                });
            }
            
            if(part.getId() != null) {
                Optional<Part> existing = Optional
                        .ofNullable(partRepository.findOne(part.getId()));

                if(existing.isPresent()) {
                    Part updated = existing.get();
                    updated.setCategory(category);
                    updated.setDescription(modified.getDescription());
                    updated.setDimensions(dimensions);
                    updated.setMaterial(modified.getMaterial());
                    updated.setNumber(modified.getNumber());
                    updated.setSupplier(supplier);
                    return partRepository.save(updated);
                }
            }

            Part created = new Part(
                    part.getNumber(),
                    part.getDescription(),
                    category,
                    dimensions,
                    part.getMaterial(),
                    supplier);

            return partRepository.save(created);
        }

        return null;
    }
    
    /**
     * Metoda pozwalająca na odtworzenie podanej części wraz z
     * odpowiednimi zależnościami.
     * @param part część która ma zostać odtworzona wraz z zależnościami.
     * @return część odtworzona wraz z zależnościami.
     */
    public Part recreate(Part part) {
        if(part != null) {
            
            Category category = categoryUtil.readOrCreate(part.getCategory());
            Supplier supplier = supplierUtil.readOrCreate(part.getSupplier());
            Collection<Dimension> dimensions = new ArrayList<Dimension>();
            if(part.getDimensions() != null) {
                part.getDimensions().forEach((dimension) -> {
                    dimension.setUnit(unitUtil.readOrCreate(dimension.getUnit()));
                    dimensions.add(dimension);
                });
            }

            Part recreated = new Part(
                    part.getNumber(),
                    part.getDescription(),
                    category,
                    dimensions,
                    part.getMaterial(),
                    supplier);

            return recreated;
        }
        
        return null;
    }
    
}
