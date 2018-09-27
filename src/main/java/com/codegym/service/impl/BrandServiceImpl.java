package com.codegym.service.impl;

import com.codegym.model.Brand;
import com.codegym.repository.BrandRepository;
import com.codegym.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Override
    public Iterable<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findOne(id);
    }

    @Override
    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void remove(Long id) {
        brandRepository.delete(id);
    }
}
