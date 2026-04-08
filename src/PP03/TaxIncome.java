package PP03;

public class TaxIncome implements Taxable{

	@Override
	public double compStateTax(double grossPay) {
		// TODO Auto-generated method stub
		return grossPay * STATE_TAX;
	}

	@Override
	public double compFederalTax(double grossPay) {
		// TODO Auto-generated method stub
		return grossPay * FEDERAL_TAX;
	}

	@Override
	public double compIncomeTax(double grossPay) {
		// TODO Auto-generated method stub
		return compStateTax(grossPay) + compFederalTax(grossPay);
	}

	// 1- this class implements the Taxable interface
	// 2- implement all the unimplemented abstract methods in the Taxable Interface, income tax is computed based on state and federal taxes   
	

}

