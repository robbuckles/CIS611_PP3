package PP03;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PayRoll {
	
	private String fileName;
	private PayRecord[] payRecords;
	private int count = 0;
	
	
	private  double totalNetPay;
	private  double avgNetPay;
	
	public PayRoll(String fileName, int n){
		
		this.fileName = fileName;
               // int n = 0;
				this.payRecords = new PayRecord[n];
		
	}
	
	
   public void readFromFile(){
		
		// read the initial data from PayRoll file to create the full 
	   // pay records with gross pay, taxes, and net pay, and then store it in PayRecord.txt file
	   
//	   System.out.println("readFromFile() started...");
//
//	    try {
//	        File file = new File(fileName);
//
//	        if (!file.exists()) {
//	            System.out.println("File NOT found: " + fileName);
//	            return;
//	        }
//
//	        BufferedReader br = new BufferedReader(new FileReader(file));
//
//	        String line;
//
//	        Employee[] employees = new Employee[100];
//	        int empCount = 0;
//
//	        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//
//	        while ((line = br.readLine()) != null) {
//
//	            System.out.println("LINE: " + line); // 🔥 DEBUG
//
//	            String[] data = line.split(",");
//
//	            if (data[0].trim().equalsIgnoreCase("employee")) {
//	                System.out.println("Employee detected"); // DEBUG
//	            }
//
//	            else if (data[0].trim().equalsIgnoreCase("payRecord")) {
//	                System.out.println("PayRecord detected"); // DEBUG
//	            }
//	        }
//
//	        br.close();
//
//	    } catch (Exception e) {
//	        System.out.println("ERROR: " + e.getMessage());
//	        e.printStackTrace();
//	    }
	   
	   try {
	        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fileName));

	        String line;

	        // Temporary employee storage
	        Employee[] employees = new Employee[100];
	        int empCount = 0;

	        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");

	        while ((line = br.readLine()) != null) {

	            String[] data = line.split(",");

	            // 🔹 EMPLOYEE LINE
	            if (data[0].trim().equalsIgnoreCase("employee")) {

	                int eID = Integer.parseInt(data[1].trim());
	                String fName = data[2].trim();
	                String lName = data[3].trim();
	                Status status = Status.valueOf(data[4].trim());

	                String street = data[5].trim();
	                int houseNum = Integer.parseInt(data[6].trim());
	                String city = data[7].trim();
	                String state = data[8].trim();
	                int zip = Integer.parseInt(data[9].trim());

	                Address addr = new Address(street, houseNum, city, state, zip);

	                Employee emp = new Employee(eID, fName, lName, addr, status);

	                employees[empCount++] = emp;
	            }

	            // 🔹 PAY RECORD LINE
	            else if (data[0].trim().equalsIgnoreCase("payRecord")) {

	                int rID = Integer.parseInt(data[1].trim());
	                int eID = Integer.parseInt(data[2].trim());

	                // Find matching employee
	                Employee emp = null;
	                for (int i = 0; i < empCount; i++) {
	                    if (employees[i].getEID() == eID) {
	                        emp = employees[i];
	                        break;
	                    }
	                }

	                if (emp == null) continue; // safety

	                int pID = Integer.parseInt(data[5].trim());
	                Date start = sdf.parse(data[6].trim());
	                Date end = sdf.parse(data[7].trim());

	                PayPeriod period = new PayPeriod(pID, start, end);

	                PayRecord record;

	                if (emp.getEmpStatus() == Status.FULLTIME) {

	                    double income = Double.parseDouble(
	                            data[3].replace("<m>", "").trim());

	                    int months = Integer.parseInt(
	                            data[4].replace("<n>", "").trim());

	                    record = new PayRecord(rID, emp, period, income, months);

	                } else {

	                    double hours = Double.parseDouble(
	                            data[3].replace("<h>", "").trim());

	                    double rate = Double.parseDouble(
	                            data[4].replace("<r>", "").trim());

	                    record = new PayRecord(rID, emp, period, hours, rate);
	                }

	                // Add to array
	                payRecords[count++] = record;

	                double net = record.netPay();
	                totalNetPay += net;
	                avgNetPay = totalNetPay / count;


	            }
	        }

	        br.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	} 
   
   



   public void writeToFile(PayRecord payRecord){
		
		// write employees' pay records to the PayRecord.txt file, it should add employee pay record to the current file data
	   try {
           PrintWriter pw = new PrintWriter(new FileWriter("PayRecord.txt", true));
           pw.println(payRecord.toString());
           pw.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
		
	} 
   
   public Employee createEmployee(int id, String fName, String lName, Address addr, Status status){
       return new Employee(id, fName, lName, addr, status);
		// creates a new Employee object and add it to the employees array, you need to pass parameters to this method
		
		
	}
	
 
   public void createPayRecord(int rID, Employee e, double hours, double rate,
           double mIncome, int numMonths, PayPeriod period){
		
		// creates a new PayRecord for an Employee object and add it to  the payRecords array, you need to pass parameters to this method
	   if (count >= payRecords.length) {
           System.out.println("Max employees reached!");
           return;
       }

       PayRecord record;

       if (e.getEmpStatus() == Status.HOURLY) {
           record = new PayRecord(rID, e, period, hours, rate);
       } else {
           record = new PayRecord(rID, e, period, mIncome, numMonths);
       }

       payRecords[count++] = record;

       double net = record.netPay();
       totalNetPay += net;
       avgNetPay = totalNetPay / count;


	}
	
	
    public  String displayPayRecord(){
		
		// it shows all payroll records for all currently added employee and the total net pay and average net pay in the GUI text area
    	// at should append data to text area, it must not overwrite data in the GUI text area
    	StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(payRecords[i].toString()).append("\n\n");
        }

        sb.append(String.format("Total Net Pay: %.2f%n", totalNetPay));
        sb.append(String.format("Average Net Pay: %.2f%n", avgNetPay));

        return sb.toString();
 
		
	}

    
   public double avgNetPay(){
		
		  	// returns the average of the total net pay of all added employees
	   if (count == 0) return 0;
       return totalNetPay / count;
	   
		
	}
    
   public String processPayRecordFromGUI(
	        int id,
	        String fName,
	        String lName,
	        Address addr,
	        Status status,
	        double salary,
	        double rate,
	        String startDateStr,
	        String endDateStr
	) {
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        sdf.setLenient(false);

	        Date start = sdf.parse(startDateStr);
	        Date end = sdf.parse(endDateStr);

	        if (!end.after(start)) {
	            return "ERROR: End date must be after start date.";
	        }

	        long diff = end.getTime() - start.getTime();
	        long days = diff / (1000 * 60 * 60 * 24);

	        if (days < 1) {
	            return "ERROR: Pay period must be at least 1 day.";
	        }

	        Employee emp = createEmployee(id, fName, lName, addr, status);

	        PayPeriod period = new PayPeriod(
	                (int)(Math.random() * 1000),
	                start,
	                end
	        );

	        int recordID = (int)(Math.random() * 1000);

	        if (status == Status.FULLTIME) {

	            int numMonths = (int)(days / 30);
	            if (numMonths < 1) numMonths = 1;

	            createPayRecord(
	                    recordID,
	                    emp,
	                    0,
	                    0,
	                    salary,
	                    numMonths,
	                    period
	            );

	        } else {

	            createPayRecord(
	                    recordID,
	                    emp,
	                    40,
	                    rate,
	                    0,
	                    0,
	                    period
	            );
	        }

	        return displayPayRecord();

	    } catch (Exception e) {
	        return "ERROR: Invalid input or date format.";
	    }
	}

}
