package com.example.CartProject.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CartProject.model.Orders;
import com.example.CartProject.service.OrderService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("cart")
public class OrderController {
	
	@Autowired
	private OrderService service;

	@PostMapping(path="order", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Orders orders(@RequestBody Orders orders)
	{
		Time time = new Time(System.currentTimeMillis());
		orders.setTime(time);
		return service.order(orders);
	}
	
	@PostMapping(path="order/cancel", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void cancel(@RequestBody Orders order) {
		order.setStatus("Cancelled");
		service.cancel(order);
	}
	
	@GetMapping(path="order/bystatus")
	public Collection<Orders> getCancelledItems(@RequestParam String status)
	{
		return service.getByStatus(status);
	}
	
	@GetMapping(path="order/bydate")
	public Collection<Orders> getByDate(@RequestParam Date fromdate, Date todate)
	{
		return service.getByDate(fromdate, todate);
	}
	
	@GetMapping(path="report/bystatus")
	public ResponseEntity<Resource> getReportOfCancelled(String status) throws DocumentException
	{
		Collection<Orders> order = service.getByStatus(status);
		Document document = new Document();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, os);
		document.open();
		PdfPTable table = new PdfPTable(10);
		table.setSpacingAfter(25);
		table.setSpacingBefore(25);
		PdfPCell c1 = new PdfPCell(new Phrase("Order ID"));
		table.addCell(c1);
		PdfPCell c2 = new PdfPCell(new Phrase("Address"));
		table.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("Customer ID"));
		table.addCell(c3);
		PdfPCell c4 = new PdfPCell(new Phrase("Customer Name"));
		table.addCell(c4);
		PdfPCell c5 = new PdfPCell(new Phrase("Ordered Date"));
		table.addCell(c5);
		PdfPCell c6 = new PdfPCell(new Phrase("Price"));
		table.addCell(c6);
		PdfPCell c7 = new PdfPCell(new Phrase("Product Name"));
		table.addCell(c7);
		PdfPCell c8 = new PdfPCell(new Phrase("Product Type"));
		table.addCell(c8);
		PdfPCell c9 = new PdfPCell(new Phrase("Status"));
		table.addCell(c9);
		PdfPCell c10 = new PdfPCell(new Phrase("Ordered Time"));
		table.addCell(c10);
		
		for(Orders orders:order)
		{
			table.addCell(String.valueOf(orders.getOrder_id()));
			table.addCell(orders.getAddress());
			table.addCell(String.valueOf(orders.getCustomer_id()));
			table.addCell(orders.getCustomer_name());
			table.addCell(String.valueOf(orders.getDate()));
			table.addCell(String.valueOf(orders.getPrice()));
			table.addCell(orders.getProduct_name());
			table.addCell(orders.getProduct_type());
			table.addCell(orders.getStatus());
			table.addCell(String.valueOf(orders.getTime()));
		}
		
		document.add(table);
		document.close();
		
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=CancelledItemsReport.pdf");
		
		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}
	
}
