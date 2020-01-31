package com.jcarbad.fruitfluxapi.repository;

import com.jcarbad.fruitfluxapi.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
