package com.jcarbad.fruitfluxapi.controller;

import com.jcarbad.fruitfluxapi.model.Category;
import com.jcarbad.fruitfluxapi.repository.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CategoryController {

   private final CategoryRepository categoryRepository;

   public CategoryController(CategoryRepository categoryRepository) {
      this.categoryRepository = categoryRepository;
   }

   @GetMapping("/category")
   Flux<Category> list() {
      return categoryRepository.findAll();
   }

   @GetMapping("/category/{id}")
   Mono<Category> getById(@PathVariable String id) {
      return categoryRepository.findById(id);
   }

   @PostMapping("/category")
   @ResponseStatus(HttpStatus.CREATED)
   Mono<Void> create(@RequestBody Publisher<Category> categoryStream) {
      return categoryRepository.saveAll(categoryStream).then();
   }

   @PutMapping("/category/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   Mono<Category> update(@PathVariable String id, @RequestBody Category category) {
      category.setId(id);
      return categoryRepository.save(category);
   }

   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("/category/{id}")
   Mono<Void> delete(@PathVariable String id) {
      Mono<Category> category = getById(id);
      if (category == null)
         return Mono.empty();

      return category.flatMap(categoryRepository::delete);
   }
}
