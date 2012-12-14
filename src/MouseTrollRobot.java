import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Increments the cursor position by a random signed int value whenever the cursor position changes.
 * 
 * @author <a href="mailto:felixmilea@gmail.com">Felix Milea-Ciobanu</a>
 * */
public class MouseTrollRobot
{
	/**
	 * Max absolute value in pixels of the delta. The delta can be either positive or negative.
	 * A new delta is generated after every update delay if the cursor position
	 * has changed since the last update.
	 */
	private static final int MAX_TROLL_DELTA = 20;

	// Time interval in ms at which the program should check the mouse position for changes.
	private static final int UPDATE_DELAY = 200;
	
	private int startDelay;
	private Robot robot;
	private Timer updateTimer, startTimer;
	private Point lastPoint;
	
	public MouseTrollRobot (int startDelay) throws AWTException
	{
		this.startDelay = startDelay;
		robot = new Robot();
		setupUpdateTimer();
	}

	/**
	 * Sets up update timer loop. It checks the cursor position since the last
	 * interval and alters it in a random manner if it has changed.
	 * */
	private void setupUpdateTimer ()
	{
		updateTimer = new Timer(UPDATE_DELAY, new ActionListener()
		{
			@Override
			public void actionPerformed (ActionEvent e)
			{
				// if the program has just been started
				if (lastPoint == null)
				{
					lastPoint = MouseInfo.getPointerInfo().getLocation();
				}
				
				// else if the mouse position has changed
				else if ( !lastPoint.equals(MouseInfo.getPointerInfo().getLocation()) )
				{
					// update the mouse position and keep track of the new position
					lastPoint.x += randomDelta();
					lastPoint.y += randomDelta();
					robot.mouseMove(lastPoint.x, lastPoint.y);
				}
			}
		});
	}
	
	/**
	 * Generate a new random delta to be used to update the mouse position.
	 */
	private int randomDelta ()
	{
		return (int)Math.round( Math.random() * MAX_TROLL_DELTA * 2 ) - MAX_TROLL_DELTA;
	}
	
	/**
	 * Starts the update timer which checks and alters the mouse position.
	 * */
	public void start ()
	{
		startTimer = new Timer(startDelay, new ActionListener()
		{
			@Override
			public void actionPerformed (ActionEvent e)
			{
				updateTimer.start();
			}
		});
		
		startTimer.setRepeats(false);
		startTimer.start();
	}
	
	/**
	 * Stops the update timer, which stops the mouse listening/altering.
	 * */
	public void stop ()
	{
		updateTimer.stop();
	}
	
}
