package com.example.library.service;

import com.example.library.entity.Category;
import com.example.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(int id, Category category) {
        return categoryRepository.findById(id).map(item->{
            item.setName(category.getName());
            return categoryRepository.save(item);
        }).orElseThrow();
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
