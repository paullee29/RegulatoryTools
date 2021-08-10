package com.paukyducky.regulatory.test;

import java.util.HashMap;
import java.util.Map;


import com.paukyducky.regulatory.section13F.Model13F;
import com.paukyducky.regulatory.section13F.Parse13FPDF;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Testing");
		Parse13FPDF parser = new Parse13FPDF();
		parser.parse13FPDF();
		
		
		System.out.println("Output");
		Map<String,Model13F> parsedObject = parser.getCUSIPList();
		System.out.println("Size: " +parsedObject.size());

		
		int count=0;
		for (String CUSIP : parsedObject.keySet()) {
			Model13F model = parsedObject.get(CUSIP);
			
			System.out.println(CUSIP+ ","+model.getIssuerName()+","+model.getIssuerDescription()+","+model.getStatus());
			count ++;
			
			
		}
		System.out.println("CUSIP Count Test:" + count);
		

	}

}
