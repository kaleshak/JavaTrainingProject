package com.patient.list;

public class Patient {
	private int patientID;
	private String patientName;
	private String patientAge;
	private String patientAddress;

	public Patient(int patientID, String patientName, String patientAge, String patientAddress) {
		super();
		this.patientID = patientID;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientAddress = patientAddress;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

}
