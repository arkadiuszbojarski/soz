package org.bojarski.sozz.service.requisition;

import org.bojarski.sozz.repository.requisition.RequisitionRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequisitionSequenceInitializer implements InitializingBean {

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Autowired
    private RequisitionUtil requisitionUtil;

    @Override
    public void afterPropertiesSet() throws Exception {
        Long sequence = requisitionRepository.getLastSequence();
        if(sequence == null) { sequence = 1L; }
        requisitionUtil.setLastSequence(sequence);
    }

 }
