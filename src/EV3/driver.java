package EV3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;

public class driver {

    private MotorControlBehavior motorControlBehavior;
    private SoundDetectionBehavior soundDetectionBehavior;
    private ObstacleDetectionBehavior obstacleDetectionBehavior;
    private ColorDetectionBehavior colorDetectionBehavior;
    private TurnBehavior turnBehavior;
    private MovePilot pilot;

    public driver() {
        motorControlBehavior = new MotorControlBehavior();
        soundDetectionBehavior = new SoundDetectionBehavior(motorControlBehavior);
        obstacleDetectionBehavior = new ObstacleDetectionBehavior(motorControlBehavior);
        colorDetectionBehavior = new ColorDetectionBehavior();
        turnBehavior = new TurnBehavior(motorControlBehavior, colorDetectionBehavior);
    }

    public void driverLoop() {
        // Display the startup screen with instructions
        displayStartScreen();

        // Wait for the Enter button to be pressed
        waitForEnterPress();

        // Proceed to the main loop after enter is pressed
        LCD.clear();
        motorControlBehavior.startMotors();

        // Main driving loop
        while (true) {
            // Check sound, obstacle, and color
            soundDetectionBehavior.checkSound();
            obstacleDetectionBehavior.checkObstacle();
            colorDetectionBehavior.checkColor();

            // Get detected color and take action
            String detectedColor = colorDetectionBehavior.getDetectedColor();
            handleDetectedColor(detectedColor);

            // Terminate program if Escape button is pressed
            if (Button.ESCAPE.isDown()) {
                System.out.println("Program Terminated");
                break;
            }
        }

        // Close color detection behavior at the end
        colorDetectionBehavior.close();
    }

    private void displayStartScreen() {
        LCD.clear();
        LCD.drawString("Track Car Project", 0, 2);  // Left-aligned text
        LCD.drawString("By Dev, Arshiya", 0, 3);
        LCD.drawString("Patrick & Faris", 0, 4);
        LCD.drawString("Press Enter", 0, 5);  // Instructions for the user

        // Add a small delay to give time for the user to see the start screen
        try {
            Thread.sleep(2000); // 2-second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForEnterPress() {
        while (!Button.ENTER.isDown()) {
            // Wait here until the Enter button is pressed
        }
    }

    private void handleDetectedColor(String detectedColor) {
        switch (detectedColor) {
            case "GREEN":
                turnBehavior.turnRight();
                break;
            case "ORANGE":
                turnBehavior.turnLeft();
                break;
            // Uncomment to stop robot if black is detected
            /*
            case "BLACK":
                turnBehavior.stopRobot();
                break;
            */
            default:
                // Do nothing if no recognized color is detected
                break;
        }
    }

    public static void main(String[] args) {
        driver driver = new driver();
        driver.driverLoop();
    }
}