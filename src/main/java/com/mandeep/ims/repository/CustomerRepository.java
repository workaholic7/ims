package com.mandeep.ims.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mandeep.ims.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	Iterable<Customer> findByDeletedFalse();

	Optional<Customer> findByIdAndDeletedFalse(int id);

}
