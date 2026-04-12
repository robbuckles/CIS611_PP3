//*********************************************************************
//*  
//*         CIS611 Spring 2026 James Benjamin & Rob Buckles  
//*  
//*              Programming project  PP03  
//*  
//*          This class is the PayRecords, and calculation of pay.  
//*          
//*  
//*  
//*                  12 APR 2026  
//*  
//*              Saved in: PayRecord.java  
//*  
//*********************************************************************
package PP03;


public class PayRecord {
	
	private int rID;
    private Employee employee;
    private PayPeriod payPeriod;
    private TaxIncome payTax;
    
    private double payHours;
    private double payRate;
    
    private double montlyIncome;
    private int numMonths;
    
       
    
    public static final int REG_HOURS = 40;
    public static final double OT_RATE = 1.25;
    
    // pay record constructor for hourly employee
    public PayRecord(int id, Employee e, PayPeriod period, double hours, double rate){
    	
    	this.rID = id;
    	this.employee = e;
    	this.payPeriod = period;
    	this.payHours = hours;
    	this.payRate = rate;
    	this.montlyIncome = 0;
    	this.numMonths = 0;
    	this.payTax = new TaxIncome();
  
    }
    
    // pay record constructor for full time employee
    public PayRecord(int id, Employee e, PayPeriod period, double mIncome, int mNum){
 	
 	this.rID = id;
 	this.employee = e;
 	this.payPeriod = period;
 	this.payHours = 0;
 	this.payRate = 0;
 	this.montlyIncome = mIncome;
 	this.numMonths = mNum;
 	this.payTax = new TaxIncome();

 }


  // 1- add setters and getters methods
  // 2- add override method toString()
  // 3- complete the code in the following methods: grossPay() and netPay()
    
    // complete the code to compute the gross pay for the employee based on the employee status
	public double grossPay(){
		if (employee.getEmpStatus() == Status.FULLTIME) {
	        return montlyIncome * numMonths;
	    } else {
	        if (payHours <= REG_HOURS) {
	            return payHours * payRate;
	        } else {
	            double overtime = payHours - REG_HOURS;
	            return (REG_HOURS * payRate) + (overtime * payRate * OT_RATE);
	        }
	    }
	}
    
  // complete the code in this method to compute the net pay of the employee after taxes (state and federal)
     public double netPay(){
    	 double gross = grossPay();
    	    double tax = payTax.compIncomeTax(gross);

    	    return gross - tax;
  }
  
     @Override
     public String toString(){

         double gross = grossPay();
         double tax = payTax.compIncomeTax(gross);
         double net = netPay();

         StringBuilder sb = new StringBuilder();

         sb.append("Pay Record ID: ").append(rID).append("\n");
         sb.append(employee.toString()).append("\n");
         sb.append(payPeriod.toString()).append("\n");

         if (employee.getEmpStatus() == Status.FULLTIME) {
             sb.append("Monthly Income: ").append(montlyIncome).append("\n");
             sb.append("Number of Months: ").append(numMonths).append("\n");
         } else {
             sb.append("Hours Worked: ").append(payHours).append("\n");
             sb.append("Hourly Rate: ").append(payRate).append("\n");
         }

         sb.append(String.format("%-15s %.2f%n", "Gross Pay:", gross));
         sb.append(String.format("%-15s %.2f%n", "Tax:", tax));
         sb.append(String.format("%-15s %.2f%n", "Net Pay:", net));

         return sb.toString();
     }
     
     public String toFileString() {

    	    double gross = grossPay();
    	    double tax = payTax.compIncomeTax(gross);
    	    double net = netPay();

    	    StringBuilder sb = new StringBuilder();

    	    sb.append(rID).append(", ");
    	    sb.append(employee.getEID()).append(", ");
    	    sb.append(employee.getEmpStatus()).append(", ");

    	    if (employee.getEmpStatus() == Status.FULLTIME) {
    	        sb.append(montlyIncome).append(", ");
    	        sb.append(numMonths).append(", ");
    	    } else {
    	        sb.append(payHours).append(", ");
    	        sb.append(payRate).append(", ");
    	    }

    	    sb.append(payPeriod.toString()).append(", ");
    	    sb.append(String.format("%.2f, %.2f, %.2f", gross, tax, net));

    	    return sb.toString();
    	}

}
