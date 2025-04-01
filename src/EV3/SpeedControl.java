package EV3;
//By Faris

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class SpeedControl implements Runnable {
    private final MotorControlBehavior motorControlBehavior; 
    private final ColorDetectionBehavior colorDetectionBehavior;
    private boolean suppressed = false;
    private int previousSpeed;

    public SpeedControl(MotorControlBehavior motorControlBehavior, ColorDetectionBehavior colorDetectionBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        this.colorDetectionBehavior = colorDetectionBehavior;
        this.previousSpeed = motorControlBehavior.getSpeed(); //store the initial speed
    }

    private synchronized boolean takeControl() {
        String detectedColor = colorDetectionBehavior.getDetectedColor();
        
        return detectedColor.equals("BLUE"); 
    }

    private synchronized void action() {
        suppressed = false;
        int blueSpeed = 150; 
        int currentSpeed = motorControlBehavior.getSpeed(); // Get current speed

        while (!suppressed) {
            String detectedColor = colorDetectionBehavior.getDetectedColor(); // Get current detected color

            if (detectedColor.equals("BLUE")) {
                //if blue is detected, speed up to 150
                if (currentSpeed != blueSpeed) {
                    motorControlBehavior.setSpeed(blueSpeed);
                }
            } else {
                //blue is no longer detected, revert to the previous speed
                if (currentSpeed != previousSpeed) {
                    motorControlBehavior.setSpeed(previousSpeed);
                }
            }

            currentSpeed = motorControlBehavior.getSpeed(); //update current speed after the change
        }
    }

    public void suppress() {
        suppressed = true;
    }

    public void run() { //this function is called by the Driver
        while (true) {
            if (takeControl()) {
                action();
            }
        }
    }
}