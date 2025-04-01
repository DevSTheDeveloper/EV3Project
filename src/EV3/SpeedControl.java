package EV3;
//By Faris
//Note from Dev: This class will be called properly when it is optimised (it works perfectly fine),
//We have temporarily disabled the detection of the blue colour due to a sensor read error
//You can see that we have commented the Blue colour on the hashmap - It worked earlier but is very 
//Sensitive to light and changes

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class SpeedControl implements Runnable {
    private final MotorControlBehavior motorControlBehavior; // Use MotorControlBehavior instead of MovePilot
    private final EV3ColorSensor colorSensor;
    private boolean suppressed = false;

    public SpeedControl(MotorControlBehavior motorControlBehavior, EV3ColorSensor colorSensor) {
        this.motorControlBehavior = motorControlBehavior;
        this.colorSensor = colorSensor;
    }

    private synchronized boolean takeControl() {
        int color = colorSensor.getColorID(); 
        System.out.println("Detected color: " + color); 
        return (color == Color.GREEN || color == Color.ORANGE || color == Color.BLUE);
    }

    private synchronized void action() {
        suppressed = false;
        int fastSpeed = 300;  // Adjusted to work with the motor speeds
        int slowSpeed = 100;
        int normalSpeed = 200;
        int blueSpeed = 150;

        while (!suppressed) {
            int color = colorSensor.getColorID(); 
            if (color == Color.GREEN) {
                motorControlBehavior.setSpeed(fastSpeed);
            } else if (color == Color.ORANGE) {
                motorControlBehavior.setSpeed(slowSpeed);
            } else if (color == Color.BLUE) { 
                motorControlBehavior.setSpeed(blueSpeed); 
            } else {
                motorControlBehavior.setSpeed(normalSpeed);
            }

            motorControlBehavior.startMotors(); // Use the method to start motors
        }

        motorControlBehavior.stopMotors(); // Stop the motors when suppressed
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