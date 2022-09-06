package com.request.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class Request {
	private int id;

	@NotNull(message = "Bank Name cannot be empty.")
	@NotBlank(message = "Bank Name cannot be blank.")
	@Pattern(regexp = "^[a-zA-Z0-9-\\s]+$", message = "Invalid Bank Name.")
	private String bankName;

	@NotNull(message = "Branch Name cannot be empty.")
	@NotBlank(message = "Branch Name cannot be blank.")
	@Pattern(regexp = "^[a-zA-Z0-9-.,\\[\\]\\(\\)\\s]+$", message = "Invalid Branch Name.")
	private String branchName;

	@NotNull(message = "MICR Code cannot be empty.")
	@NotBlank(message = "MICR Code cannot be blank.")
	private String micrCode;

	@NotNull(message = "IFS Code cannot be empty.")
	@NotBlank(message = "IFS Code cannot be blank.")
	private String ifscCode;

	@NotNull(message = "Account No cannot be empty.")
	@NotBlank(message = "Account No cannot be blank.")
	@Pattern(regexp = "[0-9]+$", message = "Invalid Account No.")
	private String accountNo;

	@NotNull(message = "Customer Id cannot be empty.")
	@NotBlank(message = "Customer Id cannot be blank.")
	@Pattern(regexp = "[0-9]+$", message = "Invalid Customer Id.")
	private String customerId;

	@NotNull(message = "Title cannot be empty.")
	@NotBlank(message = "Title cannot be blank.")
	@Pattern(regexp = "^[A-Za-z./]+$", message = "Invalid title.")
	private String title;

	@NotNull(message = "Name cannot be empty.")
	@NotBlank(message = "Name cannot be blank.")
	@Pattern(regexp = "^['A-Za-z][a-zA-Z\\s\\-&',._]*$", message = "Invalid Name.")
	private String name;

	@NotNull(message = "Gender cannot be empty.")
	@NotBlank(message = "Gender cannot be blank.")
	@Pattern(regexp = "^(M|F|T)$", message = "Invalid Gender.")
	private String gender;

	@NotNull(message = "Date Of Birth cannot be empty.")
	@NotBlank(message = "Date Of Birth cannot be blank.")
	private String dob;

	@NotNull(message = "Address cannot be empty.")
	@NotBlank(message = "Address cannot be blank.")
	private String address;

	private String mobileNo;

	@Pattern(regexp = "^(\\s{0}|[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,4}))$", message = "Invalid Email Id.")
	private String emailId;

	@Pattern(regexp = "^(\\s{0}|[0-9]{12})$", message = "Invalid Aadhar No.")
	private String aadharNo;

	@NotNull(message = "Nominee Name cannot be empty.")
	@NotBlank(message = "Nominee Name cannot be blank.")
	@Pattern(regexp = "^['A-Za-z][a-zA-Z\\s\\-&',._]*$", message = "Invalid Nominee Name.")
	private String nomineeName;

	@Pattern(regexp = "^(\\s{0}|[0-9]{12})$", message = "Invalid Nominee Aadhar No.")
	private String nomineeAadharNo;

	@NotNull(message = "Nominee Relationship cannot be empty.")
	@NotBlank(message = "Nominee Relationship cannot be blank.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid Nominee Relationship.")
	private String nomineeRelationship;

	@NotNull(message = "Nominee Minor cannot be empty.")
	@NotBlank(message = "Nominee Minor cannot be blank.")
	@Pattern(regexp = "^(Y|N)$", message = "Invalid Nominee Minor.")
	private String isNomineeMinor;

	@Pattern(regexp = "^(\\s{0}|['A-Za-z][a-zA-Z\\s\\-&',._]*)$", message = "Invalid Gaurdian Name.")
	private String gaurdianName;

	@NotNull(message = "Pension Amount cannot be empty.")
	@NotBlank(message = "Pension Amount cannot be blank.")
	@Pattern(regexp = "^[0-9.]+$", message = "Invalid Pension Amount.")
	private String pensionAmt;

	@NotNull(message = "Monthly Contribution cannot be empty.")
	@NotBlank(message = "Monthly Contribution cannot be blank.")
	@Pattern(regexp = "^[0-9.]+$", message = "Invalid Monthly Contribution.")
	private String monthlyContribution;

	private String age;

	@NotNull(message = "Contribution Period cannot be empty.")
	@NotBlank(message = "Contribution Period cannot be blank.")
	private String contributionPeriod;

	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}+|(\\s{0})$", message = "Invalid Pan Number.")
	private String panNumber;

	public Request() {
		super();
	}

	public Request(int id, String bankName, String branchName, String micrCode, String ifscCode, String accountNo,
			String customerId, String title, String name, String gender, String dob, String address, String mobileNo,
			String emailId, String aadharNo, String nomineeName, String nomineeAadharNo, String nomineeRelationship,
			String isNomineeMinor, String gaurdianName, String pensionAmt, String monthlyContribution, String age,
			String contributionPeriod, String panNumber) {
		super();
		this.id = id;
		this.bankName = bankName;
		this.branchName = branchName;
		this.micrCode = micrCode;
		this.ifscCode = ifscCode;
		this.accountNo = accountNo;
		this.customerId = customerId;
		this.title = title;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.aadharNo = aadharNo;
		this.nomineeName = nomineeName;
		this.nomineeAadharNo = nomineeAadharNo;
		this.nomineeRelationship = nomineeRelationship;
		this.isNomineeMinor = isNomineeMinor;
		this.gaurdianName = gaurdianName;
		this.pensionAmt = pensionAmt;
		this.monthlyContribution = monthlyContribution;
		this.age = age;
		this.contributionPeriod = contributionPeriod;
		this.panNumber = panNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineeAadharNo() {
		return nomineeAadharNo;
	}

	public void setNomineeAadharNo(String nomineeAadharNo) {
		this.nomineeAadharNo = nomineeAadharNo;
	}

	public String getNomineeRelationship() {
		return nomineeRelationship;
	}

	public void setNomineeRelationship(String nomineeRelationship) {
		this.nomineeRelationship = nomineeRelationship;
	}

	public String getIsNomineeMinor() {
		return isNomineeMinor;
	}

	public void setIsNomineeMinor(String isNomineeMinor) {
		this.isNomineeMinor = isNomineeMinor;
	}

	public String getGaurdianName() {
		return gaurdianName;
	}

	public void setGaurdianName(String gaurdianName) {
		this.gaurdianName = gaurdianName;
	}

	public String getPensionAmt() {
		return pensionAmt;
	}

	public void setPensionAmt(String pensionAmt) {
		this.pensionAmt = pensionAmt;
	}

	public String getMonthlyContribution() {
		return monthlyContribution;
	}

	public void setMonthlyContribution(String monthlyContribution) {
		this.monthlyContribution = monthlyContribution;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getContributionPeriod() {
		return contributionPeriod;
	}

	public void setContributionPeriod(String contributionPeriod) {
		this.contributionPeriod = contributionPeriod;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

}
