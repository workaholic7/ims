package com.mandeep.ims.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mandeep.ims.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
