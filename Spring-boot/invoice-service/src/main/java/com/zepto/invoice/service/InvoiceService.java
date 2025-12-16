package com.zepto.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zepto.invoice.entity.InvoiceEntity;
import com.zepto.invoice.request.InvoiceRequest;
import com.zepto.invoice.request.repository.InvoiceRepository;

@Service
public class InvoiceService {
	@Autowired
	InvoiceRepository invoiceRepository;

	public int createInvoice(InvoiceRequest invoiceRequest) {

		InvoiceEntity entity = new InvoiceEntity();
		entity.setInvValue(invoiceRequest.getInvValue());
		entity.setGstNo(invoiceRequest.getGstNo());
		entity.setStatus(invoiceRequest.getStatus());
		entity.setNote(invoiceRequest.getNote());
		entity.setDescription(invoiceRequest.getDescription());

		entity = invoiceRepository.save(entity);// call repository layer
		return entity.getInvId();
	}

	// GET BY ID
	public InvoiceEntity getInvoice(int invId) {
		return invoiceRepository.findById(invId).orElse(null);
	}
	
	

	// GET findInvoice By Status
	public List<InvoiceEntity> findInvoiceByStatus(String status) {
		List<InvoiceEntity> invoices = invoiceRepository.findInvoiceByStatus(status);
		return invoices;
	}

	// Get findByNameQuery
	public List<InvoiceEntity> doSomethingService(String gstNo) {
		return invoiceRepository.findInvoiceBygstNo(gstNo);
	}

	// GET ALL
	public List getAllInvoices() {
		return (List) invoiceRepository.findAll();
	}

	// UPDATE
	public InvoiceEntity updateInvoice(int invId, InvoiceRequest invoiceRequest) {
		InvoiceEntity entity = invoiceRepository.findById(invId).orElse(null);

		if (entity != null) {
			entity.setInvValue(invoiceRequest.getInvValue());
			entity.setGstNo(invoiceRequest.getGstNo());
			entity.setStatus(invoiceRequest.getStatus());
			entity.setNote(invoiceRequest.getNote());
			entity.setDescription(invoiceRequest.getDescription());
			return invoiceRepository.save(entity);
		}

		return null;
	}

	// DELETE
	public String deleteInvoice(int invId) {
		if (invoiceRepository.existsById(invId)) {
			invoiceRepository.deleteById(invId);
			return "Invoice Deleted Successfully!";
		}
		return "Invoice Not Found!";
	}
}
