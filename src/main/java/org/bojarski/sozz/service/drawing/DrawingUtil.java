package org.bojarski.sozz.service.drawing;

import java.util.Optional;

import org.bojarski.sozz.model.domain.drawing.Drawing;
import org.bojarski.sozz.repository.drawing.DrawingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DrawingUtil {

    private final DrawingRepository drawingRepository;

    @Autowired
    public DrawingUtil(DrawingRepository drawingRepository) {
        this.drawingRepository = drawingRepository;
    }

    public Drawing readOrCreate(Drawing drawing) {
        if(drawing != null) {
            
            if(drawing.getId() != null) {
                Optional<Drawing> existingDrawing = Optional
                        .ofNullable(drawingRepository.findOne(drawing.getId()));

                if(existingDrawing.isPresent()) {
                    return existingDrawing.get();
                }
            }

            if(drawing.getNumber() != null) {
                Optional<Drawing> sameNumberDrawing = Optional
                        .ofNullable(drawingRepository.findOneByNumber(drawing.getNumber()));

                if(sameNumberDrawing.isPresent()) {
                    return sameNumberDrawing.get();
                }
            }
            
            return drawingRepository.save(new Drawing(drawing.getNumber()));
        }
        
        return null;
    }
}
