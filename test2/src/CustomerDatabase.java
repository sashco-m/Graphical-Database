import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.Color;
import java.util.*; //tokenizer
import java.io.*; //file reading and writing
import javax.swing.DefaultListModel; //allows us to update lists

public class CustomerDatabase
{
    private CustomerRecord[] cr;
    private CustomerRecord[] tempArray; //need this array to be accessible on the sortCustomer panel
    private int recordsFilled;

    public CustomerDatabase (String fileLocation)
    {
	cr = new CustomerRecord [1000];
	try
	{
	    BufferedReader br = new BufferedReader (new FileReader (fileLocation));
	    StringTokenizer st;
	    String record = br.readLine ();
	    recordsFilled = 0;

	    while (record != null)
	    {
		st = new StringTokenizer (record, "/");
		cr [recordsFilled] = new CustomerRecord (st.nextToken (), st.nextToken (), st.nextToken (), st.nextToken (), st.nextToken (), st.nextToken ());
		record = br.readLine ();
		recordsFilled++;
	    }
	    br.close ();

	}
	catch (IOException e)
	{
	    JTextArea fileNotFound = new JTextArea ();
	    fileNotFound.setText ("File cannot be found! Make sure to name the\naccounts file \"accounts.txt\", and have it in \nthe same folder as this program");
	    fileNotFound.setEditable (false);
	    fileNotFound.setBackground (Color.white);
	    JOptionPane.showMessageDialog (null, fileNotFound, "File not found!", JOptionPane.WARNING_MESSAGE);
	    System.exit (0);
	}


    }


    public DefaultListModel sortCustomer (int sortType)  //returns an updated list model
    {

	tempArray = new CustomerRecord [recordsFilled];

	DefaultListModel list = new DefaultListModel ();

	for (int i = 0 ; i < recordsFilled ; i++) //making the temp array the same as the current array
	{
	    tempArray [i] = cr [i];
	}

	if (sortType == 0) //sort by age
	{
	    sortCustomerAge (tempArray, 0, recordsFilled - 1);
	}
	else if (sortType == 1) //sort by income
	{
	    sortCustomerIncome (tempArray, 0, recordsFilled - 1);
	}

	for (int i = 0 ; i < tempArray.length ; i++)
	{
	    list.addElement (tempArray [i]);
	}

	return list;



    }


    public void sortCustomerAge (CustomerRecord[] tempArray, int low, int high)
    {

	int left, right;
	CustomerRecord pivot; //for used to compare age
	if (low < high)
	{
	    pivot = tempArray [low];
	    left = low;
	    right = high;
	    while (left < right)
	    {
		while (Integer.parseInt (tempArray [right].getAge ()) >= Integer.parseInt (pivot.getAge ()) && left < right)
		    right--;

		tempArray [left] = tempArray [right];
		while (Integer.parseInt (tempArray [left].getAge ()) <= Integer.parseInt (pivot.getAge ()) && left < right)
		    left++;
		tempArray [right] = tempArray [left];
	    }
	    tempArray [left] = pivot;
	    sortCustomerAge (tempArray, low, left - 1);
	    sortCustomerAge (tempArray, right + 1, high);
	}
    }


    public void sortCustomerIncome (CustomerRecord[] tempArray, int low, int high)
    {

	int left, right;
	CustomerRecord pivot; //for used to compare income
	if (low < high)
	{
	    pivot = tempArray [low];
	    left = low;
	    right = high;
	    while (left < right)
	    {
		while (Double.parseDouble (tempArray [right].getIncome ()) >= Double.parseDouble (pivot.getIncome ()) && left < right)
		    right--;

		tempArray [left] = tempArray [right];
		while (Double.parseDouble (tempArray [left].getIncome ()) <= Double.parseDouble (pivot.getIncome ()) && left < right)
		    left++;
		tempArray [right] = tempArray [left];
	    }
	    tempArray [left] = pivot;
	    sortCustomerIncome (tempArray, low, left - 1);
	    sortCustomerIncome (tempArray, right + 1, high);

	}
    }



    public void exit ()
    {
	try
	{
	    PrintWriter pw = new PrintWriter (new FileWriter ("accounts.txt"));
	    for (int i = 0 ; i < recordsFilled ; i++)
	    {
		pw.println (cr [i].getFirstName () + "/" + cr [i].getLastName () + "/" + cr [i].getAddress () + "/" + cr [i].getTelephone () + "/" + cr [i].getAge () + "/" + cr [i].getIncome ()); // 65;//test;
	    }

	    pw.close ();
	}


	catch (IOException io)
	{
	    //file not found
	    //already error trapped for during the constructor
	}

    }


    public int deleteCustomer (String fN, String lN)
    {
	int index = searchCustomer (fN, lN, 0, recordsFilled - 1);
	if (index == -1)
	{
	    //all display happens in agency.java
	}
	else if (index == -2) //automatically recommends first names if last name is spelt correctly
	{
	    int[] nameIndex = new int [recordsFilled];
	    int counter = 0;
	    for (int i = 0 ; i < recordsFilled ; i++)
	    {
		if (cr [i].getLastName ().equalsIgnoreCase (lN))
		{
		    nameIndex [counter] = i;
		    counter++;
		}
	    }
	    String[] nameOptions = new String [counter + 1];

	    for (int j = 0 ; j < counter ; j++)
	    {
		nameOptions [j] = cr [nameIndex [j]].getFirstName ();
	    }
	    nameOptions [counter] = "exit";

	    int suggested = ReadLib.getOptions ("First name may be mispelled. Did you mean...", nameOptions);

	    if (suggested == counter)
	    {
		return -3; //user exited this option(do nothing)
	    }
	    else
	    {
		index = nameIndex [suggested];
		while (index + 1 < recordsFilled)
		{
		    cr [index] = cr [index + 1];
		    index++;
		}
		//have a cool graphic
		recordsFilled--;
	    }
	}
	else //if a proper index was entered
	{
	    while (index + 1 < recordsFilled)
	    {
		cr [index] = cr [index + 1];
		index++;
	    }
	    //have a cool graphic
	    recordsFilled--;
	}




	return index;
    }


    public void insertCustomer (String fN, String lN, String address, String telephone, String age, String income)
    {

	CustomerRecord insertC = new CustomerRecord (fN, lN, address, telephone, age, income);
	insertCustomer (0, recordsFilled - 1, insertC);

    }

    public void insertCustomer (int first, int last, CustomerRecord insertC)
    {

	int middle = (first + last) / 2;
	//this block good
	if (first > last) //this base case occurs when the name goes at the very beginning or end of the array
	{
	    if (recordsFilled == 0 || insertC.getLastName ().compareToIgnoreCase (cr [0].getLastName ()) < 0) //this must mean the beginning //updated to work if the array is empty
	    {
		for (int i = 0 ; i < recordsFilled ; i++) //move every element in the array over
		{
		    cr [recordsFilled - i] = cr [recordsFilled - 1 - i];
		}
		cr [0] = insertC;
		recordsFilled++;
	    }
	    else //if youre not first youre last (good life advice)
	    {
		cr [recordsFilled] = insertC;
		recordsFilled++;
	    }
	}


	//this monstrosity of a base case checks the last name to the right of it, to determine if it should go in between
	else if (middle < recordsFilled - 1 && insertC.getLastName ().compareToIgnoreCase (cr [middle].getLastName ()) > 0 && insertC.getLastName ().compareToIgnoreCase (cr [middle + 1].getLastName ()) < 0)
	{
	    //the first stipulation in the base case is to prevent it from going out of bounds of the array

	    int i = 0;
	    while (middle + 1 + i < recordsFilled) //if this case is true, move every index to the right starting from the adjacent index
	    {
		cr [recordsFilled - i] = cr [recordsFilled - 1 - i];

		i++;
	    }

	    cr [middle + 1] = insertC;
	    recordsFilled++;

	}


	else if (cr [middle].getLastName ().equalsIgnoreCase (insertC.getLastName ())) //this is for the case if they are the same
	{
	    int i = 0;
	    while (middle + i < recordsFilled) //inserts it in the place of the other one
	    {
		cr [recordsFilled - i] = cr [recordsFilled - 1 - i];

		i++;
	    }

	    cr [middle] = insertC;
	    recordsFilled++;


	}


	//check either side of middle num
	//recurses using the first half of the array(notice it is the same code in the other base case)
	else if (insertC.getLastName ().compareToIgnoreCase (cr [middle].getLastName ()) < 0)
	{
	    insertCustomer (first, middle - 1, insertC);

	}


	else //recurses using the second half
	{
	    insertCustomer (middle + 1, last, insertC);

	}
    }


    public int searchCustomer (String fN, String lN)
    {
	int index = searchCustomer (fN, lN, 0, recordsFilled - 1);

	if (index >= 0)
	{

	}
	else if (index == -1)
	{
	    //testing purposes only
	}


	else //possible suggesting names feature??
	{
	    int[] nameIndex = new int [recordsFilled];
	    int counter = 0;
	    for (int i = 0 ; i < recordsFilled ; i++)
	    {
		if (cr [i].getLastName ().equalsIgnoreCase (lN))
		{
		    nameIndex [counter] = i;
		    counter++;
		}
	    }
	    String[] nameOptions = new String [counter + 1];

	    for (int j = 0 ; j < counter ; j++)
	    {
		nameOptions [j] = cr [nameIndex [j]].getFirstName ();
	    }
	    nameOptions [counter] = "exit";

	    int suggested = ReadLib.getOptions ("First name may be mispelled. Did you mean...", nameOptions);

	    if (suggested == counter)
	    {
		//they pressed exit, dont do anything
	    }
	    else
	    {
		index = nameIndex [suggested];
	    }
	}



	return index;
    }

    public int searchCustomer (String fN, String lN, int first, int last)
    {
	//this code is more or less rock solid
	//request other pieces of data if unable to determine from first and last name

	int middle = (first + last) / 2;


	if (first > last)
	{
	    return -1; //no harkers found
	}


	else if (cr [middle].getLastName ().equalsIgnoreCase (lN))
	{

	    int i = 1;

	    while (i <= middle && (cr [middle - i].getLastName ()).equalsIgnoreCase (lN))
	    {
		if ((cr [middle - i].getFirstName ()).equalsIgnoreCase (fN))
		    return middle - i;
		i++;
	    }
	    i = 0;
	    while (i <= last - middle && (cr [middle + i].getLastName ()).equalsIgnoreCase (lN))
	    {
		if ((cr [middle + i].getFirstName ()).equalsIgnoreCase (fN))
		    return middle + i;
		i++;
	    }
	    return -2; //if there are harkers but no philip
	}


	//check either side of middle num


	else if ((cr [middle].getLastName ()).compareToIgnoreCase (lN) > 0)
	{
	    return searchCustomer (fN, lN, first, middle - 1);
	}


	else
	{
	    return searchCustomer (fN, lN, middle + 1, last);
	}
    }


    //end of test

    public DefaultListModel displayRecord ()
    {
	CustomerRecord[] filledRecords = new CustomerRecord [recordsFilled];
	DefaultListModel d = new DefaultListModel ();
	JList list;

	for (int i = 0 ; i < recordsFilled ; i++)
	{
	    filledRecords [i] = cr [i];
	    d.addElement (cr [i]);
	}

	return d;
    }


    public CustomerRecord getCustomer (int index)
    {
	return cr [index];
    }


    public CustomerRecord getCustomerTemp (int index)
    {
	return tempArray [index];
    }


    public int getRecordsFilled ()
    {
	return recordsFilled;
    }
}


