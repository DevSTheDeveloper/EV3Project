package EV3;

import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class TurnBehavior implements Behavior {
	//constants for rotation and activation distance
	final static int ARC_INCREMENT = 5;
	final static int APPROACH_DISTANCE = 15;
	
	private int turnDirection = 1; //left = 1; right = -1;
	
	private boolean suppressed = false;
    private MovePilot pilot;
   
    //define sensors and providers
    private EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(MotorPort.A);
    private SampleProvider distanceProvider = ultrasonicSensor.getDistanceMode(); 
    
    private EV3ColorSensor colorSensor = new EV3ColorSensor(MotorPort.B);
    private SensorMode colorProvider = colorSensor.getColorIDMode();
    
    //samples - could theoretically be packed into one?
    float[] distanceSample = new float[1];
    float[] colorSample = new float[1];
    
    @Override
    public boolean takeControl() {
    	//fetch samples
    	distanceProvider.fetchSample(distanceSample, 0);
    	colorProvider.fetchSample(colorSample, 0);
    	
    	//cast colour to integer
    	int currentColor = (int) colorSample[0];
    	//close to wall or over a directional colour
        return distanceSample[0] <= APPROACH_DISTANCE 
        		|| currentColor == Color.YELLOW
        		|| currentColor == Color.PINK;
    }
	
    @Override
    public void action() {
        suppressed = false;
        while (!suppressed) {
        	distanceProvider.fetchSample(distanceSample, 0);
        	colorProvider.fetchSample(colorSample, 0);
        	
        	//yellow for right, pink for left
        	if (colorSample[0] == Color.YELLOW) {
        		turnDirection = -1;
        	} else if (colorSample[0] == Color.PINK) {
				turnDirection = 1;
			}
        	
        	//if in distance start turning, use turnDirection to decide which way
        	if (distanceSample[0] <= APPROACH_DISTANCE)
        		pilot.arcForward(ARC_INCREMENT * turnDirection);
        }
    }
	
    @Override
    public void suppress() {
        suppressed = true;  //higher priority process takes over with this call
    }
}