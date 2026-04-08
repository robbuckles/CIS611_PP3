package PP03;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserGUI extends JPanel {
	
	 private JLabel label1;
	  private JLabel label2;
	  private JTextField field1;
	  private JTextField field2;
	  private JButton TransferButton;
	  private JButton CloseButton;
	  private JTextArea textArea;
	  private JComboBox combList;
	  private JScrollPane jp;
	  private PayRoll payRoll;
	  private String fileName = "PayRoll.txt";
	  
	  public UserGUI() {
		  
                 // prompt the user to input the number of pay records
                 int n; // is the number of pay records for employees
		 payRoll = new PayRoll(fileName,n);

	    initGUI();
	    doTheLayout();

	    TransferButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            transfer();
	            }
	    });
	    
	    CloseButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            close();
	            }
	    });
	    
	    combList.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            //transfer_actionPerformed(e);
	        	updateTextarea();
	            
	            }
	    });

	  } // end of constructor

	  private void initGUI(){
	      
		  label1 = new JLabel("Type some text here:");
	      label2 = new JLabel("Your text will appear here:");
	      field1 = new JTextField("This is the default text", 30);
	      field2 = new JTextField(30);
	      textArea = new JTextArea(5, 15);
	      textArea.setEditable(false);
	      textArea.setLineWrap(true);
	      textArea.setWrapStyleWord(true);
	      
	      // testing
	      textArea.append("This is an editable JTextArea. " +
	    		    "A text area is a \"plain\" text component, " +
	    		    "which means that although it can display text " +
	    		    "in any font, all of the text is in the same font.");
	      
	      jp = new JScrollPane(textArea);
	      
	      String[] listStrings = { "","Laptops", "Tablets", "Cell Phones" };
	      combList = new JComboBox(listStrings);
	      
	      TransferButton = new JButton("Transfer");
	      CloseButton = new JButton("Close");
	      
	      field2.setEditable(false);

	  }// end of creating objects method

	  private void doTheLayout(){

	      JPanel top = new JPanel();
	      JPanel center = new JPanel();
	      JPanel bottom = new JPanel();

	      top.setLayout( new FlowLayout());
	      top.add(label1);
	      top.add(field1);
	      top.add(combList);

	      center.setPreferredSize( new Dimension( 10, 155 ) );
	      center.setLayout( new GridLayout(2,2));
	      center.add(label2);
	      center.add(field2);
	      center.add(new JLabel(""));
	      center.add(jp);

	      bottom.setLayout( new FlowLayout());
	      bottom.add(TransferButton);
	      bottom.add(CloseButton);

	      setLayout( new BorderLayout());
	      add(top, "North");
	      add(center, "Center");
	      add(bottom, "South");

	  }// end of Layout method

	 
	  void transfer(){
	        String mytext = field1.getText();
	        field2.setText(mytext);
	  }// end of transfer action event method
	  
	  void updateTextarea(){
		  
		 		 
		  if(combList.getSelectedItem().toString().compareToIgnoreCase("") == 0)
			  textArea.append("This is an editable JTextArea. " +
		    		    "A text area is a \"plain\" text component, " +
		    		    "which means that although it can display text " +
		    		    "in any font, all of the text is in the same font.");
			 else if (combList.getSelectedItem().toString().compareToIgnoreCase( "Laptops") == 0)
				 textArea.setText("You have selected Laptops item name");
			 else if (combList.getSelectedItem().toString().compareToIgnoreCase("Tablets") == 0)
				 textArea.setText("You have not selected Tablets item name");
			 else if (combList.getSelectedItem().toString().compareToIgnoreCase("Cell Phones") == 0)
				 textArea.setText("You have not selected Cell Phones item name");
		  
	  }

	  void close(){
	      System.exit(0);
	  }// end of transfer action event method


	public static void main(String[] args) {
	   JFrame f = new JFrame("Pay Roll");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();
        contentPane.add( new UserGUI());
        f.pack();
        //setSize(200, 200);
        f.setVisible(true);


	}

}
