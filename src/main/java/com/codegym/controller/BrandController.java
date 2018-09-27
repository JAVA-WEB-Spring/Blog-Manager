package com.codegym.controller;

import com.codegym.model.Brand;
import com.codegym.model.Blog;
import com.codegym.service.BrandService;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/brands")
    public ModelAndView showList() {
        Iterable<Brand> brands = brandService.findAll();
        ModelAndView modelAndView = new ModelAndView("/brand/list");
        modelAndView.addObject("brands", brands);
        return modelAndView;
    }

    @GetMapping("/create-brand")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/brand/create");
        modelAndView.addObject("brand", new Brand());
        return modelAndView;
    }

    @PostMapping("/create-brand")
    public ModelAndView updateBlog(@ModelAttribute("brand") Brand brand) {
        brandService.save(brand);
        ModelAndView modelAndView = new ModelAndView("/brand/create");
        modelAndView.addObject("brand", new Brand());
        modelAndView.addObject("message", "New blog has been updated");
        return modelAndView;
    }

    @GetMapping("/edit-brand/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Brand brand = brandService.findById(id);
        if (brand == null) {
            return new ModelAndView("error-404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/brand/edit");
            modelAndView.addObject("brand", brand);
            return modelAndView;
        }
    }

    @PostMapping("/edit-brand")
    public ModelAndView editBlog(@ModelAttribute("brand") Brand brand) {
        brandService.save(brand);
        ModelAndView modelAndView = new ModelAndView("/brand/edit");
        modelAndView.addObject("brand", brand);
        modelAndView.addObject("message", "This blog was edited");
        return modelAndView;
    }

    @GetMapping("/delete-brand/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Brand brand = brandService.findById(id);
        if (brand == null) {
            return new ModelAndView("error-404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/brand/delete");
            modelAndView.addObject("brand", brand);
            return modelAndView;
        }
    }

    @PostMapping("/delete-brand")
    public String deleteBlog(@ModelAttribute("brand") Brand brand) {
        brandService.remove(brand.getId());
        return "redirect:brands";
    }

    @GetMapping("/view-brand/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        Brand brand = brandService.findById(id);
        if (brand == null) {
            return new ModelAndView("error-404");
        }
            Iterable<Blog>  products = blogService.findAllByBrand(brand);
            ModelAndView modelAndView = new ModelAndView("/brand/view");
            modelAndView.addObject("brand", brand);
            modelAndView.addObject("products", products);
            return modelAndView;
    }
}
