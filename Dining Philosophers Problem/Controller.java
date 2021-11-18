/**
 * The class Controller counts the number of threads that have finished running
 * out of a certain number of threads that are supposed to run, then notifying waiting threads.
 * 
 * @author (Inbal Sapir)
 * @version (January 10, 2020)
 */
public class Controller 
{
	// variables
	private int number; // number of philosophers
	private int counter; // number of philosophers finished eating and thinking
	// constructor
	/**
	 * Constructs a new controller, using the number of threads that are supposed to run
	 * before notifying waiting threads.
	 * @param number the number of threads that are supposed to run before notifying waiting threads
	 */
	public Controller (int number)
	{
		this.number= number;
		counter= 0;
	}
	// method
	/**
	 * Counts the number of threads that have finished running.
	 * If all relevant threads have finished running, notifying waiting threads and starts counting from 0 again.
	 * If not all relevant threads have finished running, prevents a thread from running. 
	 */
	public synchronized void finished ()
	{
		counter++;
		if (counter<number)
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
		else
		{
			counter= 0;
			notifyAll();
		}
	}
}
