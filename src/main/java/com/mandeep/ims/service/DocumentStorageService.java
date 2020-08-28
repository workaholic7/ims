package com.mandeep.ims.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mandeep.ims.entity.Invoice;
import com.mandeep.ims.entity.ItemDetail;
import com.mandeep.ims.exception.CustomException;

@Service
public class DocumentStorageService {

	private final Path fileStorageLocation;


	private String uploadDir = "/tmp/ims";
	
	Font bold = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	Font regular = new Font(FontFamily.TIMES_ROMAN, 11);

	@Autowired
	public DocumentStorageService() throws CustomException {
		this.fileStorageLocation = Paths.get(uploadDir)
				.toAbsolutePath().normalize();
		try {
			File file = new File(this.fileStorageLocation.toString());
			file.mkdir();
		} catch (Exception ex) {
			throw new CustomException("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

	public Resource loadFileAsResource(String fileName) throws Exception {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new FileNotFoundException("File not found " + fileName);
		}
	}

	public Resource createNewFile(Invoice invoice,String fileName) throws CustomException {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			createDocument(document, invoice);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}

		try {
			return new UrlResource(this.fileStorageLocation.resolve(fileName).normalize().toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;

	}

	private void createDocument(Document document, Invoice invoice) {
		try {
			Paragraph body = new Paragraph();
			addEmptyLine(body, 5);
			addCustomerTable(body, invoice);
			addEmptyLine(body, 3);
			
			PdfPTable table = new PdfPTable(1);
			PdfPCell cell = getCell("Item Details", PdfPCell.ALIGN_LEFT, true, false);
			cell.enableBorderSide(Rectangle.BOTTOM);
			cell.setBorderColorBottom(new BaseColor(100, 100, 255));
			table.addCell(cell);
			
			body.add(table);
			createItemTable(body, invoice.getItemList());
			
			document.add(body);

		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	private void addCustomerTable(Paragraph document, Invoice invoice) throws DocumentException {
		PdfPTable table = new PdfPTable(5);
		//table.setWidthPercentage(100);
		table.addCell(getCell("Bill To: ", PdfPCell.ALIGN_LEFT, true, false));
		table.addCell(getCell(invoice.getCustomer().getName(), PdfPCell.ALIGN_LEFT, false, false));
		table.addCell(getCell());
		table.addCell(getCell("Reference No: ", PdfPCell.ALIGN_LEFT, true, false));
		table.addCell(getCell(invoice.getReferenceNum(), PdfPCell.ALIGN_LEFT, false, false));
		table.addCell(getCell());
		table.addCell(getCell(invoice.getCustomer().getPhoneNum(), PdfPCell.ALIGN_LEFT, false, false));
		table.addCell(getCell());
		table.addCell(getCell("Date: ", PdfPCell.ALIGN_LEFT, true, false));
		table.addCell(getCell(String.valueOf(invoice.getDate()), PdfPCell.ALIGN_LEFT, false, false));
		table.addCell(getCell());
		table.addCell(getCell(invoice.getCustomer().getAddress().toString(), PdfPCell.ALIGN_LEFT, false, false, 2));
		table.addCell(getCell());
		table.addCell(getCell());
		document.add(table);
	}
	
	private void createItemTable(Paragraph document, List<ItemDetail> itemDetails)
			throws BadElementException {
		
		PdfPTable table = new PdfPTable(4);
		
		PdfPCell cell = getCell("", PdfPCell.ALIGN_LEFT, true, false, 4);
		cell.enableBorderSide(Rectangle.BOTTOM);
		
		table.addCell(cell);
		
		table.addCell(getCell("Item Description"));
		table.addCell(getCell("QTY"));
		table.addCell(getCell("Unit Price(RM)"));
		table.addCell(getCell("Amount(RM)"));
		
		//table.setHeaderRows(1);
		
		for(ItemDetail item:itemDetails) {
			table.addCell(item.getDescription());
			table.addCell(String.valueOf(item.getQuantity()));
			table.addCell(String.valueOf(item.getUnitPrice()));
			table.addCell(String.valueOf(item.getAmount()));
		}
		document.add(table);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	public PdfPCell getCell() {
		return getCell("", PdfPCell.ALIGN_LEFT, true, false, 0);
	}
	
	public PdfPCell getCell(String text, int alignment, boolean isBold, boolean border) {
		return getCell(text, alignment, isBold, border, 0);
	}
	
	public PdfPCell getCell(String text) {
		return getCell(text, PdfPCell.ALIGN_LEFT, true, true, 0);
	}

	public PdfPCell getCell(String text, int alignment, boolean isBold, boolean border, int colspan) {
		Phrase p = isBold ? new Phrase(text, bold):new Phrase(text);
		PdfPCell cell = new PdfPCell(p);
		cell.setPadding(0);
		cell.setMinimumHeight(20);
		
		cell.setHorizontalAlignment(alignment);
		if(!border)
			cell.setBorder(PdfPCell.NO_BORDER);
		if(colspan>0) {
			cell.setColspan(colspan);
		}
		return cell;
	}
	
}
