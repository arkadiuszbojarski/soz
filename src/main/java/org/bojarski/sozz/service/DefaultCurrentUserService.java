package org.bojarski.sozz.service;

import org.bojarski.sozz.model.Account;
import org.bojarski.sozz.model.Requisition;
import org.bojarski.sozz.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Implementacja serwisu aktualnego użytkownika.
 * @author Arkadiusz Bojarski
 *
 */
@Service
public class DefaultCurrentUserService implements CurrentUserService {
    
    private RequisitionRepository requisitionRepository;
    
    @Autowired
    public DefaultCurrentUserService(RequisitionRepository requisitionRepository) {
        this.requisitionRepository = requisitionRepository;
    }

    @Override
    public boolean canModifyRequisition(Long number) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Requisition requisition = requisitionRepository.findOneByNumberAndEndIsNull(number);
        return account != null && requisition != null && requisition.getAuthor().getId() == account.getId();
    }

}
