//********************************************************************* 
//* * 
//* 		CIS611 Spring 2026 James Benjamin & Rob Buckles * 
//* * 
//* 			 Programming project  PP03 * 
//* * 
//*			 This project implements a payroll system to calculate the monthly/hourly pay for n employees
//*				including the employees in the input data file “PayRoll.txt”.
//* * 
//* * 
//* 				 10 APR 2026 * 
//* * 
//* 			 Saved in: UserGUI.java * 
//* * 
//********************************************************************* 

package PP03;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Font;

public class UserGUI extends JPanel {
    private JLabel idLabel, fNameLabel, lNameLabel, streetLabel, houseNumLabel, cityLabel, zipLabel;
    private JLabel startDateLabel, endDateLabel;
    private JTextField idField, fNameField, lNameField;
    private JTextField streetField, houseNumField, cityField, zipField;
    private JTextField startDateField, endDateField;
    
    private JComboBox<String> stateCombo;
    private JRadioButton fullTimeBtn, hourlyBtn;
    private ButtonGroup statusGroup;
    
    private JButton addEmployeeBtn; 
    private JButton CloseButton;
    private JTextArea textArea;
    private JScrollPane jp;
    
    private PayRoll payRoll;
    private String fileName = "PayRoll.txt";

    private JLabel salaryLabel, rateLabel;
    private JTextField salaryField, rateField;  
	  
    public UserGUI() {
        // Note: 'n' needs a value. Setting to 10 as a placeholder.
        int n = 10; 
        payRoll = new PayRoll(fileName, 100);

        initGUI();
        doTheLayout();
        
        payRoll.readFromFile();
        textArea.append(payRoll.displayPayRecord());

        addEmployeeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e){
                transfer();
            }
        });
        
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e){
                close();
            }
        });
    }

    private void initGUI(){
        // Employee Info
        idLabel = new JLabel("Employee ID:");
        idField = new JTextField(10);
        fNameLabel = new JLabel("First Name:");
        fNameField = new JTextField(15);
        lNameLabel = new JLabel("Last Name:");
        lNameField = new JTextField(15);

        // Address Info
        streetLabel = new JLabel("Street:");
        streetField = new JTextField(20);
        houseNumLabel = new JLabel("House #:");
        houseNumField = new JTextField(5);
        cityLabel = new JLabel("City:");
        cityField = new JTextField(15);
        zipLabel = new JLabel("Zip Code:");
        zipField = new JTextField(5);

        // State Selection
        String[] states = { "CT", "NY", "NJ", "MA", "PA" };
        stateCombo = new JComboBox<>(states);

        // Status Selection
        fullTimeBtn = new JRadioButton("Full Time", true);
        hourlyBtn = new JRadioButton("Hourly");
        statusGroup = new ButtonGroup();
        statusGroup.add(fullTimeBtn);
        statusGroup.add(hourlyBtn);

        // Timing Components 
        startDateLabel = new JLabel("Start Date (MM/dd/yyyy):");
        startDateField = new JTextField(10);
        endDateLabel = new JLabel("End Date (MM/dd/yyyy):");
        endDateField = new JTextField(10);

        // Results Area
        textArea = new JTextArea(10, 30);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textArea.setEditable(false);
        jp = new JScrollPane(textArea);

        // Buttons
        addEmployeeBtn = new JButton("Add Employee");
        CloseButton = new JButton("Close");

        //Salary/Rate
        salaryLabel = new JLabel("Monthly Salary:");
        salaryField = new JTextField(10);
        rateLabel = new JLabel("Hourly Rate:");
        rateField = new JTextField(10);
    }

    private void doTheLayout(){
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Employee Information"));

        inputPanel.add(idLabel);        inputPanel.add(idField);
        inputPanel.add(fNameLabel);     inputPanel.add(fNameField);
        inputPanel.add(lNameLabel);     inputPanel.add(lNameField);
        inputPanel.add(streetLabel);    inputPanel.add(streetField);
        inputPanel.add(houseNumLabel);  inputPanel.add(houseNumField);
        inputPanel.add(cityLabel);      inputPanel.add(cityField);
        inputPanel.add(new JLabel("State:")); inputPanel.add(stateCombo);
        inputPanel.add(zipLabel);       inputPanel.add(zipField);
        inputPanel.add(startDateLabel); inputPanel.add(startDateField);
        inputPanel.add(endDateLabel);   inputPanel.add(endDateField);
        inputPanel.add(salaryLabel);    inputPanel.add(salaryField);
        inputPanel.add(rateLabel);      inputPanel.add(rateField);
		
        inputPanel.add(new JLabel("Status:"));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(fullTimeBtn);
        radioPanel.add(hourlyBtn);
        inputPanel.add(radioPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addEmployeeBtn);
        buttonPanel.add(CloseButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(jp, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    
    void transfer() {

        if (idField.getText().isEmpty() ||
            fNameField.getText().isEmpty() ||
            lNameField.getText().isEmpty() ||
            streetField.getText().isEmpty() ||
            houseNumField.getText().isEmpty() ||
            cityField.getText().isEmpty() ||
            zipField.getText().isEmpty() ||
            startDateField.getText().isEmpty() ||
            endDateField.getText().isEmpty()) {

            textArea.append("ERROR: All fields must be filled.\n");
            return;
        }

        try {
            int id = Integer.parseInt(idField.getText());
            int houseNum = Integer.parseInt(houseNumField.getText());
            int zip = Integer.parseInt(zipField.getText());

            Address addr = new Address(
                    streetField.getText(),
                    houseNum,
                    cityField.getText(),
                    stateCombo.getSelectedItem().toString(),
                    zip
            );

            Status status = fullTimeBtn.isSelected()
                    ? Status.FULLTIME
                    : Status.HOURLY;

            double salary = salaryField.getText().isEmpty()
                    ? 0
                    : Double.parseDouble(salaryField.getText());

            double rate = rateField.getText().isEmpty()
                    ? 0
                    : Double.parseDouble(rateField.getText());

            String result = payRoll.processPayRecordFromGUI(
                    id,
                    fNameField.getText(),
                    lNameField.getText(),
                    addr,
                    status,
                    salary,
                    rate,
                    startDateField.getText(),
                    endDateField.getText()
            );

            textArea.setText(result);

        } catch (NumberFormatException e) {
            textArea.append("ERROR: Invalid numeric input.\n");
        }
    }
//    void transfer() {
//		// 1. Setup the date formatter to match label hint
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//		sdf.setLenient(false); // Make sure '13/45/2026' is rejected
//
//		try {
//			textArea.setText(""); // Clear previous results
//            
//            // 2. Parse the text into Date objects
//			Date start = sdf.parse(startDateField.getText());
//			Date end = sdf.parse(endDateField.getText());
//
//			// 3. Validation: End date must be after start date
//			if (!end.after(start)) {
//				textArea.append("ERROR: End date must be after Start date.\n");
//				return; // Stop here if date is invalid
//			}
//
//			// 4. Validation: At least one day long (24 hours)
//			long diffInMillies = Math.abs(end.getTime() - start.getTime());
//			long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
//
//			if (diffInDays < 1) {
//				textArea.append("ERROR: Pay period must be at least 1 day.\n");
//				return;
//			}
//
//			// 5. Success! Now we can display the info
//            textArea.append(String.format("%-18s %s\n", "Validated for:", fNameField.getText()));
//            textArea.append(String.format("%-18s %d days\n", "Duration:", diffInDays));
//
//
//            // --- 2. INTEGRATE JAMES'S MATH ---
//        TaxIncome taxCalc = new TaxIncome(); // Now works because you have the real file!
//        double grossPay = 0.0;
//
//        // Pull the pay data from your new fields
//        if (fullTimeBtn.isSelected()) {
//            grossPay = Double.parseDouble(salaryField.getText());
//        } else {
//            // Logic for hourly: Rate * 40 hours (standard)
//            grossPay = Double.parseDouble(rateField.getText()) * 40; 
//        }
//
//        // Use the methods James finished
//        double fedTax = taxCalc.compFederalTax(grossPay);
//        double stateTax = taxCalc.compStateTax(grossPay);
//        double totalTax = taxCalc.compIncomeTax(grossPay); // Calculates Fed + State
//        double netPay = grossPay - totalTax;
//
//        // --- 3. DISPLAY RESULTS ---
//        textArea.append(String.format("\n%-18s %s", "Employee Status:", (fullTimeBtn.isSelected() ? "Full Time" : "Hourly")));
//        textArea.append(String.format("\n%-18s $ %10.2f", "Gross Pay:", grossPay));
//        textArea.append(String.format("\n%-18s $ %10.2f", "Federal Tax:", fedTax));
//        textArea.append(String.format("\n%-18s $ %10.2f", "State Tax:", stateTax));
//        textArea.append(String.format("\n%-18s $ %10.2f", "Total Tax:", totalTax));
//        textArea.append("\n----------------------------------");
//        textArea.append(String.format("\n%-18s $ %10.2f\n", "Net Pay:", netPay));
//
//		} catch (Exception ex) {
//			textArea.append("ERROR: Check your inputs. Ensure dates are MM/dd/yyyy and pay fields are numeric.\n");
//		}
//	}
	  
    void updateTextarea(){
        // Logic for refreshing display goes here
    }

    void close(){
        System.exit(0);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Pay Roll");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new UserGUI());
        f.pack();
        f.setVisible(true);
    }
}
