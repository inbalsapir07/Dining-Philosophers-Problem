/**
 * The class Stick represents a stick. 
 * 
 * @author (Inbal Sapir)
 * @version (January 10, 2020)
 */
public class Stick 
{
	// variables
	private int mark; // the stick's serial number
	private boolean isUsed; // is the stick in use
	// constructor
	/**
	 * Constructs a new stick using a serial number.
	 * @param mark the serial number
	 */
	public Stick (int mark)
	{
		this.mark= mark;
		isUsed= false;
	}
	// methods
	/**
	 * Uses this stick if this stick is not used. If this stick is used, waits.
	 */
	public synchronized void useStick ()
	{
		while (isUsed==true)
		{
			try
			{
				wait ();
			}
			catch (InterruptedException e) 
			{
				System.out.println("Interrupted while waiting");
			}
		}
		isUsed= true;
	}
	/**
	 * Releases this stick.
	 */
	public synchronized void releaseStick ()
	{
		isUsed= false;
		notifyAll();
	}
	/**
	 * Gets the stick's serial number.
	 * @return the stick's serial number
	 */
	public int getMark ()
	{
		return mark;
	}
}
