package org.bojarski.sozz.service.category;

import java.util.Optional;

import org.bojarski.sozz.model.domain.category.Category;
import org.bojarski.sozz.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryUtil {

    @Autowired
    private CategoryRepository categoryRepository;
    
    public Category readOrRecreate(Category category) {
        if(category != null) {
            Category resultingCategory = null;

            if(category.getId() != null) {
                Optional<Category> existingCategory = Optional
                        .ofNullable(categoryRepository.findOne(category.getId()));

                if(existingCategory.isPresent()) {
                    resultingCategory = existingCategory.get();
                }
            }

            if(resultingCategory == null && category.getName() != null) {
                Optional<Category> sameNameCategory = Optional
                        .ofNullable(categoryRepository.findOneByName(category.getName()));

                if(sameNameCategory.isPresent()) {
                    resultingCategory = sameNameCategory.get();
                }
            }

            if(resultingCategory == null) {
                resultingCategory = new Category(category.getName());
            }

            return resultingCategory;
        }
        
        return null;
    }
    
    public Category readOrCreate(Category category) {
        if(category != null) {

            if(category.getId() != null) {
                Optional<Category> existingCategory = Optional
                        .ofNullable(categoryRepository.findOne(category.getId()));

                if(existingCategory.isPresent()) {
                    return existingCategory.get();
                }
            }

            if(category.getName() != null) {
                Optional<Category> sameNameCategory = Optional
                        .ofNullable(categoryRepository.findOneByName(category.getName()));

                if(sameNameCategory.isPresent()) {
                    return sameNameCategory.get();
                }
            }
            
            return categoryRepository.save(new Category(category.getName()));
        }
        
        return null;
    }
    
}
