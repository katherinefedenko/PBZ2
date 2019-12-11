package modelP;

public class Patient {
	private int key;
	private String surname;
	private String name;
	private String secondName;
	private String sex;
	private String age;
	private String diagnosis;
	private String howArrive;
	private String dateOfArrival;
	private String growth;
	private String hairColor;
	private String specialSigns;
	private String possibleSigns;
	private String numberOfRoom;
	private String telephoneNumber;
	private String transfer = "";
	private String dateOfDischarge;
	private String causeOfDischarge;

	public Patient() {
		key = 0;
		surname = "";
		name = "";
		secondName = "";
		sex = "";
		age = "";
		diagnosis = "";
		howArrive = "";
		dateOfArrival = "";
		growth = "";
		hairColor = "";
		specialSigns = "";
		possibleSigns = "";
		numberOfRoom = "";
		telephoneNumber = "";
		transfer = "";
		dateOfDischarge = "";
		causeOfDischarge = "";
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	public String getDiagnosis() {
		return diagnosis;
	}
	
	public void setHowArrive(String howArrive) {
		this.howArrive = howArrive;
	}
	
	public String getHowArrive() {
		return howArrive;
	}
	
	public void setDateOfArrival(String dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}
	
	public String getDateOfArrival() {
		return dateOfArrival;
	}
	
	public void setGrowth(String growth) {
		this.growth = growth;
	}
	
	public String getGrowth() {
		return growth;
	}
	
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	
	public String getHairColor() {
		return hairColor;
	}
	
	public void setPossibleSigns(String possibleSigns) {
		this.possibleSigns = possibleSigns;
	}
	
	public String getPossibleSigns() {
		return possibleSigns;
	}
	
	public void setSpecialSigns(String specialSigns) {
		this.specialSigns = specialSigns;
	}
	
	public String getSpecialSigns() {
		return specialSigns;
	}
	
	public void setNumberOfRoom(String numberOfRoom) {
		this.numberOfRoom = numberOfRoom;
	}
	
	public String getNumberOfRoom() {
		return numberOfRoom;
	}
	
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	
	public String getTransfer() {
		return transfer;
	}
	
	public void setDateOfDischarge(String dateOfDischarge) {
		this.dateOfDischarge = dateOfDischarge;
	}
	
	public String getDateOfDischarge() {
		return dateOfDischarge;
	}
	
	public void setCauseOfDischarge(String causeOfDischarge) {
		this.causeOfDischarge = causeOfDischarge;
	}
	
	public String getCauseOfDischarge() {
		return causeOfDischarge;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getAge() {
		return age;
	}
	
	
}
