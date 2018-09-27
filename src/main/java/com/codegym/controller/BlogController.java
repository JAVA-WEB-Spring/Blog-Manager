package com.codegym.controller;

import com.codegym.model.Brand;
import com.codegym.model.Blog;
import com.codegym.model.BlogForm;
import com.codegym.service.BrandService;
import com.codegym.service.BlogService;
import com.codegym.ultils.StorageUltils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BlogController {
    @Autowired
    BrandService brandService;
    @Autowired
    BlogService blogService;

    @ModelAttribute("brands")
    public Iterable<Brand> brands() {
        return brandService.findAll();
    }

    @GetMapping("/blogs")
    public ModelAndView showList(@RequestParam("s")Optional<String> s, Pageable pageable) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContaining(s.get(), pageable);
            ModelAndView modelAndView = new ModelAndView("/blog/list");
            modelAndView.addObject("blogs", blogs);
            return modelAndView;
        } else {
            blogs = blogService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("/blog/list");
            modelAndView.addObject("blogs", blogs);
            return modelAndView;
        }
    }

    @GetMapping("/")
    public ModelAndView showList2(@RequestParam("s")Optional<String> s, Pageable pageable) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContaining(s.get(), pageable);
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("blogs", blogs);
            return modelAndView;
        } else {
            blogs = blogService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("blogs", blogs);
            return modelAndView;
        }
    }

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blogForm", new Blog());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public ModelAndView createForm(@ModelAttribute("blogForm") BlogForm blogForm) throws IOException {
        ModelAndView modelAndView = new ModelAndView("/blog/create");

        String randomCode = UUID.randomUUID().toString();
        String originFileName = blogForm.getImage().getOriginalFilename();
        String randomName = randomCode + StorageUltils.getFileExtension(originFileName);
        blogForm.getImage().transferTo(new File(StorageUltils.FEATURE_LOCATION + "/" + randomName));

        Blog blog = new Blog();
        blog.setTitle(blogForm.getTitle());
        blog.setContent(blogForm.getContent());
        blog.setBrand(blogForm.getBrand());
        blog.setImage(randomName);
        blogService.save(blog);
        modelAndView.addObject("blogForm", new BlogForm());
        modelAndView.addObject("message", "New blog has been created");
        return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Blog blog = blogService.findById(id);
        BlogForm blogForm = new BlogForm();
        blogForm.setTitle(blog.getTitle());
        blogForm.setContent(blog.getContent());
        blogForm.setBrand(blog.getBrand());
        blogForm.setId(blog.getId());
        blogForm.setImageUrl(blog.getImage());
        if (blog == null) {
            return new ModelAndView("error-404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blogForm", blogForm);
            return modelAndView;
        }
    }

    @PostMapping("/edit-blog/{id}")
    public ModelAndView editBlog(@ModelAttribute("blogForm") BlogForm blogForm,
                                 @PathVariable("id") Long id) throws IOException {
        Blog blog = blogService.findById(id);
        if (!blogForm.getImage().isEmpty()) {
            StorageUltils.removeFeature(blog.getImage());
            String randomCode = UUID.randomUUID().toString();
            String originFileName = blogForm.getImage().getOriginalFilename();
            String randomName = randomCode + StorageUltils.getFileExtension(originFileName);
            blogForm.getImage().transferTo(new File(StorageUltils.FEATURE_LOCATION + "/" + randomName));
            blogForm.setImageUrl(randomName);
            blog.setImage(randomName);
        }
        blog.setTitle(blogForm.getTitle());
        blog.setContent(blogForm.getContent());
        blog.setBrand(blogForm.getBrand());
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blogForm", blogForm);
        modelAndView.addObject("message", "this blog was edited");
        return modelAndView;

    }

    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ModelAndView("error-404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/blog/delete");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        }
    }

    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog blog, RedirectAttributes redirectAttributes) {
        Object a = blog.getTitle();
        redirectAttributes.addFlashAttribute("success",a  +  " was delete");
        blogService.remove(blog.getId());
        return "redirect:/blogs";
    }

    @GetMapping("/view-blog/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ModelAndView("error-404");
        } else {
            ModelAndView modelAndView = new ModelAndView("view");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        }
    }


}
