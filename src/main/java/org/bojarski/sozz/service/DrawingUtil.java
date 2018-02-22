package org.bojarski.sozz.service;

import java.util.Optional;

import org.bojarski.sozz.model.Drawing;
import org.bojarski.sozz.repository.DrawingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Klasa zawierająca metody pomocnicze dla rysunków technicznych.
 * @author Arkadiusz Bojarski
 *
 */
@Component
public class DrawingUtil {

    private final DrawingRepository drawingRepository;

    /**
     * Konstruktor przechowujący referencję do repozytorium rysunków technicznych.
     * @param drawingRepository repozytorium rysunków technicznych.
     */
    @Autowired
    public DrawingUtil(DrawingRepository drawingRepository) {
        this.drawingRepository = drawingRepository;
    }

    /**
     * Metoda pozwalająca na odczytanie lub utworzenie w bazie rysunku technicznego.
     * @param drawing rysunek który ma być odczytane lub utworzony w bazie.
     * @return rysunek odczytany lub utworzony w bazie.
     */
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
