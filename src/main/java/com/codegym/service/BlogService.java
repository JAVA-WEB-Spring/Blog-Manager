package com.codegym.service;

import com.codegym.model.Brand;
import com.codegym.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface BlogService {
    Page<Blog> findAll(Pageable pageable);

    Blog findById(Long id);

    void save(Blog product);

    void remove(Long id);

    Iterable<Blog> findAllByBrand(Brand brand);


    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);}
