package EV3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.motor.Motor;

public class driver {

    final static float WHEEL_DIAMETER = 51;  // Wheel diameter (mm)
    final static float TRACK_WIDTH = 144;    // Distance between the wheels (mm)

    public static void main(String[] args) {
        // Create motors for left and right wheels
        EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);

        // Create the ultrasonic sensor
        EV3UltrasonicSensor ultraSensor = new EV3UltrasonicSensor(SensorPort.S2);

        // Get the distance mode from the ultrasonic sensor
        SampleProvider distanceMode = ultraSensor.getDistanceMode();
        float[] sample = new float[distanceMode.sampleSize()];

        // Set motor speeds
        leftMotor.setSpeed(300);
        rightMotor.setSpeed(300);

        // Start motors
        leftMotor.forward();
        rightMotor.forward();

        while (true) {
            // Fetch the distance sample from the ultrasonic sensor
            distanceMode.fetchSample(sample, 0);
            float distance = sample[0];  // Get distance in meters

            // Convert distance to centimeters
            distance *= 100;  // In cm

            // Display the distance on the screen
            LCD.clear();
            LCD.drawString("Dist: " + distance + " cm", 0, 0);
            LCD.refresh();

            // Stop the motors if the distance is less than 10 cm
            if (distance < 10) {
                System.out.println("Stopping...");
                leftMotor.stop(true); // Stop the left motor immediately
                rightMotor.stop(true); // Stop the right motor immediately
                break;  // Exit the loop
            }

            try {
                Thread.sleep(100);  // Delay for 100ms before the next sensor reading
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}