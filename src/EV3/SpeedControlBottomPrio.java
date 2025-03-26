package EV3;

//by Faris
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.navigation.MovePilot;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Behavior;

public class SpeedControlBottomPrio implements Behavior {
	
	private MovePilot pilot;
	private EV3ColorSensor colorSensor;
	private boolean suppressed = false;
	
    public SpeedControlBottomPrio(MovePilot pilot, EV3ColorSensor colorSensor) {
        this.pilot = pilot;
        this.colorSensor = colorSensor;
    }
    
    public boolean takeControl() {
    	return true;
    }
	
	public void action() {
		suppressed = false;
		
		double fastSpeed = 20.0;
		double slowSpeed = 5.0;
		double normalSpeed = 10.0;
		
		SensorMode colorIDMode = colorSensor.getColorIDMode();
		float [] sample = new float[colorIDMode.sampleSize()];
		
		while (!suppressed) {
			colorIDMode.fetchSample(sample, 0);
			int color = (int) sample[0];

			if (color == Color.GREEN) {
				pilot.setLinearSpeed(fastSpeed);
			} else if (color == Color.ORANGE) {
				pilot.setLinearSpeed(slowSpeed);
			} else {
				pilot.setLinearSpeed(normalSpeed);
			}
		
			pilot.forward();
			Thread.yield();

		}
		
		pilot.stop();
	
	}
	
	public void suppress() {
		suppressed = true;
		pilot.stop();
	}
	
}