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
    
    public Part readOrCreate(Part part) {
        if(part != null) {
            
            if(part.getId() != null) {
                Optional<Part> existingPart = Optional
                        .ofNullable(partRepository.findOne(part.getId()));

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
