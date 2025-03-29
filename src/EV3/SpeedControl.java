package EV3;
//* @author Farris

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.navigation.MovePilot;

public class SpeedControl implements Runnable {
    private final MovePilot pilot;
    private final EV3ColorSensor colorSensor;
    private boolean suppressed = false;

    public SpeedControl(MovePilot pilot, EV3ColorSensor colorSensor) {
        this.pilot = pilot;
        this.colorSensor = colorSensor;
    }

    private synchronized boolean takeControl() {
        int color = colorSensor.getColorID(); 
        System.out.println("Detected color: " + color); 
        return (color == Color.GREEN || color == Color.ORANGE || color == Color.BLUE);
    }

    private synchronized void action() {
        suppressed = false;
        double fastSpeed = 20.0;
        double slowSpeed = 5.0;
        double normalSpeed = 10.0;
        double blueSpeed = 150.0; 

        while (!suppressed) {
            int color = colorSensor.getColorID(); 
            if (color == Color.GREEN) {
                pilot.setLinearSpeed(fastSpeed);
            } else if (color == Color.ORANGE) {
                pilot.setLinearSpeed(slowSpeed);
            
            //NOTE by Dev: The blue speed control may not be used due to colorReader sensitivity
            } else if (color == Color.BLUE) { 
                pilot.setLinearSpeed(blueSpeed); 
            } else {
                pilot.setLinearSpeed(normalSpeed);
            }
            

            pilot.forward();
        }

        pilot.stop();
    }

    public void suppress() {
        suppressed = true;
    }

    public void run() {
        while (true) {
            if (takeControl()) {
                action();
            }
        }
    }
}