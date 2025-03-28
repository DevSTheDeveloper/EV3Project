package EV3;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.Button;

public class ColorSensorTest {
    public static void main(String[] args) {
        // Initialize the EV3 Color Sensor on port S1
    	EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
        // Infinite loop to continuously read sensor data
        while (!Button.ENTER.isDown()) {  // Wait for user to press Enter to stop
            // Create a float array to hold RGB values
            float[] rgb = new float[3];

            // Fetch the current RGB values from the color sensor
            colorSensor.getRGBMode().fetchSample(rgb, 0);

            // Print the RGB values to the screen
            System.out.println("Red: " + rgb[0]);
            System.out.println("Green: " + rgb[1]);
            System.out.println("Blue: " + rgb[2]);

            // Pause for a short time to avoid spamming the output
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        colorSensor.close();  // Close the sensor when done
        System.out.println("Test Completed");
    }
}