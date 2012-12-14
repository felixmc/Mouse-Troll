import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;

/**
 * Console application that monitors the cursor position and
 * alters it in a random manner whenever it is changed.
 *
 * @author <a href="mailto:felixmilea@gmail.com">Felix Milea-Ciobanu</a>
 *
 * WARNING: running this program will make it hard to move your cursor.
 * Run it at your own discretion!
 * 
 * If Eclipse is your Java IDE, the simplest way to close the program while the
 * cursor is being "trolled" seems to be to exit Eclipse using the keyboard.
 * */
public class Driver
{

	// delay in ms before the "mouse trolling" begins
	private static final int START_DELAY = 2000;
	
	public static void main(String[] args) throws AWTException
	{
		MouseTrollRobot mouseTroll = new MouseTrollRobot(START_DELAY);
		
		// start monitoring/altering the mouse position
		mouseTroll.start();
		
		/**
		 * This stops the program from exiting since there are no
		 * other operations going on. This can be removed if program implements a GUI.
		 * Provides the same functionality as C#'s Console.Read() and C++'s System.pause()
		 * */
		Scanner scan = new Scanner(System.in);
		scan.next();
		
		/**
		 * The "mouse trolling" can be stopped from within the program by either
		 * doing scan.close() or mouseTroll.stop()
		 * */
	}

}
