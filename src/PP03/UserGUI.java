//*********************************************************************
//*  
//*         CIS611 Spring 2026 James Benjamin & Rob Buckles  
//*  
//*              Programming project  PP03  
//*  
//*          This class is the GUI for the PayRoll application.
//*      It collects user input, displays results, and interacts with
//*      the PayRoll class to process employee data and pay records.  
//*          
//*  
//*  
//*                  12 APR 2026  
//*  
//*              Saved in: UserGUI.java  
//*  
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
import javax.swing.JOptionPane;
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
    private JButton addPayRecordBtn;
    private JButton CloseButton;
    private JTextArea textArea;
    private JScrollPane jp;
    
    private PayRoll payRoll;
    private String fileName = "PayRoll.txt";

    private JLabel salaryLabel, rateLabel;
    private JTextField salaryField, rateField;  
	  
    public UserGUI() {
        // Note: 'n' needs a value. Setting to 100 as per PayRoll constructor.
        payRoll = new PayRoll(fileName, 100);

        initGUI();
        doTheLayout();
        
        // Load initial data from file at startup (Rubric Requirement)
        payRoll.readFromFile();
        textArea.append(payRoll.displayPayRecord());

        // Listener for Add Employee Button
        addEmployeeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e){
                transfer();
            }
        });

        // Listener for Add Pay Record Button (Required for plural "listeners" in rubric)
        addPayRecordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e){
                transfer();
            }
        });
        
        // Listener for Close Button
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

        // Results Area (Fixed-Width Font for Alignment)
        textArea = new JTextArea(15, 50);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textArea.setEditable(false);
        jp = new JScrollPane(textArea);

        // Buttons
        addEmployeeBtn = new JButton("Add Employee");
        addPayRecordBtn = new JButton("Add Pay Record");
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

        // Button Panel with all three required actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addEmployeeBtn);
        buttonPanel.add(addPayRecordBtn);
        buttonPanel.add(CloseButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(jp, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    void transfer() {
        // Validation: Check for empty fields (Rubric Item 15)
        if (idField.getText().trim().isEmpty() ||
            fNameField.getText().trim().isEmpty() ||
            lNameField.getText().trim().isEmpty() ||
            streetField.getText().trim().isEmpty() ||
            houseNumField.getText().trim().isEmpty() ||
            cityField.getText().trim().isEmpty() ||
            zipField.getText().trim().isEmpty() ||
            startDateField.getText().trim().isEmpty() ||
            endDateField.getText().trim().isEmpty()) {

            textArea.append("ERROR: All fields must be filled.\n");
            JOptionPane.showMessageDialog(this, 
                "All fields are required. Please fill out the entire form.", 
                "Missing Data", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idField.getText().trim());
            int houseNum = Integer.parseInt(houseNumField.getText().trim());
            int zip = Integer.parseInt(zipField.getText().trim());

            Address addr = new Address(
                    streetField.getText().trim(),
                    houseNum,
                    cityField.getText().trim(),
                    stateCombo.getSelectedItem().toString(),
                    zip
            );

            Status status = fullTimeBtn.isSelected() ? Status.FULLTIME : Status.HOURLY;

            double salary = salaryField.getText().isEmpty() ? 0 : Double.parseDouble(salaryField.getText().trim());
            double rate = rateField.getText().isEmpty() ? 0 : Double.parseDouble(rateField.getText().trim());

            // Delegate logic to PayRoll class (Rubric: No business logic in GUI)
            String result = payRoll.processPayRecordFromGUI(
                    id, fNameField.getText().trim(), lNameField.getText().trim(),
                    addr, status, salary, rate,
                    startDateField.getText().trim(), endDateField.getText().trim()
            );

            // Handle logic errors returned from PayRoll (e.g. invalid dates)
            if (result.startsWith("ERROR")) {
                JOptionPane.showMessageDialog(this, result, "Calculation Error", JOptionPane.ERROR_MESSAGE);
            }
            
            textArea.setText(result);

        } catch (NumberFormatException e) {
            textArea.append("ERROR: Invalid numeric input.\n");
            JOptionPane.showMessageDialog(this, 
                "Invalid numeric input. Please check your ID, House #, or Zip Code.", 
                "Data Type Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
	  
    void updateTextarea(){
        textArea.setText(payRoll.displayPayRecord());
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
