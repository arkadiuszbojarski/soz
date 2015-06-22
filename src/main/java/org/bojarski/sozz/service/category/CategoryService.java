package org.bojarski.sozz.service.category;

import org.bojarski.sozz.model.domain.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CategoryService {

    public Category create(Category category);
   
    public Category read(Long id);
    
    public Category update(Long id, Category category);
    
    public Page<Category> search(String query, PageRequest pageRequest);
    
    public void delete(Long id);
    
}
