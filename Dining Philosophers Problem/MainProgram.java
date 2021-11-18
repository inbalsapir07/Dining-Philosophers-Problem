import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * The Main Program
 * Question 2, maman 15.
 * 
 * @author (Inbal Sapir)
 * @version (January 10, 2020)
 */
public class MainProgram 
{
	/**
	 * The main method of the program.
	 * Displaying five philosophers seating at a round table.
	 * The philosophers either try to eat, eat or think,
	 * while it is possible to eat only when holding two sticks,
	 * and only one stick is placed between every two philosophers.
	 */
	public static void main(String[] args) 
	{
		Table table= new Table ();
		JFrame frame= new JFrame ("Philosophers Table");
		frame.add(table);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setVisible(true);
		Thread thread= new Thread (table);
		JOptionPane.showMessageDialog(frame, "Philosopher is trying to eat- Unfilled magenta circle"+ 
		"\nPhilosopher is eating- Filled magenta circle"+ "\nPhilosopher is thinking- Filled yellow circle");
		thread.start();
	}
}
