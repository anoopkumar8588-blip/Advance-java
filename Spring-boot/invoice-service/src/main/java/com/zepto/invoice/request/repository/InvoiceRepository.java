package com.zepto.invoice.request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zepto.invoice.entity.InvoiceEntity;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer> {

	// findByStatus(String status)-->select * from payment where status='Success'
	List<InvoiceEntity> findInvoiceByStatus(String status);
	
	 List<InvoiceEntity> findInvoiceBygstNo (String gstNo );
}
