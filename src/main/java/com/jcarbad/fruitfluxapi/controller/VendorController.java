package com.jcarbad.fruitfluxapi.controller;

import com.jcarbad.fruitfluxapi.model.Vendor;
import com.jcarbad.fruitfluxapi.repository.VendorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VendorController {

   private final VendorRepository vendorRepository;

   public VendorController(VendorRepository vendorRepository) {
      this.vendorRepository = vendorRepository;
   }

   @GetMapping("/vendor")
   Flux<Vendor> list() {
      return vendorRepository.findAll();
   }

   @GetMapping("/vendor/{id}")
   Mono<Vendor> findById(@PathVariable String id) {
      return vendorRepository.findById(id);
   }

   @PutMapping("/vendor/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   Mono<Vendor> update(@PathVariable String id, @RequestBody Vendor category) {
      category.setId(id);
      return vendorRepository.save(category);
   }

   @DeleteMapping("/vendor/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   Mono<Void> delete(@PathVariable String id) {
      Mono<Vendor> vendor = findById(id);
      if (vendor == null)
         return Mono.empty();

      return vendor.flatMap(vendorRepository::delete);
   }
}
