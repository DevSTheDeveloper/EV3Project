/*
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class ColorDetection {

    private EV3ColorSensor colorSensor;

    // Constructor to initialize the color sensor
    public ColorDetection(SensorPort port) {
        colorSensor = new EV3ColorSensor(port); // Initialize the sensor on the specified port
    }

    // Method to get and return the detected color as a string
    public String detectColor() {
        int colorID = colorSensor.getColorID(); // Get the detected color ID
        String detectedColor;

        // Map the color ID to a string representing the detected color
        switch (colorID) {
            case EV3ColorSensor.COLOR_RED:
                detectedColor = "RED";
                break;
            case EV3ColorSensor.COLOR_GREEN:
                detectedColor = "GREEN";
                break;
            case EV3ColorSensor.COLOR_BLUE:
                detectedColor = "BLUE";
                break;
            case EV3ColorSensor.COLOR_YELLOW:
                detectedColor = "YELLOW";
                break;
            case EV3ColorSensor.COLOR_BLACK:
                detectedColor = "BLACK";
                break;
            case EV3ColorSensor.COLOR_WHITE:
                detectedColor = "WHITE";
                break;
            case EV3ColorSensor.COLOR_BROWN:
                detectedColor = "BROWN";
                break;
            default:
                detectedColor = "UNKNOWN";
                break;
        }

        return detectedColor; // Return the detected color as a string
    }

    // Method to continuously detect and print colors
    public void startColorDetection() {
        while (true) {
            String color = detectColor(); // Detect the color
            System.out.println("Detected color: " + color); // Print the detected color
            Delay.msDelay(500); // Delay to prevent spamming the console
        }
    }

    // Main method to test color detection
    public static void main(String[] args) {
        ColorDetection colorDetection = new ColorDetection(SensorPort.S1); // Use the appropriate sensor port
        colorDetection.startColorDetection(); // Start detecting colors
    }
}
*/