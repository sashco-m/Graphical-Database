import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class MouseClass implements MouseListener
{
    private JButton button1;
    private ImageIcon icon,iconHover;

    public MouseClass (JButton button1, ImageIcon icon, ImageIcon iconHover)
    {
	this.button1 = button1;
	button1.addMouseListener (this);
	this.icon=icon;
	this.iconHover=iconHover;
    }


    public void mouseClicked (MouseEvent m)
    {
	
    }


    public void mouseEntered (MouseEvent m)  //important
    {
	button1.setIcon(iconHover);
    }


    public void mouseExited (MouseEvent m)
    {
       button1.setIcon(icon);
    }


    public void mousePressed (MouseEvent m)
    {
	
    }


    public void mouseReleased (MouseEvent m)
    {
	
    }
}
