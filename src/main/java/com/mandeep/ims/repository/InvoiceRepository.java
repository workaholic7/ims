package com.mandeep.ims.repository;

import org.springframework.data.repository.CrudRepository;

import com.mandeep.ims.entity.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

}
