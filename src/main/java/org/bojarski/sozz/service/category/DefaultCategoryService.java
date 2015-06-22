package org.bojarski.sozz.service.category;

import java.util.Collection;
import java.util.Optional;

import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.domain.category.Category;
import org.bojarski.sozz.model.domain.category.QCategory;
import org.bojarski.sozz.model.domain.part.Part;
import org.bojarski.sozz.model.exception.AlreadyExistsException;
import org.bojarski.sozz.model.exception.NotFoundException;
import org.bojarski.sozz.model.exception.UsedException;
import org.bojarski.sozz.repository.category.CategoryRepository;
import org.bojarski.sozz.repository.part.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;

@Service
@Transactional(readOnly = true)
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    
    private final PartRepository partRepository;

    private final CategoryUtil categoryUtil;
    
    @Autowired
    public DefaultCategoryService(CategoryRepository categoryRepository,
            PartRepository partRepository, CategoryUtil categoryUtil) {
        this.categoryRepository = categoryRepository;
        this.partRepository = partRepository;
        this.categoryUtil = categoryUtil;
    }
    
    @Override
    @Transactional(readOnly= false)
    public Category create(Category category) {
        Category existing = categoryRepository.findOneByName(category.getName());
        
        if(existing != null) {
            throw new AlreadyExistsException(Messages.CATEGORY_EXISTS, Messages.NAME, Messages.CATEGORY_EXISTS_DEFAULT);
        }
        
        return categoryRepository.save(new Category(category.getName()));
    }
    
    @Override
    public Category read(Long id) {
        Optional<Category> existing = Optional.ofNullable(categoryRepository.findOne(id));
        return existing.orElseThrow(() -> new NotFoundException(Messages.CATEGORY_NOT_FOUND, id.toString(), Messages.CATEGORY_NOT_FOUND_DEFAULT));
    }
    
    @Override
    @Transactional(readOnly = false)
    public Category update(Long id, Category category) {
        Category updated = read(id);
        Category existing = categoryRepository.findOneByName(category.getName());
        
        if(existing != null && updated != existing) {
            throw new AlreadyExistsException(Messages.CATEGORY_EXISTS, Messages.NAME, Messages.CATEGORY_EXISTS_DEFAULT);
        }
        
        updated.setName(category.getName());
        
        return categoryUtil.readOrCreate(updated);
    }
    
    @Override
    public Page<Category> search(String query, PageRequest pageRequest) {
        QCategory category = QCategory.category;
        BooleanBuilder where = new BooleanBuilder();

        if(query != null) {
            where.and(category.name.containsIgnoreCase(query));
        }
        
        return where.hasValue() ? categoryRepository.findAll(where, pageRequest) : categoryRepository.findAll(pageRequest);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        read(id);
        Collection<Part> usingParts = partRepository.findByCategoryId(id);
        if(!usingParts.isEmpty()) {
            throw new UsedException(Messages.CATEGORY_USED_BY_PART, id);
        }
        
        categoryRepository.delete(id);
    }

}
