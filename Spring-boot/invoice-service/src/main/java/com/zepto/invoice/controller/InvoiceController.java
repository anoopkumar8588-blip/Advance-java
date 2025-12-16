package com.zepto.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zepto.invoice.entity.InvoiceEntity;
import com.zepto.invoice.request.InvoiceRequest;
import com.zepto.invoice.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@PostMapping("generatedInvoice")
	public String generatedInvoice(@RequestBody InvoiceRequest invoiceRequest) {
		int invId = invoiceService.createInvoice(invoiceRequest);

		return " You invoice has been generated successfully. Invoice id is :" + invId;
	}

	@GetMapping("/{id}")
	public InvoiceEntity getInvoice(@PathVariable int id) {
		return invoiceService.getInvoice(id);
	}

	@GetMapping("/all")
	public List<InvoiceEntity> getAllInvoices() {
		return (List<InvoiceEntity>) invoiceService.getAllInvoices();
	}

	@PutMapping("/update/{id}")
	public InvoiceEntity updateInvoice(@PathVariable int id, @RequestBody InvoiceRequest request) {
		return invoiceService.updateInvoice(id, request);
	}

	@GetMapping("/getInv/{status}")
	public String findInvByStatus(@PathVariable String status) {
		List<InvoiceEntity> invoices = invoiceService.findInvoiceByStatus(status);
		return "your invoice desc is:" + invoices.get(0).getDescription();
	}

	@GetMapping("/status/{gstno}")
	public List<InvoiceEntity> doSomething(@PathVariable String gstno) {
		return invoiceService.doSomethingService(gstno);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteInvoice(@PathVariable int id) {
		return invoiceService.deleteInvoice(id);
	}

}
