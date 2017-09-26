package org.bojarski.sozz.service.drawing;

import java.util.Collection;
import java.util.Optional;

import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.domain.drawing.Drawing;
import org.bojarski.sozz.model.domain.drawing.QDrawing;
import org.bojarski.sozz.model.domain.requisition.Requisition;
import org.bojarski.sozz.model.exception.AlreadyExistsException;
import org.bojarski.sozz.model.exception.NotFoundException;
import org.bojarski.sozz.model.exception.UsedException;
import org.bojarski.sozz.repository.drawing.DrawingRepository;
import org.bojarski.sozz.repository.requisition.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;

/**
 * Implementacja interfejsu serwisu rysunków technicznych.
 * @author Arkadiusz Bojarski
 *
 */
@Service
@Transactional(readOnly = true)
public class DefaultDrawingService implements DrawingService {

    private final DrawingRepository drawingRepository;

    private final RequisitionRepository requisitionRepository;

    private final DrawingUtil drawingUtil;
    
    /**
     * Konstruktor przechowujący referencję do repozytorium rysunków i zapotrzebowań
     * oraz obiektu klasy pomocniczej rysunków.
     * @param drawingRepository
     * @param requisitionRepository
     * @param drawingUtil
     */
    @Autowired
    public DefaultDrawingService(DrawingRepository drawingRepository, RequisitionRepository requisitionRepository, DrawingUtil drawingUtil) {
        this.drawingRepository = drawingRepository;
        this.requisitionRepository = requisitionRepository;
        this.drawingUtil = drawingUtil;
    }
    
    @Override
    @Transactional(readOnly= false)
    public Drawing create(Drawing drawing) {
        Drawing existing = drawingRepository.findOneByNumber(drawing.getNumber());
        
        if(existing != null) {
            throw new AlreadyExistsException(Messages.DRAWING_EXISTS, Messages.NUMBER, Messages.DRAWING_EXISTS_DEFAULT);
        }
        
        return drawingRepository.save(new Drawing(drawing.getNumber()));
    }
    
    @Override
    public Drawing read(Long id) {
        Optional<Drawing> existing = Optional.ofNullable(drawingRepository.findOne(id));
        return existing.orElseThrow(() -> new NotFoundException(Messages.DRAWING_NOT_FOUND, id.toString(), Messages.DRAWING_NOT_FOUND_DEFAULT));
    }
    
    @Override
    @Transactional(readOnly = false)
    public Drawing update(Long id, Drawing drawing) {
        Drawing updated = read(id);
        
        Drawing existing = drawingRepository.findOneByNumber(drawing.getNumber());
        if(existing != null && updated != existing) {
            throw new AlreadyExistsException(Messages.DRAWING_EXISTS, Messages.NUMBER, Messages.DRAWING_EXISTS_DEFAULT);
        }
        
        updated.setNumber(drawing.getNumber());
        
        return drawingUtil.readOrCreate(updated);
    }

    @Override
    public Page<Drawing> search(String query, PageRequest pageRequest) {
        QDrawing drawing = QDrawing.drawing;
        BooleanBuilder where = new BooleanBuilder();
        
        if(query != null) {
            where.and(drawing.number.containsIgnoreCase(query));
        }
        
        return where.hasValue() ? drawingRepository.findAll(where, pageRequest) : drawingRepository.findAll(pageRequest);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        read(id);
        
        Collection<Requisition> usingRequisitions = requisitionRepository.findByDrawingId(id);
        if(!usingRequisitions.isEmpty()) {
            throw new UsedException(Messages.DRAWING_USED_BY_REQUISITION, id);
        }
        
        drawingRepository.delete(id);
    }

}
