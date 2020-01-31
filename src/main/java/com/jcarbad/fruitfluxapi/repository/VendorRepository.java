package com.jcarbad.fruitfluxapi.repository;

import com.jcarbad.fruitfluxapi.model.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
