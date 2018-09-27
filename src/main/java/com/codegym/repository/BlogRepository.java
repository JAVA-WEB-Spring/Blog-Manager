package com.codegym.repository;

import com.codegym.model.Brand;
import com.codegym.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);

    Iterable<Blog> findAllByBrand(Brand brand);
}
