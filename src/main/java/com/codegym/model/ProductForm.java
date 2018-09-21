package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private Long id;
    private String title;
    private String content;
    private Brand brand;
    private String imageUrl;
    private MultipartFile image;

    public ProductForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public ProductForm(Long id, String title, String content, Brand brand, String imageUrl, MultipartFile image) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.image = image;
    }
}
