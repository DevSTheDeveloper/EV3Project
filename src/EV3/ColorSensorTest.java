package EV3;
////THIS IS ONLY USED FOR TESTING - WILL NOT APPEAR IN FINAL DRAFT
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.Button;

public class ColorSensorTest {
    public static void main(String[] args) {
    	EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
        while (!Button.ENTER.isDown()) {  // Wait for user to press Enter to stop
            //Create a float array to hold RGB values
            float[] rgb = new float[3];

            //Fetch the current RGB values from the color sensor
            colorSensor.getRGBMode().fetchSample(rgb, 0);

            //Print the RGB values to the screen
            System.out.println("Red: " + rgb[0]);
            System.out.println("Green: " + rgb[1]);
            System.out.println("Blue: " + rgb[2]);

        }

        colorSensor.close();  //Close the sensor when done
        System.out.println("Test Completed");
    }
}