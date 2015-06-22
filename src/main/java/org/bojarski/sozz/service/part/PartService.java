package org.bojarski.sozz.service.part;

import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.domain.part.PartSearchConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PartService {

    public Part create(Part part);
    
    public Part read(Long id);
       
    public Part update(Long id, Part part);
    
    public Page<Part> search(String query, PartSearchConditions conditions, PageRequest pageRequest);
    
    public void delete(Long id);
    
}
