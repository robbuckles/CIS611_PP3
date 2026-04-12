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
    
    // Getters
    public int getPID() {
        return pID;
    }

    public Date getStartDate() {
        return pStartDate;
    }

    public Date getEndDate() {
        return pEndDate;
    }

    // Setters
    public void setPID(int pID) {
        this.pID = pID;
    }

    public void setStartDate(Date start) {
        this.pStartDate = start;
    }

    public void setEndDate(Date end) {
        this.pEndDate = end;
    }

    @Override
    public String toString() {
        return "Period ID: " + pID + ", Start: " + pStartDate + ", End: " + pEndDate;
    }
	
}
