import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CloseWindow implements WindowListener
{
    JFrame f;
    CustomerDatabase cd;
    JPanel cards;
    CardLayout c;

    public CloseWindow (JFrame f, CustomerDatabase cd,JPanel cards,CardLayout c)
    {
	f.addWindowListener (this);
	this.f = f;
	this.cd = cd;
	this.c=c;
	this.cards=cards;
    }


    public void windowActivated (WindowEvent w)
    {
    }


    public void windowClosed (WindowEvent w)
    {
    }


    public void windowOpened (WindowEvent w)
    {
    }


    public void windowDeactivated (WindowEvent w)
    {
    }


    public void windowClosing (WindowEvent w)
    {

	c.show(cards,"Exit");
	
    }


    public void windowIconified (WindowEvent w)
    {
    }


    public void windowDeiconified (WindowEvent w)
    {
    }
}
