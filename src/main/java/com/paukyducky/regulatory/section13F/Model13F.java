package com.paukyducky.regulatory.section13F;

public class Model13F {
	
	private String CUSIP;
	private String issuerName;
	private String issuerDescription;
	private String status;
	
	
	public Model13F (String CUSIP, String issuerName, String issuerDescription, String status) {
		this.CUSIP=CUSIP;
		this.issuerName=issuerName;
		this.issuerDescription=issuerDescription;
		this.status=status;
		
	}
	
	
	
	
	public String getCUSIP() {
		return CUSIP;
	}
	public void setCUSIP(String cUSIP) {
		CUSIP = cUSIP;
	}
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	public String getIssuerDescription() {
		return issuerDescription;
	}
	public void setIssuerDescription(String issuerDescription) {
		this.issuerDescription = issuerDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
