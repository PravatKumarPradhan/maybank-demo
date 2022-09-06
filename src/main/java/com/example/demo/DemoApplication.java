package com.example.demo;

import com.example.demo.util.DocConvertorXLSXToCSV;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		List<String> sheets = new ArrayList<>(2);
		sheets.add("b");
		sheets.add("c");



		try {
			File excel = ResourceUtils.getFile("classpath:Book1.xlsx").getAbsoluteFile();
			DocConvertorXLSXToCSV.conversionOfXlsxToCSVNames(excel.getAbsolutePath(), "test.csv", sheets);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
