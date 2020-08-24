package com.mandeep.ims;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.wickedsource.docxstamper.DocxStamper;
import org.wickedsource.docxstamper.DocxStamperConfiguration;

import com.mandeep.ims.dto.InvoiceDocxDto;
import com.mandeep.ims.dto.ItemDto;

@SpringBootApplication
@EnableJpaRepositories
public class IMSApplication {

	@Autowired
	ResourceLoader resourceLoader;

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(IMSApplication.class, args);
		IMSApplication app = new IMSApplication();
		app.getFile();
	}

	public void getFile() throws IOException {
		InvoiceDocxDto a = new InvoiceDocxDto();
		a.setName("Parul");
		a.setPhoneNum("90354353");
		a.setAddress("erwe");
		List<ItemDto> list = new ArrayList<ItemDto>();
		list.add(new ItemDto("a", 1, 1.1f, 1.1f));
		list.add(new ItemDto("b", 2, 2.1f, 2.1f));
		a.setItems(list);
		Resource resource = new ClassPathResource("/templates/invoiceTemplate.docx");
		InputStream template = resource.getInputStream();
		OutputStream out = new FileOutputStream("invoice.docx");
		DocxStamper stamper = new DocxStamperConfiguration().build();
		stamper.stamp(template, a, out);

		out.close();
	}
}
