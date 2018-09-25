package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(targetEntity = Blog.class)
    private List<Blog> blogs;

    public Brand() {
    }

    public Brand(String name, List<Blog> products) {
        this.name = name;
        this.blogs = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getProducts() {
        return blogs;
    }

    public void setProducts(List<Blog> products) {
        this.blogs = products;
    }
}
