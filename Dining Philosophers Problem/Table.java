import java.awt.*;
import java.util.concurrent.*;
import javax.swing.*;
/**
 * The class Table represents a table with philosophers and sticks, 
 * with only one stick placed between every two philosophers.
 * The philosophers either try to eat, eat or think,
 * while it is possible to eat only when holding two sticks.
 * 
 * @author (Inbal Sapir)
 * @version (January 10, 2020)
 */
public class Table extends JPanel implements Runnable
{
	// variables
	private final int PHILOSOPHERS_OR_STICKS= 5; // the number of philosophers and the number of sticks
	private Philosopher [] philosophers; // an array of all the philosophers seating at the table
	// constructor
	/**
	 * An empty constructor. Constructs a new Table object, 
	 * composed of philosophers and sticks.
	 */
	public Table ()
	{
		philosophers= new Philosopher [PHILOSOPHERS_OR_STICKS];
		Stick [] sticks= new Stick [PHILOSOPHERS_OR_STICKS];
		// constructing sticks
		for (int i=0; i<philosophers.length; i++) 
			sticks[i]= new Stick (i+1);
		// constructing philosophers and associating sticks with philosophers
		Controller controller= new Controller (PHILOSOPHERS_OR_STICKS);
		for (int i=0; i<philosophers.length; i++) 
		{
			if (i<philosophers.length-1)
				philosophers[i]= new Philosopher (""+(i+1), sticks[i], sticks[i+1], controller, this);
			else 
				philosophers[i]= new Philosopher (""+(i+1), sticks[i], sticks[0], controller, this);
		}
	}
	// methods
	/**
	 * Run method. The philosophers either try to eat, eat or think,
     * while it is possible to eat only when holding two sticks.
	 * @override run in Runnable interface
	 */
	public void run ()
	{
		ExecutorService executor= Executors.newCachedThreadPool();
		for (int i=0; i<philosophers.length; i++)
			executor.execute((Runnable)philosophers[i]);
	}
	/**
	 * Painting an Illustration of the Philosophers trying to eat, eating or thinking.
	 * @override paintComponent in JPanel class
	 * @param g graphic object
	 */
	public synchronized void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		int width = getWidth(); // width of this panel
		int height = getHeight(); // height of this panel
		int portion= (width*2+height*2)/PHILOSOPHERS_OR_STICKS; // intervals between philosophers
		int [] xPointsTable= new int [PHILOSOPHERS_OR_STICKS]; // the x coordinates of the corners of the table
		int [] yPointsTable= new int [PHILOSOPHERS_OR_STICKS]; // the y coordinates of the corners of the table
		int [] xPointsSeats= new int [PHILOSOPHERS_OR_STICKS]; // the x coordinates of the circles that represent the philosophers
		int [] yPointsSeats= new int [PHILOSOPHERS_OR_STICKS]; // the y coordinates of the circles that represent the philosophers
		int size= (int) Math.sqrt(width*height/40); // the size of the circles that represent the philosophers
		// calculating the x,y coordinates of the corners of the table and the circles that represent the philosophers
		for (int i=0; i<philosophers.length; i++)
		{
			int distance= (width/2)+(i*portion); // the distance of philosopher[i] from the upper left side of the panel
			if (distance<width) // if philosopher[i] is located on the upper side of the panel
			{
				xPointsTable[i]= distance;
				yPointsTable[i]= 0;
				xPointsSeats[i]= distance-(size/2);
				yPointsSeats[i]= 0;
			}
			if (width<=distance && distance<width+height) // if philosopher[i] is located on the right side of the panel
			{
				xPointsTable[i]= width;
				yPointsTable[i]= distance-(width);
				xPointsSeats[i]= width-size;
				yPointsSeats[i]= distance-(width)-(size/2);
			}
			if (width+height<=distance && distance<width*2+height) // if philosopher[i] is located on the bottom side of the panel
			{
				xPointsTable[i]= width*2+height-distance;
				yPointsTable[i]= height;
				xPointsSeats[i]= width*2+height-distance-(size/2);
				yPointsSeats[i]= height-size;
			}
			if (width*2+height<=distance && distance<width*2+height*2) // if philosopher[i] is located on the left side of the panel
			{
				xPointsTable[i]= 0;
				yPointsTable[i]= width*2+height*2-distance;
				xPointsSeats[i]= 0;
				yPointsSeats[i]= width*2+height*2-distance-(size/2);
			}
			if (width*2+height*2<=distance) // if philosopher[i] is located on the upper side of the panel
			{
				xPointsTable[i]= distance-(width*2+height*2);
				yPointsTable[i]= 0;
				xPointsSeats[i]= distance-(width*2+height*2)-(size/2);
				yPointsSeats[i]= 0;
			}
		}
		// drawing the table
		g.setColor(new Color(120, 90, 70));
		g.fillPolygon(xPointsTable, yPointsTable, PHILOSOPHERS_OR_STICKS);
		// drawing philosophers considering their state
		for (int i=0; i<philosophers.length; i++) 
		{
			if (philosophers[i].getState()==0) // if philosopher[i] is trying to eat
			{
				g.setColor(Color.MAGENTA);
				g.drawOval(xPointsSeats[i], yPointsSeats[i], size, size);
			}
			if (philosophers[i].getState()==1) // if philosopher[i] is eating
			{
				g.setColor(Color.MAGENTA);
				g.fillOval(xPointsSeats[i], yPointsSeats[i], size, size);
			}
			if (philosophers[i].getState()==2) // if philosopher[i] is thinking
			{
				g.setColor(Color.YELLOW);
				g.fillOval(xPointsSeats[i], yPointsSeats[i], size, size);
			}
		}
	}
}
