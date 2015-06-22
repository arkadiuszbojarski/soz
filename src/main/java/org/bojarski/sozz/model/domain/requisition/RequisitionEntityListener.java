package org.bojarski.sozz.model.domain.requisition;

import java.util.Date;

import javax.persistence.PrePersist;

import org.bojarski.sozz.model.domain.account.Account;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class RequisitionEntityListener {

    @PrePersist
    public void prePersist(Requisition requisition) {
        requisition.setStart(new Date());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        requisition.setAuthor((Account) securityContext.getAuthentication().getDetails());
    }
    
}
