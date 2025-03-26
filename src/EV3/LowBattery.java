//* @author Farris

package EV3;

import lejos.hardware.Battery;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.lcd.LCD;

public class LowBattery implements Behavior{
	private MovePilot pilot;
	private final float LOW_BATTERY_THRESHOLD = 7.0f;
	private boolean suppressed = false;
	
	public LowBattery(MovePilot pilot) {
		this.pilot = pilot;
	}
	
	public boolean takeControl() {
		return Battery.getVoltage() < LOW_BATTERY_THRESHOLD;	
	}
	
	public void action() {
		suppressed = false;
		
		pilot.stop();
		LCD.clear();
		LCD.drawString("Low Battery", 0, 2);
		LCD.drawString("Ending Operation", 0, 3);
		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			pilot.stop();
		}
		
		System.exit(0);
	
	}
	
	public void suppress() {
		suppressed = true;
		pilot.stop();
	}
}