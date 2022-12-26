package com.ems.operation.communication.response;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

	private String userId;
	private String employeeId;
	private String userType;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String orgEmail;
	private String personalEmail;
	private String countryCode;
	private String phoneNumber;
	private String officialDesignation;
	private long dateOfJoining;
	private long dateOfBirth;
	private String bloodGroup;
	private String maritalStatus;
	private List<UserResponse> emergencyContactResponseList = new ArrayList<>();
	private String relationship;
	private String status;
	private boolean isConsentAccepted;
//	private List<AddressResponse> addressResponseList = new ArrayList<>();
//	private List<EducationResponse> educationResponseList = new ArrayList<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOrgEmail() {
		return orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOfficialDesignation() {
		return officialDesignation;
	}

	public void setOfficialDesignation(String officialDesignation) {
		this.officialDesignation = officialDesignation;
	}

	public long getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(long dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public List<UserResponse> getEmergencyContactResponseList() {
		return emergencyContactResponseList;
	}

	public void setEmergencyContactResponseList(List<UserResponse> emergencyContactResponseList) {
		this.emergencyContactResponseList = emergencyContactResponseList;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isConsentAccepted() {
		return isConsentAccepted;
	}

	public void setConsentAccepted(boolean isConsentAccepted) {
		this.isConsentAccepted = isConsentAccepted;
	}

//	public List<AddressResponse> getAddressResponseList() {
//		return addressResponseList;
//	}
//
//	public void setAddressResponseList(List<AddressResponse> addressResponseList) {
//		this.addressResponseList = addressResponseList;
//	}
//
//	public List<EducationResponse> getEducationResponseList() {
//		return educationResponseList;
//	}
//
//	public void setEducationResponseList(List<EducationResponse> educationResponseList) {
//		this.educationResponseList = educationResponseList;
//	}

}
