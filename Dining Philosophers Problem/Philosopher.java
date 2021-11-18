/**
 * The class Philosopher represents a Philosopher. 
 * 
 * @author (Inbal Sapir)
 * @version (January 10, 2020)
 */
public class Philosopher implements Runnable
{
	// variables
	private String name; // name of philosopher
	private Stick leftStick; // the stick to the left of the philosopher
	private Stick rightStick; // the stick to the right of the philosopher
	private int state; // if 0- philosopher is trying to eat, if 1- philosopher is eating, if 2- philosopher is thinking
	private Controller controller;
	private Table table; // the table this philosopher is seating at
	private final int DELAY= 10000; // delaying eating and thinking up to such milliseconds
	// constructor
	/**
	 * Constructs a new philosopher using his name, the two sticks placed next to him,
	 * an object to notify when this runnable class finishes running and the table he is seating at.
	 * @param name the philosopher's name
	 * @param rightStick the stick to the philosopher's right
	 * @param leftStick the stick to the philosopher's left
	 * @param controller the object to notify when this runnable class finishes running
	 * @param table the table the philosopher is seating at
	 */
	public Philosopher (String name, Stick rightStick, Stick leftStick, Controller controller, Table table)
	{
		this.name= name;
		this.rightStick= rightStick;
		this.leftStick= leftStick;
		state= 0;
		this.controller= controller;
		this.table= table;
	}
	// methods
	/**
	 * The philosophers tries to eat, then eats, then thinks.  
	 * @override run in Runnable interface
	 */
	public void run ()
	{
		while (true)
		{
			eating ();
			thinking ();
			state= 0;
			table.repaint();
			controller.finished();
		}
	}
	/**
	 * Philosophers tries to eat.
	 */
	public void eating ()
	{
		if (rightStick.getMark()<leftStick.getMark()) // trying to take the stick with the lowest serial number
		{
			rightStick.useStick();
			leftStick.useStick();
		}
		else 
		{
			leftStick.useStick();
			rightStick.useStick();
		}
		state= 1;
		table.repaint();
		try 
		{
			Thread.sleep((int)(Math.random()*DELAY));
		}
		catch (InterruptedException e) 
		{
			System.out.println("Interrupted while sleeping");
		}
	}
	/**
	 * Philosophers thinks.
	 */
	public void thinking ()
	{
		rightStick.releaseStick(); // releasing sticks
		leftStick.releaseStick();
		state= 2;
		table.repaint();
		try 
		{
			Thread.sleep((int)(Math.random()*DELAY));
		}
		catch (InterruptedException e) 
		{
			System.out.println("Interrupted while sleeping");
		}
	}
	/**
	 * Gets the philosopher's state: 0 for trying to eat, 1 for eating, 2 for thinking.
	 * @return the philosopher's state
	 */
	public int getState ()
	{
		return state;
	}
}
