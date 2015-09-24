package org.bojarski.sozz.model.domain.requisition;

import java.util.Date;

import javax.persistence.PrePersist;

import org.bojarski.sozz.model.domain.account.Account;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Klasa obsługująca audytowanie zapotrzebowań.
 * @author Arkadiusz Bojarski
 *
 */
public class RequisitionEntityListener {

    /**
     * Metoda obsługująca zdarzenie dodania nowego zapotrzebowania.
     * Ustala autora i datę utworzenia zapotrzebowania.
     * @param nowo dodawane zapotrzebowanie.
     */
    @PrePersist
    public void prePersist(Requisition requisition) {
        requisition.setStart(new Date());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        requisition.setAuthor((Account) securityContext.getAuthentication().getDetails());
    }
    
}
