package org.bojarski.sozz.service.drawing;

import org.bojarski.sozz.model.domain.drawing.Drawing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DrawingService {
    
    Drawing create(Drawing drawing);
    
    Drawing read(Long id);
    
    Drawing update(Long id, Drawing drawing);

    Page<Drawing> search(String query, PageRequest pageRequest);

    void delete(Long id);

}
