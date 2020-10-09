import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class CornerButton extends JButton
{
    //private int size;
    private int[] xPoints, yPoints;
    private int xPointsTL[] = {0, 100, 100, 35, 35, 0};
    private int yPointsTL[] = {0, 0, 35, 35, 100, 100};
    private int xPointsTR[] = {0, 100, 100, 65, 65, 0};
    private int yPointsTR[] = {0, 0, 100, 100, 35, 35};
    private int xPointsBL[] = {0, 35, 35, 100, 100, 0};
    private int yPointsBL[] = {0, 0, 65, 65, 100, 100};
    private int xPointsBR[] = {0, 65, 65, 100, 100, 0};
    private int yPointsBR[] = {65, 65, 0, 0, 100, 100};

    public static final int ORIENT_NORTH_WEST = 0;
    public static final int ORIENT_NORTH_EAST = 1;
    public static final int ORIENT_SOUTH_WEST = 2;
    public static final int ORIENT_SOUTH_EAST = 3;

    public CornerButton (int orientation)
    {
	super ();
	setContentAreaFilled (false);
	if (orientation == 0)
	{
	    xPoints = xPointsTL;
	    yPoints = yPointsTL;

	}
	else if (orientation == 1)
	{
	    xPoints = xPointsTR;
	    yPoints = yPointsTR;
	}
	else if (orientation == 2)
	{
	    xPoints = xPointsBL;
	    yPoints = yPointsBL;
	}
	else if (orientation == 3)
	{
	    xPoints = xPointsBR;
	    yPoints = yPointsBR;
	}

    }


    protected void paintComponent (Graphics g)
    {
	if (getModel ().isArmed ())
	{
	    g.setColor (Color.red); //dont want this to exist
	}
	else
	{
	    g.setColor (getBackground ());
	}

	//Polygon polygon = new Polygon (xPoints, yPoints, xPoints.length);
	//g.fillPolygon (polygon);
	super.paintComponent (g);
    }


    protected void paintBorder (Graphics g)
    {
	//g.setColor (getForeground ());
	//Polygon polygon = new Polygon (xPoints, yPoints, xPoints.length);
	//g.drawPolygon (polygon);
    }


    Polygon polygon;

    public boolean contains (int x, int y)
    {
	if (polygon == null || !polygon.getBounds ().equals (getBounds ()))
	{
	    polygon = new Polygon (xPoints, yPoints, xPoints.length);
	}
	return polygon.contains (x, y);
    }
}
