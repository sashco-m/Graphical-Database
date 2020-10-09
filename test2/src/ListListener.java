import javax.swing.*;
import javax.swing.event.*;

public class ListListener implements ListSelectionListener
{
    private JList list;
    private CustomerDatabase cd;
    private JLabel firstName, lastName, address, age, telephone, income;
    private DefaultListModel d;
    private int type;

    public ListListener (JLabel firstName, JLabel lastName, JLabel address, JLabel age, JLabel telephone, JLabel income, JList list, CustomerDatabase cd, int type)
    {
	this.list = list;
	this.cd = cd;

	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	this.age = age;
	this.telephone = telephone;
	this.income = income;
	this.type = type;

    }


    public void valueChanged (ListSelectionEvent e)
    {

	int index = list.getSelectedIndex ();


	try
	{
	   
	   if(cd.getRecordsFilled()>0)//if the array is empty
	   {
	    CustomerRecord cust = cd.getCustomer (index);
	    
	    if (type == 1)
	    {
		cust = cd.getCustomerTemp (index);
	    }
	    

	    firstName.setText ("First name: " + cust.getFirstName ());
	    lastName.setText ("Last name: " + cust.getLastName ());
	    address.setText ("Address: " + cust.getAddress ());
	    age.setText ("Age: " + cust.getAge ());
	    telephone.setText ("Phone Number: " + cust.getTelephone ());
	    income.setText ("Income: " + cust.getIncome ());
	    }
	}
	catch (ArrayIndexOutOfBoundsException a)
	{
	    
	}


    }
}
