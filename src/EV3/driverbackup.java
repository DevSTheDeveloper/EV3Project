/*
package EV3;
// by Dev and modified by Patrick 

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.Color;

public class driverbackup {

    private EV3LargeRegulatedMotor leftMotor;
    private EV3LargeRegulatedMotor rightMotor;
    private EV3UltrasonicSensor ultraSensor;
    private EV3ColorSensor colorSensor;  // Initialize the color sensor
    private SampleProvider distanceMode;
    private float[] sample;

    public driverbackup() {
        this.leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        this.rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);
        this.ultraSensor = new EV3UltrasonicSensor(SensorPort.S2);
        this.colorSensor = new EV3ColorSensor(SensorPort.S1);  // Initialize color sensor on port S1

        // Set motor speeds
        this.leftMotor.setSpeed(300);
        this.rightMotor.setSpeed(300);

        this.distanceMode = ultraSensor.getDistanceMode();
        this.sample = new float[distanceMode.sampleSize()];
    }

    // Getter methods for SampleProvider and sample array
    public SampleProvider getDistanceMode() {
        return distanceMode;
    }

    public float[] getSample() {
        return sample;
    }

    // Start the motors
    public void startMotors() {
        leftMotor.forward();
        rightMotor.forward();
    }

    // Stop the motors
    public void stopMotors() {
        leftMotor.stop(true);
        rightMotor.stop(true);
    }

    // Restart the motors (when object is no longer detected)
    public void restartMotors() {
        System.out.println("Restarting motors...");
        leftMotor.forward();
        rightMotor.forward();
    }

    // Main loop runs continuously
    public void mainLoop() {
        while (true) {
            // Get the red, green, and blue components of the detected color
            int redValue = colorSensor.getRed(); // Get the red component value (0–255)
            int greenValue = colorSensor.getGreen(); // Get the green component value (0–255)
            int blueValue = colorSensor.getBlue(); // Get the blue component value (0–255)

            // Display the RGB values
            System.out.println("Red: " + redValue);
            System.out.println("Green: " + greenValue);
            System.out.println("Blue: " + blueValue);

            // Sleep for 500ms before the next iteration
            try {
                Thread.sleep(500);  // Half second delay between iterations
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        driver driver = new driver();
        driver.mainLoop();
    }
}
*/