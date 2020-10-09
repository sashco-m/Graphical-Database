import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.border.Border;
import java.awt.image.BufferedImage; //allows us to resize our logo

public class Agency implements ActionListener
{
    //display components (all of these components are updated, so they must be declared here)
    private JLabel firstName4, lastName4, age4, income4, address4, telephone4;
    private JLabel firstName5, lastName5, address5, age5, telephone5, income5, sortTitle;
    private JLabel displayHeader;

    //sort components
    private JButton incomeSort, ageSort, returnToMain6;
    private JScrollPane sortingPane;
    private JList sortingList;
    private JLabel sortedName, sortedVal;
    //other panel components
    private JButton search, sort, insert, delete, display, returnToMain, confirmSearch,
	confirmInsert, returnToMain2, returnToMain3, returnToMain4, returnToMain5,
	exitYes, exitNo, confirmDelete;
    private JTextField firstName, lastName, firstName2, lastName2, age, income, address,
	telephone, firstName3, lastName3;
    private JLabel insertSuccess, title, titleInsert, deleteSuccess, deleteFail, ageSearch, incomeSearch,
	addressSearch, telephoneSearch, searchFail;
    private JPanel cards; //the main JPanel
    private CardLayout c; //our card layout
    private CustomerDatabase cd;
    private JList customers; //JList for display
    private JScrollPane customerList; //scrollpane for display
    private JFrame f;
    private DefaultListModel modelDisplay, modelSort; //list models for display and sort
    private ListListener sortlistlistener, listlistener; //list listeners for display and sort
    
    //awt components
    private Color buttonColor = new Color(0, 140, 250);
    private Font titleFont = new Font("Verdana", Font.BOLD, 30);
    private Font subTitle = new Font("Verdana", Font.PLAIN, 20);
    
    public Agency (JPanel cards, CustomerDatabase cd, JFrame f)
    {
	this.f = f;
	//testing moving stuff over
	JPanel cardMain = new JPanel ();
	JPanel cardSearch = new JPanel ();
	JPanel cardInsert = new JPanel ();
	JPanel cardExit = new JPanel ();
	JPanel cardDelete = new JPanel ();
	JPanel cardDisplay = new JPanel ();
	JPanel cardSort = new JPanel ();

	//creating display panel components
	returnToMain5 = new JButton ("Main Menu");
	returnToMain5.addActionListener (this);
	returnToMain5.setBackground(Color.red);
	firstName4 = new JLabel ();
	lastName4 = new JLabel ();
	age4 = new JLabel ();
	address4 = new JLabel ();
	telephone4 = new JLabel ();
	income4 = new JLabel ();
	displayHeader = new JLabel("Showing All Customers");
	modelDisplay = new DefaultListModel ();
	modelDisplay = cd.displayRecord ();
	//
	cardDisplay.setLayout (null);
	cardDisplay.setBackground (Color.cyan);
	//list
	customers = new JList (modelDisplay);
	//
	listlistener = new ListListener (firstName4, lastName4, address4, age4, telephone4, income4, customers, cd, 0);
	customers.addListSelectionListener (listlistener);
	//listmodel test

	customerList = new JScrollPane (customers);

	//setting bounds for display panel components
	customerList.setBounds (50, 100, 200, 300);
	firstName4.setBounds (300, 100, 200, 50);
	lastName4.setBounds (300, 150, 200, 50);
	address4.setBounds (300, 200, 200, 50);
	age4.setBounds (300, 250, 200, 50);
	telephone4.setBounds (300, 300, 200, 50);
	income4.setBounds (300, 350, 200, 50);
	returnToMain5.setBounds (50, 500, 100, 25);

	//adding components to display panel
	cardDisplay.add (customerList);
	cardDisplay.add (firstName4);
	cardDisplay.add (lastName4);
	cardDisplay.add (address4);
	cardDisplay.add (age4);
	cardDisplay.add (telephone4);
	cardDisplay.add (income4);
	cardDisplay.add (returnToMain5);

	//creating sort panel components
	sortTitle = new JLabel ("Sorting by Income");
	cardSort.setLayout (null);
	cardSort.setBackground (Color.cyan);
	modelSort = new DefaultListModel ();
	modelSort = cd.displayRecord ();
	sortingList = new JList (modelSort);
	sortingPane = new JScrollPane (sortingList);
	//listener
	firstName5 = new JLabel ();
	lastName5 = new JLabel ();
	age5 = new JLabel ();
	address5 = new JLabel ();
	telephone5 = new JLabel ();
	income5 = new JLabel ();
	sortlistlistener = new ListListener (firstName5, lastName5, address5, age5, telephone5, income5, sortingList, cd, 1);
	sortingList.addListSelectionListener (sortlistlistener);
	returnToMain6 = new JButton ("Main Menu");
	incomeSort = new JButton ("Income Sort");
	ageSort = new JButton ("Age Sort");

	//setting bounds for sort panel components
	sortTitle.setBounds (130, 30, 500, 100);
	sortTitle.setFont (titleFont);
	sortingPane.setBounds (50, 150, 200, 300);
	returnToMain6.setBounds (50, 500, 100, 25);
	returnToMain6.setBackground(Color.red);
	incomeSort.setBounds (275, 350, 150, 100);
	incomeSort.setBackground(buttonColor);
	ageSort.setBounds (425, 350, 150, 100);
	ageSort.setBackground(buttonColor);

	firstName5.setBounds (300, 150, 200, 20);
	lastName5.setBounds (300, 180, 200, 20);
	address5.setBounds (300, 210, 200, 20);
	age5.setBounds (300, 250, 200, 20);
	telephone5.setBounds (300, 280, 200, 20);
	income5.setBounds (300, 310, 200, 20);

	//adding sort panel components
	cardSort.add (sortingPane);
	cardSort.add (returnToMain6);
	cardSort.add (incomeSort);
	cardSort.add (ageSort);
	cardSort.add (sortTitle);
	cardSort.add (firstName5);
	cardSort.add (lastName5);
	cardSort.add (age5);
	cardSort.add (address5);
	cardSort.add (telephone5);
	cardSort.add (income5);

	//delete panel components
	confirmDelete = new JButton ("Delete");
	returnToMain4 = new JButton ("Main Menu");
	lastName3 = new JTextField ();
	firstName3 = new JTextField ();
	JLabel firstNameDelete = new JLabel ("First Name");
	JLabel lastNameDelete = new JLabel ("Last Name");
	JLabel deleteTitle = new JLabel ("Delete a Customer Record");
	deleteSuccess = new JLabel ("Customer Record Deleted Successfully");
	deleteFail = new JLabel ("Customer Record Not Found");
	//bounds for delete
	deleteTitle.setBounds (100, 30, 500, 200);
	returnToMain4.setBounds (50, 50, 100, 25);
	returnToMain4.setBackground(Color.red);
	lastName3.setBounds (250, 250, 100, 20);
	firstName3.setBounds (250, 200, 100, 20);
	firstNameDelete.setBounds (140, 200, 100, 20);
	firstNameDelete.setHorizontalAlignment (SwingConstants.CENTER);
	lastNameDelete.setBounds (140, 250, 100, 20);
	lastNameDelete.setHorizontalAlignment (SwingConstants.CENTER);
	confirmDelete.setBounds (400, 400, 100, 25);
	confirmDelete.setBackground(buttonColor);
	//exit panel buttons
	exitYes = new JButton ("Yes");
	exitNo = new JButton ("No");
	exitYes.setBackground(Color.red);
	exitNo.setBackground(Color.green);
	JLabel exitTitle = new JLabel ("Exit. Are you sure?");
	JLabel exitSubTitle = new JLabel ("Exiting will save automatically");
	//bounds for exit
	exitTitle.setFont (titleFont);
	exitTitle.setBounds (150, 100, 600, 200);
	exitSubTitle.setFont(subTitle);
	exitSubTitle.setBounds(150, 150, 600, 175);
	exitYes.setBounds (195, 300, 100, 50);
	exitNo.setBounds (295, 300, 100, 50);
	//buttons for insert panel
	confirmInsert = new JButton ("Confirm");
	age = new JTextField ();
	income = new JTextField ();
	address = new JTextField ();
	telephone = new JTextField ();
	firstName2 = new JTextField ();
	lastName2 = new JTextField ();
	insertSuccess = new JLabel ("Record Inserted Successfully!");
	titleInsert = new JLabel ("Insert a Customer Record");
	JLabel firstNameInsert = new JLabel ("First Name");
	JLabel lastNameInsert = new JLabel ("Last Name");
	JLabel ageInsert = new JLabel ("Age");
	JLabel incomeInsert = new JLabel ("Income");
	JLabel addressInsert = new JLabel ("Address");
	JLabel telephoneInsert = new JLabel ("Telephone");

	//bounds for insert
	firstName2.setBounds (250, 200, 100, 20);
	firstNameInsert.setBounds (140, 200, 100, 20);
	firstNameInsert.setHorizontalAlignment (SwingConstants.CENTER);
	lastName2.setBounds (250, 250, 100, 20);
	lastNameInsert.setBounds (140, 250, 100, 20);
	lastNameInsert.setHorizontalAlignment (SwingConstants.CENTER);
	age.setBounds (250, 300, 100, 20);
	ageInsert.setBounds (140, 300, 100, 20);
	ageInsert.setHorizontalAlignment (SwingConstants.CENTER);
	address.setBounds (250, 350, 100, 20);
	addressInsert.setBounds (140, 350, 100, 20);
	addressInsert.setHorizontalAlignment (SwingConstants.CENTER);
	telephone.setBounds (250, 400, 100, 20);
	telephoneInsert.setBounds (140, 400, 100, 20);
	telephoneInsert.setHorizontalAlignment (SwingConstants.CENTER);
	income.setBounds (250, 450, 100, 20);
	incomeInsert.setBounds (140, 450, 100, 20);
	incomeInsert.setHorizontalAlignment (SwingConstants.CENTER);
	confirmInsert.setBounds (400, 450, 100, 25);
	confirmInsert.setBackground(buttonColor);
	titleInsert.setBounds (100, 30, 500, 200);

	//buttons for main panel
	search = new CornerButton (CornerButton.ORIENT_NORTH_WEST);
	ImageIcon s = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\search.png"); //adding the default image
	ImageIcon sHover = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\searchHover.png");//adding the hover image
	MouseClass m1 = new MouseClass (search, s, sHover); //using a mouseListener to switch between hover and default images
	search.setIcon (s);
	insert = new CornerButton (CornerButton.ORIENT_NORTH_EAST);
	ImageIcon i = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\insert.png");
	ImageIcon iHover = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\insertHover.png");
	MouseClass m2 = new MouseClass (insert, i, iHover);
	insert.setIcon (i);
	delete = new CornerButton (CornerButton.ORIENT_SOUTH_WEST);
	ImageIcon d = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\delete.png");
	ImageIcon dHover = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\deleteHover.png");
	MouseClass m3 = new MouseClass (delete, d, dHover);
	delete.setIcon (d);
	sort = new CornerButton (CornerButton.ORIENT_SOUTH_EAST);
	ImageIcon so = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\sort.png");
	ImageIcon soHover = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\sortHover.png");
	MouseClass m4 = new MouseClass (sort, so, soHover);
	sort.setIcon (so);
	display = new JButton ();
	ImageIcon di = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\center.png");
	ImageIcon diHover = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\centerHover.png");
	MouseClass m5 = new MouseClass (display, di, diHover);
	//insert the hover for center
	display.setIcon (di);
	title = new JLabel ();
	//all panels
	returnToMain = new JButton ("Main Menu");
	returnToMain.setBounds (50, 50, 100, 25);
	returnToMain.setBackground(Color.red);
	returnToMain2 = new JButton ("Main Menu");
	returnToMain2.setBounds (50, 50, 100, 25);
	returnToMain2.setBackground(Color.red);
	//search panel swing components
	firstName = new JTextField ();
	lastName = new JTextField ();
	confirmSearch = new JButton ("Confirm");
	JLabel firstNameSearch = new JLabel ("First Name");
	JLabel lastNameSearch = new JLabel ("Last Name");
	ageSearch = new JLabel ();
	JLabel ageSearchTitle = new JLabel ("Age");
	incomeSearch = new JLabel ();
	JLabel incomeSearchTitle = new JLabel ("Income");
	addressSearch = new JLabel ();
	JLabel addressSearchTitle = new JLabel ("Address");
	telephoneSearch = new JLabel ();
	JLabel telephoneSearchTitle = new JLabel ("Telephone");
	searchFail = new JLabel ("Record Not Found");
	JLabel searchPanelTitle = new JLabel ("Search for a Customer");
	//bounds for main panel
	search.setBounds (195, 250, 100, 100);
	insert.setBounds (305, 250, 100, 100);
	delete.setBounds (195, 360, 100, 100);
	sort.setBounds (305, 360, 100, 100);
	display.setBounds (240, 295, 120, 120);
	title.setBounds (-40, 10, 700, 400); //change these
	//adding to Main
	cardMain.add (search);
	cardMain.add (insert);
	cardMain.add (delete);
	cardMain.add (sort);
	cardMain.add (display);
	//setbounds for search
	firstName.setBounds (250, 200, 150, 20);
	firstNameSearch.setBounds (150, 200, 150, 20);
	lastName.setBounds (250, 250, 150, 20);
	lastNameSearch.setBounds (150, 250, 150, 20);
	ageSearch.setBounds (250, 300, 150, 20);
	ageSearchTitle.setBounds (150, 300, 150, 20);
	incomeSearch.setBounds (250, 350, 150, 20);
	incomeSearchTitle.setBounds (150, 350, 150, 20);
	addressSearch.setBounds (250, 400, 150, 20);
	addressSearchTitle.setBounds (150, 400, 150, 20);
	telephoneSearch.setBounds (250, 450, 150, 20);
	telephoneSearchTitle.setBounds (150, 450, 150, 20);
	confirmSearch.setBounds (450, 450, 100, 25);
	confirmSearch.setBackground (buttonColor);
	searchPanelTitle.setBounds (100, 30, 500, 200);
	searchPanelTitle.setFont (titleFont);
	//adding to search
	cardSearch.add (firstName);
	cardSearch.add (lastName);
	cardSearch.add (returnToMain);
	cardSearch.add (confirmSearch);
	cardSearch.add (firstNameSearch);
	cardSearch.add (lastNameSearch);
	cardSearch.add (ageSearch);
	cardSearch.add (ageSearchTitle);
	cardSearch.add (incomeSearch);
	cardSearch.add (incomeSearchTitle);
	cardSearch.add (addressSearch);
	cardSearch.add (addressSearchTitle);
	cardSearch.add (telephoneSearch);
	cardSearch.add (telephoneSearchTitle);
	cardSearch.add (searchFail);
	cardSearch.add (searchPanelTitle);
	//adding to insert
	cardInsert.add (firstName2);
	cardInsert.add (lastName2);
	cardInsert.add (age);
	cardInsert.add (income);
	cardInsert.add (address);
	cardInsert.add (telephone);
	//labels
	cardInsert.add (firstNameInsert);
	cardInsert.add (lastNameInsert);
	cardInsert.add (ageInsert);
	cardInsert.add (incomeInsert);
	cardInsert.add (addressInsert);
	cardInsert.add (telephoneInsert);
	cardInsert.add (confirmInsert);
	cardInsert.add (returnToMain2);
	cardInsert.add (insertSuccess);
	cardInsert.add (titleInsert);
	//exit
	cardExit.add (exitTitle);
	cardExit.add (exitSubTitle);
	cardExit.add (exitYes);
	cardExit.add (exitNo);
	//add to delete
	cardDelete.add (deleteTitle);
	cardDelete.add (firstName3);
	cardDelete.add (lastName3);
	cardDelete.add (firstNameDelete);
	cardDelete.add (lastNameDelete);
	cardDelete.add (confirmDelete);
	cardDelete.add (returnToMain4);
	cardDelete.add (deleteSuccess);
	cardDelete.add (deleteFail);
	//display main panel
	ImageIcon logo = new ImageIcon ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\logo.png");
	//this allows me to resize the image however I like
	Image img = logo.getImage ();
	BufferedImage bi = new BufferedImage (img.getWidth (null), img.getHeight (null), BufferedImage.TYPE_INT_ARGB);
	Graphics g = bi.createGraphics ();
	g.drawImage (img, 0, 0, 640, 360, null);
	ImageIcon newLogo = new ImageIcon (bi);
	title.setIcon (newLogo);
	cardMain.add (title);
	//exit
	cardExit.setLayout (null);
	cardExit.setBackground (Color.cyan);
	//main
	cardMain.setLayout (null);
	cardMain.setBackground (Color.cyan);
	titleInsert.setFont (titleFont);
	//display search panel
	cardSearch.setLayout (null);
	cardSearch.setBackground (Color.cyan);
	//display insert panel
	cardInsert.setLayout (null);
	cardInsert.setBackground (Color.cyan);
	//delete panel
	cardDelete.setLayout (null);
	cardDelete.setBackground (Color.cyan);
	deleteTitle.setFont (titleFont);

	//adding panels to the cardstack
	cards.add (cardMain, "Main");
	cards.add (cardSearch, "Search");
	cards.add (cardInsert, "Insert");
	cards.add (cardExit, "Exit");
	cards.add (cardDelete, "Delete");
	cards.add (cardDisplay, "Display");
	cards.add (cardSort, "Sort");

	this.cards = cards;
	this.cd = cd;
	//adding all action listeners
	search.addActionListener (this);
	returnToMain.addActionListener (this);
	confirmSearch.addActionListener (this);
	confirmInsert.addActionListener (this);
	insert.addActionListener (this);
	returnToMain2.addActionListener (this);
	exitYes.addActionListener (this);
	exitNo.addActionListener (this);
	confirmDelete.addActionListener (this);
	returnToMain4.addActionListener (this);
	delete.addActionListener (this);
	display.addActionListener (this);
	sort.addActionListener (this);
	returnToMain6.addActionListener (this);
	ageSort.addActionListener (this);
	incomeSort.addActionListener (this);

	c = (CardLayout) cards.getLayout ();
	CloseWindow x = new CloseWindow (f, cd, cards, c);
    }

    public void actionPerformed (ActionEvent e)
    {


	if (e.getSource () == search) //go to search
	{
	    c.show (cards, "Search");
	}
	else if (e.getSource () == sort)//go to sort
	{
	    c.show (cards, "Sort");
	    modelSort = cd.sortCustomer (1);
	    sortingList.setModel (modelSort);
	}
	else if (e.getSource () == display)//go to display
	{
	    c.show (cards, "Display");
	    
	    modelDisplay = cd.displayRecord ();
	    customers.removeListSelectionListener(listlistener);
	    customers.setModel (modelDisplay);
	    customers.addListSelectionListener(listlistener);
	    customers.setSelectedIndex(0);
	}
	else if (e.getSource () == insert)//go to insert
	{
	    c.show (cards, "Insert");
	}
	else if (e.getSource () == delete)//go to delete
	{
	    c.show (cards, "Delete");
	}
	else if (e.getSource () == returnToMain) //from search
	{

	    c.show (cards, "Main");
	    lastName.setText ("");
	    firstName.setText ("");
	    firstName.setBackground (Color.white);
	    lastName.setBackground (Color.white);
	    searchFail.setBounds (0, 0, 0, 0);
	    ageSearch.setText ("");
	    addressSearch.setText ("");
	    telephoneSearch.setText ("");
	    incomeSearch.setText ("");
	}

	else if (e.getSource () == returnToMain2) //from insert
	{

	    c.show (cards, "Main");
	    lastName2.setText ("");
	    firstName2.setText ("");
	    age.setText ("");
	    income.setText ("");
	    address.setText ("");
	    telephone.setText ("");
	    insertSuccess.setBounds (100, 130, 0, 0);
	    firstName2.setBackground (Color.white);
	    lastName2.setBackground (Color.white);
	    age.setBackground (Color.white);
	    income.setBackground (Color.white);
	    address.setBackground (Color.white);
	    telephone.setBackground (Color.white);

	}
	else if (e.getSource () == exitYes)
	{
	    cd.exit ();
	    System.exit (0); 
	}
	else if (e.getSource () == exitNo)//returns to main panel
	{
	    c.show (cards, "Main");
	}
	else if (e.getSource () == confirmDelete)
	{
	    int index;
	    firstName3.setBackground (Color.white);
	    lastName3.setBackground (Color.white);
	    deleteSuccess.setBounds (100, 130, 0, 0);
	    int error = 0;
	    NumberFormatException f = new NumberFormatException ();
	    try
	    {

		String fN, lN;
		fN = firstName3.getText ();
		if (fN.equals (""))
		    throw f;
		error = 1;
		lN = lastName3.getText ();
		if (lN.equals (""))
		    throw f;
		index = cd.deleteCustomer (fN, lN);
		if (index == -1)
		{
		    deleteFail.setBounds (100, 132, 300, 30);
		}
		else if (index == -3)
		{
		    deleteFail.setBounds (0, 0, 0, 0);
		    deleteSuccess.setBounds (0, 0, 0, 0);
		}
		else
		{
		deleteSuccess.setBounds (100, 132, 300, 30);
		deleteFail.setBounds (0, 0, 0, 0);
		}
		firstName3.setText ("");
		lastName3.setText ("");
	    }
	    catch (NumberFormatException n)
	    {
		if (error == 0)
		{
		    firstName3.setText ("");
		    firstName3.requestFocus ();
		    firstName3.setBackground (Color.red);
		}
		else
		{
		    lastName3.setText ("");
		    lastName3.requestFocus ();
		    lastName3.setBackground (Color.red);

		}
	    }
	}
	else if (e.getSource () == returnToMain4)
	{
	    firstName3.setText ("");
	    lastName3.setText ("");
	    deleteSuccess.setBounds (0, 0, 0, 0);
	    deleteFail.setBounds (0, 0, 0, 0);
	    c.show (cards, "Main");
	}
	else if (e.getSource () == returnToMain5)
	{
	    firstName4.setText ("");
	    lastName4.setText ("");
	    age4.setText ("");
	    address4.setText ("");
	    telephone4.setText ("");
	    income4.setText ("");
	    c.show (cards, "Main");
	}
	else if (e.getSource () == returnToMain6)
	{
	    c.show (cards, "Main");
	    sortTitle.setText ("Sorting by Income");
	    sortingList.removeListSelectionListener (sortlistlistener);
	    sortingList.setModel (cd.sortCustomer (1));
	    sortingList.addListSelectionListener (sortlistlistener);
	    sortingList.setSelectedIndex (0);

	}
	else if (e.getSource () == confirmSearch)
	{
	    firstName.setBackground (Color.white);
	    lastName.setBackground (Color.white);
	    int error = 0;
	    NumberFormatException f = new NumberFormatException ();
	    try
	    {

		String fN, lN;
		fN = firstName.getText ();
		if (fN.equals (""))
		    throw f;
		error = 1;
		lN = lastName.getText ();
		if (lN.equals (""))
		    throw f;
		int index = cd.searchCustomer (fN, lN);

		if (index == -1)
		{
		    searchFail.setBounds (100, 132, 300, 30);
		    ageSearch.setText ("");
		    addressSearch.setText ("");
		    telephoneSearch.setText ("");
		    incomeSearch.setText ("");
		}
		else
		{
		    CustomerRecord cr = cd.getCustomer (index);
		    ageSearch.setText (cr.getAge () + "");
		    incomeSearch.setText (cr.getIncome () + "");
		    telephoneSearch.setText (cr.getTelephone () + "");
		    addressSearch.setText (cr.getAddress () + "");
		    searchFail.setBounds (0, 0, 0, 0);
		}


	    }
	    catch (NumberFormatException n)
	    {
		if (error == 0)
		{
		    firstName.setText ("");
		    firstName.requestFocus ();
		    firstName.setBackground (Color.red);
		}
		else
		{
		    lastName.setText ("");
		    lastName.requestFocus ();
		    lastName.setBackground (Color.red);

		}
	    }
	}
	else if (e.getSource () == confirmInsert)
	{
	    insertSuccess.setBounds (100, 130, 0, 0);
	    firstName2.setBackground (Color.white);
	    lastName2.setBackground (Color.white);
	    age.setBackground (Color.white);
	    income.setBackground (Color.white);
	    address.setBackground (Color.white);
	    telephone.setBackground (Color.white);
	    int test;
	    int error = 0;
	    NumberFormatException f = new NumberFormatException ();
	    try
	    {

		String fN, lN, old, inc, add, tel;
		fN = firstName2.getText ();
		if (fN.equals (""))
		    throw f;
		error = 1;
		lN = lastName2.getText ();
		if (lN.equals (""))
		    throw f;
		error = 2;
		add = address.getText ();
		if (add.equals (""))
		    throw f;
		error = 3;
		tel = telephone.getText ();
		if (tel.equals (""))
		    throw f;
		error = 4;
		old = age.getText ();
		if (old.equals (""))
		    throw f;
		//exception is thrown if letters
		test = Integer.parseInt (old);
		//can only input numbers
		error = 5;
		inc = income.getText ();
		if (inc.equals (""))
		    throw f;
		//test if they inputted letters
		test = Integer.parseInt (inc);
		//exception is thrown and handled


		cd.insertCustomer (fN, lN, add, tel, old, inc);

		insertSuccess.setBounds (100, 132, 300, 30);

		firstName2.setText ("");
		lastName2.setText ("");
		age.setText ("");
		income.setText ("");
		address.setText ("");
		telephone.setText ("");
	    }
	    catch (NumberFormatException n)
	    {
		if (error == 0)
		{
		    firstName2.setText ("");
		    firstName2.requestFocus ();
		    firstName2.setBackground (Color.red);
		}
		else if (error == 1)
		{
		    lastName2.setText ("");
		    lastName2.requestFocus ();
		    lastName2.setBackground (Color.red);

		}
		else if (error == 2)
		{

		    address.setText ("");
		    address.requestFocus ();
		    address.setBackground (Color.red);

		}
		else if (error == 3)
		{

		    telephone.setText ("");
		    telephone.requestFocus ();
		    telephone.setBackground (Color.red);

		}
		else if (error == 4)
		{
		    age.setText ("");
		    age.requestFocus ();
		    age.setBackground (Color.red);
		}
		else
		{
		    income.setText ("");
		    income.requestFocus ();
		    income.setBackground (Color.red);

		}
	    }


	    ;
	}
	else if (e.getSource () == ageSort)
	{
	    sortTitle.setText ("Sorting by Age");
	    sortingList.removeListSelectionListener (sortlistlistener);
	    sortingList.setModel (cd.sortCustomer (0));
	    sortingList.addListSelectionListener (sortlistlistener);
	    sortingList.setSelectedIndex (0);

	}
	else if (e.getSource () == incomeSort)
	{
	    sortTitle.setText ("Sorting by Income");
	    sortingList.removeListSelectionListener (sortlistlistener);
	    sortingList.setModel (cd.sortCustomer (1));
	    sortingList.addListSelectionListener (sortlistlistener);
	    sortingList.setSelectedIndex (0);
	}
    }
}
