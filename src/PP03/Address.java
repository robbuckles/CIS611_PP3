package PP03;

public class Address {
	
	private String street;
	private int houseNumber;
	private String city;
	private String state;
	private int zipCode;
	
	
	public Address(String street, int houseNumber, String city, String state, int zipCode) {
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public int getHouseNumber() {
		return houseNumber;
	}


	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getZipCode() {
		return zipCode;
	}


	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}


	@Override
	public String toString() {
		return "Address [street=" + street + ", houseNumber=" + houseNumber + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + "]";
	}
	
	
	

}
