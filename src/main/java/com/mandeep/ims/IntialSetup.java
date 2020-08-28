package com.mandeep.ims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mandeep.ims.entity.ItemType;
import com.mandeep.ims.repository.ItemTypeRepository;

@Component
public class IntialSetup {

	@Autowired
	private ItemTypeRepository itemTypeRepository;

	@EventListener
    public void appReady(ApplicationReadyEvent event) {

		if (itemTypeRepository.findByName("Mineral Water") == null)
			itemTypeRepository.save(new ItemType("Mineral Water"));

		if (itemTypeRepository.findByName("Reverse Osmosis") == null)
			itemTypeRepository.save(new ItemType("Reverse Osmosis"));
    }
	
}
