package com.jcarbad.fruitfluxapi.bootstrap;

import com.jcarbad.fruitfluxapi.model.Category;
import com.jcarbad.fruitfluxapi.model.Vendor;
import com.jcarbad.fruitfluxapi.repository.CategoryRepository;
import com.jcarbad.fruitfluxapi.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Component
public class Bootstrap implements CommandLineRunner {
   private final CategoryRepository categoryRepository;
   private final VendorRepository vendorRepository;

   public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
      this.categoryRepository = categoryRepository;
      this.vendorRepository = vendorRepository;
   }

   @Override
   public void run(String... args) throws Exception {
      if (categoryRepository.count().block() == 0L) {
         System.out.println("##### Loading Bootstrap Data");
         Stream.of("Fruits", "Nuts", "Breads", "Meats", "Eggs").forEach(cat -> {
            categoryRepository.save(Category.builder().description(cat).build()).block();
         });

         System.out.println("Loaded categories data..." + categoryRepository.count().block());

         Stream.of(
               Vendor.builder().firstName("Michael").lastName("Weston").build(),
               Vendor.builder().firstName("Jessie").lastName("Waters").build(),
               Vendor.builder().firstName("Bill").lastName("Myers").build(),
               Vendor.builder().firstName("Jimmy").lastName("Buffet").build(),
               Vendor.builder().firstName("Allan").lastName("Watts").build()
         )
            .forEach(vendor -> vendorRepository.save(vendor).block());

         System.out.println("Loaded vendors data..." + vendorRepository.count().block());
      }
   }
}
