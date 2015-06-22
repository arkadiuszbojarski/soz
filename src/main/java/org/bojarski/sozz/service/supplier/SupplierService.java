package org.bojarski.sozz.service.supplier;

import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.bojarski.sozz.model.domain.supplier.SupplierSearchConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface SupplierService {

    public Supplier create(Supplier supplier);
   
    public Supplier read(Long id);
    
    public Supplier update(Long id, Supplier supplier);
    
    public Page<Supplier> search(String query, SupplierSearchConditions conditions, PageRequest pageRequest);
    
    public void delete(Long id);
    
}