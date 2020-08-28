package com.mandeep.ims.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mandeep.ims.entity.ItemType;

@Repository
public interface ItemTypeRepository extends CrudRepository<ItemType, Integer> {

	public ItemType findByName(String string);

}
