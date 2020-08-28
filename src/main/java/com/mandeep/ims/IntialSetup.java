package com.mandeep.ims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mandeep.ims.entity.ItemType;
import com.mandeep.ims.repository.ItemTypeRepository;

@Component
public class IntialSetup {

	@Autowired
	private ItemTypeRepository itemTypeRepository;

	public IntialSetup() {
		if (itemTypeRepository.findByName("Mineral Water") == null)
			itemTypeRepository.save(new ItemType("Mineral Water"));

		if (itemTypeRepository.findByName("Reverse Osmosis") == null)
			itemTypeRepository.save(new ItemType("Reverse Osmosis"));

	}
}
