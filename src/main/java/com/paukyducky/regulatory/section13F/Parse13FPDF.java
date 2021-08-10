package com.paukyducky.regulatory.section13F;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.paukyducky.regulatory.utilities.FileWriting;
import com.paukyducky.regulatory.utilities.PDFParserToString;




public class Parse13FPDF {
	
	

	private String fileName;
	private Map<String,Model13F> CUSIPList = new HashMap<>();
	private String testFileName;
	private String cusipPattern = "[a-zA-Z0-9]{1}[0-9]{1}[a-zA-Z0-9]"
			+ "{4}\\s\\s\\s\\s[a-zA-Z0-9]{2}\\s\\s\\s[0-9]";
	

	public void parse13FPDF() {
		



		
		PDFParserToString pdfParser = new PDFParserToString();

		
		File f = new File(fileName+".pdf");              // Construct a File.
	    if (f.exists()) {                           // Does it exist?
	      if (f.isFile() && f.canRead()) {          // is it a File and can I read it?
	        Scanner input = null;
	        try {
	          input = new Scanner(f);               // The Scanner!
	          while (input.hasNextLine()) {
	            String contents = input.nextLine();
	            System.out.println(contents);       // Print the lines.
	          }
	        } catch (FileNotFoundException e) {
	          e.printStackTrace();
	        } finally {
	          if (input != null) {
	            input.close();                      // Close the file scanner.
	          }
	        }
	      } else if (f.isDirectory()) {             // No, it's a directory!
	        try {
	          System.out.println("File "
	              + f.getCanonicalPath()
	              + " is a directory");
	        } catch (IOException e) {
	          e.printStackTrace();
	          
	        }
	      }
	    }
	    
	    
		pdfParser.setFileName(fileName+".pdf");
		String output = pdfParser.getOutput();

		
		FileWriting.fileCreator("", testFileName);
		FileWriting.textWriter("", testFileName, output);

		
		
		Scanner sc = new Scanner(output);
		// [a-zA-Z0-9]{1}[0-9]{1}[a-zA-Z0-9]{4}\\s\\s\\s\\s[a-zA-Z0-9]{2}\\s\\s\\s[0-9]
		// Regex Matching
		
		
		
		Pattern pattern = Pattern.compile(cusipPattern);
		Matcher matcher = pattern.matcher(output);
		
		
		int count = 0;
		List<String> lines = new ArrayList <String>();
		String line = null;
		String parsed = "CUSIP,issuerName,issuerDescription,status\n";
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			matcher = pattern.matcher(line);
			if (matcher.find()) {
				
				
				
				
				lines.add(line);
				


				String CUSIP = (line.substring(18,35).replaceAll("\\s", "")).trim();
				String issuerName = (line.substring(41,87).replaceAll("\\s\\s+", " ")).trim();
				String issuerDescription = (line.substring(87,110).replaceAll("\\s\\s+", " ")).trim();
				String status = (line.substring(117)).trim();
				count ++;

				CUSIPList.put(line.substring(18,35).replaceAll("\\s", "")
						, new Model13F(CUSIP,issuerName,issuerDescription,status));
				
				
				
				parsed = parsed + CUSIP +"," +issuerName+"," + issuerDescription+","+status+"\n";
				
			
			}

		}


		System.out.println("CUSIP Count: " + count);
		FileWriting.fileCreator("", fileName+".txt");
		FileWriting.textWriter("", fileName+".txt", parsed);
		
		sc.close();


	}


// PDF Name 
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Map<String, Model13F> getCUSIPList() {
		return CUSIPList;
	}



	public void setTestFileName(String testFileName) {
		this.testFileName = testFileName;
	}



	
	
	

}
