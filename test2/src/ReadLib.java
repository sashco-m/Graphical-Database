import javax.swing.*;
public class ReadLib
{
    public static String readString (String prompt)
    {
	String test = JOptionPane.showInputDialog (prompt);
	if (test == null) //comparing two objects, use boolean comparison
	    System.exit (0);

	while (test.equals ("")) //comparing two strings
	{
	    JOptionPane.showMessageDialog (null, "Enter a value");
	    test = JOptionPane.showInputDialog (prompt);
	    if (test == null)
		System.exit (0);
	}


	return test;
    }


    public static int readInt (String prompt)
    {
	String test;
	int num = 0;
	boolean letter = true; //test if it is a letter
	while (letter)
	{
	    try
	    {
		test = readString (prompt); //can call the other method to do most of the code
		num = Integer.parseInt (test);
		letter = false;
	    }
	    catch (NullPointerException n)
	    {
		System.exit (0);
	    }
	    catch (NumberFormatException f)
	    {
		JOptionPane.showMessageDialog (null, "Invalid Input", "Input", JOptionPane.ERROR_MESSAGE);
	    }

	}
	return num;
    }


    public static double readDouble (String prompt)
    {
	String test;
	double num = 0;
	boolean letter = true; //test if it is a letter
	while (letter)
	{
	    try
	    {
		test = readString (prompt); //can call the other method to do most of the code
		num = Double.parseDouble (test);
		letter = false;
	    }
	    catch (NullPointerException n)
	    {
		System.exit (0);
	    }
	    catch (NumberFormatException f)
	    {
		JOptionPane.showMessageDialog (null, "Invalid Input", "Input", JOptionPane.ERROR_MESSAGE);
	    }

	}
	return num;
    }


    public static int getOptions (String prompt, String[] list)
    {

	return JOptionPane.showOptionDialog (null, prompt, "Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, list, list [0]);


    }


    public static int getConfirm (String prompt)
    {
	String[] options = {"Yes", "No"};
	return JOptionPane.showOptionDialog (null, prompt, "Confirm", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options [0]);

    }
}


