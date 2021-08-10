package com.paukyducky.regulatory.section13F;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.IsoFields;
import java.util.Date;


import org.apache.commons.io.FileUtils;

public class PDFDownloader implements Downloader {
	
	private String urlLink;
	private String fileName;
	
	public PDFDownloader () {
		System.out.println("Inside PDFDownloader constructor");
	}
	
	
	
	@Override
	public void fileDownloader () {
		System.out.println("Inside fileDownloader");

		urlLink = generateURLLink();
		fileName = getFileName();
		
		try {
			
			FileUtils.copyURLToFile(
					  new URL(urlLink), 
					  new File(fileName+".pdf") );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private String generateURLLink () {
		//https://www.sec.gov/files/investment/13flist2021q2.pdf
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year  = localDate.getYear();
		int quarter = localDate.get(IsoFields.QUARTER_OF_YEAR);
		if (quarter == 4) {
			quarter = 1;
		} else {
			quarter-=1;
		}
		urlLink = "https://www.sec.gov/files/investment/13flist" + year + "q" + quarter + ".pdf";
		System.out.println("Downloading File: " + urlLink);
		this.urlLink = urlLink;
		return urlLink;
	}
	

	
	
	// https://www.sec.gov/divisions/investment/13f/13flist2019q2.pdf
	public String getFileName() {
		fileName = urlLink.substring(urlLink.indexOf("13f"), urlLink.indexOf(".pdf"));
		return fileName;
	}



}
