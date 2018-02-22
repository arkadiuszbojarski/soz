package org.bojarski.sozz.service;

import java.util.Optional;

import org.bojarski.sozz.model.Category;
import org.bojarski.sozz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Klasa zawierająca metody pomocnicze dla kategorii.
 * @author Arkadiusz Bojarski
 *
 */
@Component
public class CategoryUtil {

    @Autowired
    private CategoryRepository categoryRepository;
    
    /**
     * Metoda pozwalająca na odczytanie z bazy lub skopiowanie podanej kategorii.
     * @param category kategoria która ma być odczytana z bazy lub skopiowana.
     * @return odczytana lub skopiowana kategoria.
     */
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
    
    /**
     * Metoda pozwalająca na odczytanie lub utworzenie w bazie podanej kategorii. 
     * @param category kategorie która ma być odczytana lub utworzona w bazie.
     * @return odczytana lub utworzona w bazie kategoria.
     */
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
