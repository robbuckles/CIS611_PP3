package PP03;

public class Person {
	
	protected String fName ;
    protected String lName ;
    protected Address address;
    
    public Person(){
    	
    }
    
	public Person(String fName, String lName, Address address) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.address = address;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAdsress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [fName=" + fName + ", lName=" + lName + ", address=" + address + "]";
	}
    

}
