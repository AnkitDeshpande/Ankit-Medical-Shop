package com.masai.dto;

import java.time.LocalDate;

public class Medicine {
    private String medId;
    private String name;
    private String company;
    private double pricePerUnit;
    private LocalDate mfgDate;
    private LocalDate expDate;
	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Medicine(String medId, String name, String company, double pricePerUnit, LocalDate mfgDate,
			LocalDate expDate) {
		super();
		this.medId = medId;
		this.name = name;
		this.company = company;
		this.pricePerUnit = pricePerUnit;
		this.mfgDate = mfgDate;
		this.expDate = expDate;
	}
	public String getMedId() {
		return medId;
	}
	public void setMedId(String medId) {
		this.medId = medId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public LocalDate getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(LocalDate mfgDate) {
		this.mfgDate = mfgDate;
	}
	public LocalDate getExpDate() {
		return expDate;
	}
	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}
	@Override
	public String toString() {
		return "Medicine [medId=" + medId + ", name=" + name + ", company=" + company + ", pricePerUnit=" + pricePerUnit
				+ ", mfgDate=" + mfgDate + ", expDate=" + expDate + "]\n";
	}
    
   
}
