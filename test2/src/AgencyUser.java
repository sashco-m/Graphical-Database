import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class AgencyUser
{
    //test
    static JPanel cards; //the main JPanel with cardLayout

    public static void main (String[] args)
    {
	JFrame f = new JFrame ("TACAD by Philip & Sashco");


	cards = new JPanel (new CardLayout ()); //important


	CustomerDatabase cd = new CustomerDatabase ("C:\\Users\\sashc\\eclipse-workspace\\test2\\src\\accounts.txt");
	Agency a = new Agency (cards, cd, f);
	f.setSize (600, 600);
	f.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
	f.setResizable (false);

	f.getContentPane ().add (cards);
	f.setVisible (true);

    }
}
