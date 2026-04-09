package PP03;


public class Employee extends Person {
	
	private int eID;
    private Status empStatus;
    
    // 1- The Employee class extends superclass Person
    // 2- add the subclass Employee constructor that calls the supper Person class constructor, you should provide input data for all parent class data fields
   // 3- add setters/getters methods
   // 4- add override toString() method that overrides toString() in the superclass Person 

	public Employee(int eID, String fName, String lName, Address addr, Status empStatus) {
        super(fName, lName, addr);
        this.eID = eID;
        this.empStatus = empStatus;
    }

    public Status getEmpStatus() {
        return empStatus;
    }

    public int getEID() {
        return eID;
    }

    @Override
    public String toString() {
        return "Employee ID: " + eID + ", Name: " + super.toString() + ", Status: " + empStatus;
    }

	
}
