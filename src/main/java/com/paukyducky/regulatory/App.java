package com.paukyducky.regulatory;

import com.paukyducky.regulatory.section13F.Downloader;
import com.paukyducky.regulatory.section13F.Parse13FPDF;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//CUSIP Pattern: [0-9]{3}[a-zA-Z0-9]{2}[a-zA-Z0-9*@#][\s][a-zA-Z0-9*@#]{2}[\s][0-9]

public class App {

	public static void main(String[] args) {

		// load the spring configuration file

//		System.setProperty("javax.net.ssl.trustStore",
//				"C:\\Program Files\\Java\\jdk1.8.0_261\\jre\\lib\\security\\jssecacerts");
//		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

		// Downloader
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Downloader theDownloader = context.getBean("myDownloader", Downloader.class);
		theDownloader.fileDownloader();
		String fileName = theDownloader.getFileName();
		System.out.println(fileName);
		Parse13FPDF theParser = context.getBean("myParse13FPDF", Parse13FPDF.class);
		theParser.setFileName(fileName);
		theParser.parse13FPDF();
		
		
		

	}

}
