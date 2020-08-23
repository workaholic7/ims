package com.mandeep.ims;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IMSApplication {

	public static void main(String[] args) {
		// SpringApplication.run(IMSApplication.class, args);
		XWPFDocument document = new XWPFDocument();
		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun titleRun = title.createRun();
		titleRun.setText("Bill To:");
		titleRun.setBold(true);
		XWPFTable table = document.createTable(2, 4);
		XWPFTableRow header = table.getRow(0);
		/*
		 * header.s header.getCell(0).setText("Item Description"); headerCell
		 * table.getRows().forEach(r -> { r.set });
		 */
		FileOutputStream out;
		try {
			out = new FileOutputStream("testdoc.docx");
			document.write(out);
			out.close();
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
