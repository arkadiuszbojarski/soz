package org.bojarski.sozz.service.requisition;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.bojarski.sozz.model.domain.drawing.Drawing;
import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.domain.requisition.QRequisition;
import org.bojarski.sozz.model.domain.requisition.Requisition;
import org.bojarski.sozz.model.domain.requisition.Status;
import org.bojarski.sozz.model.domain.unit.Unit;
import org.bojarski.sozz.repository.requisition.RequisitionRepository;
import org.bojarski.sozz.service.drawing.DrawingUtil;
import org.bojarski.sozz.service.part.PartUtil;
import org.bojarski.sozz.service.unit.UnitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysema.query.BooleanBuilder;

@Component
public class RequisitionUtil {
    
    private AtomicLong sequence = new AtomicLong();

    private final RequisitionRepository requisitionRepository;
    
    private final PartUtil partUtil;

    private final UnitUtil unitUtil;

    private final DrawingUtil drawingUtil;
    
    @Autowired
    public RequisitionUtil(RequisitionRepository requisitionRepository, PartUtil partUtil,
            UnitUtil unitUtil, DrawingUtil drawingUtil) {
        this.requisitionRepository = requisitionRepository;
        this.partUtil = partUtil;
        this.unitUtil = unitUtil;
        this.drawingUtil = drawingUtil;
    }
    
    public void setLastSequence(Long sequence) {
        this.sequence.set(sequence);
    }

    public Requisition readOrCreate(Requisition requisition) {
        if(requisition != null) {
            
            QRequisition requisitionPredicate = QRequisition.requisition;
            BooleanBuilder where = new BooleanBuilder();
            
            if(requisition.getId() != null) {
                where.or(requisitionPredicate.id.eq(requisition.getId()));
            }
            if(!where.hasValue() && requisition.getNumber() != null) {
                where.or(requisitionPredicate.number.eq(requisition.getNumber()));
            }
            
            if(where.hasValue()) {
                Optional<Requisition> existingRequisition = Optional
                        .ofNullable((requisitionRepository.findOne(where)));

                if(existingRequisition.isPresent()) {
                    return existingRequisition.get();
                }
            }

            Part part = partUtil.readOrCreate(requisition.getPart());
            Unit unit = unitUtil.readOrCreate(requisition.getUnit());
            Drawing drawing = drawingUtil.readOrCreate(requisition.getDrawing());
            
            Requisition newRequisition = new Requisition(
                    sequence.getAndIncrement(),
                    Status.PLACED,
                    requisition.getElement(),
                    part,
                    requisition.getAmount(),
                    unit,
                    drawing,
                    requisition.getComment()
                    );


            return requisitionRepository.save(newRequisition);
        }

        return null;
    }
    
    public Requisition update(Requisition oldRequisition, Requisition requisition) {
        oldRequisition.setEnd(new Date());
        Requisition newRequisition = new Requisition(
                oldRequisition.getNumber(),
                requisition.getStatus(),
                requisition.getElement(),
                partUtil.readOrCreate(requisition.getPart()),
                requisition.getAmount(),
                unitUtil.readOrCreate(requisition.getUnit()),
                drawingUtil.readOrCreate(requisition.getDrawing()),
                requisition.getComment());
        requisitionRepository.save(oldRequisition);
        return requisitionRepository.save(newRequisition);
    }
    
    public Requisition create(Requisition requisition) {
        if(requisition != null) {
            Requisition recreated = new Requisition(
                    sequence.getAndIncrement(),
                    Status.PLACED,
                    requisition.getElement(),
                    partUtil.readOrCreate(requisition.getPart()),
                    requisition.getAmount(),
                    unitUtil.readOrCreate(requisition.getUnit()),
                    drawingUtil.readOrCreate(requisition.getDrawing()),
                    requisition.getComment());
            return recreated;
        }

        return null;
    }

}

