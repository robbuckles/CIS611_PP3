package PP03;

import java.util.Date;


public class PayPeriod {
	
	private int pID;
    private Date pStartDate, pEndDate;
    
    // 1- add the class constructor
    // 2- add the setters/getters methods
    // 3- add override method toString() 

	public PayPeriod(int pID, Date start, Date end) {
        this.pID = pID;
        this.pStartDate = start;
        this.pEndDate = end;
    }

    @Override
    public String toString() {
        return "Period ID: " + pID + ", Start: " + pStartDate + ", End: " + pEndDate;
    }
    

	
}
