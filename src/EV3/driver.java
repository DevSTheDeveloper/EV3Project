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
    private LowBattery lowBattery;
    private MovePilot pilot;

    public driver() {
        motorControlBehavior = new MotorControlBehavior();
        soundDetectionBehavior = new SoundDetectionBehavior(motorControlBehavior);
        obstacleDetectionBehavior = new ObstacleDetectionBehavior(motorControlBehavior);
        colorDetectionBehavior = new ColorDetectionBehavior();
        turnBehavior = new TurnBehavior(motorControlBehavior, colorDetectionBehavior);
        
        lowBattery = new LowBattery(pilot);
    }

    public void driverLoop() {
        // Display the startup screen with instructions
        LCD.clear();
        LCD.drawString("Track Car Project", 0, 2);  // Left-aligned text
        LCD.drawString("By Dev, Arshiya", 0, 3);
        LCD.drawString("Patrick & Faris", 0, 4);
        LCD.drawString("Press Enter", 0, 5);  // Instructions for the user

        // Add a small delay to give time for the user to see the start screen
        try {
            Thread.sleep(2000); // 2-second delay (adjust if necessary)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wait for the Enter button to be pressed
        while (!Button.ENTER.isDown()) {
            // Wait here till user clicks the enter button to start
        }
        
        //proceed to the main loop after enter is pressed
        LCD.clear();
        motorControlBehavior.startMotors();

        while (true) {
            if (lowBattery.takeControl()) {
                lowBattery.action();
                break;
            }

            soundDetectionBehavior.checkSound();
            obstacleDetectionBehavior.checkObstacle();
            colorDetectionBehavior.checkColor();
            String detectedColor = colorDetectionBehavior.getDetectedColor();

            if ("GREEN".equals(detectedColor)) {
                turnBehavior.turnRight();
            }

            if ("ORANGE".equals(detectedColor)) {
                turnBehavior.turnLeft();
            }

            // Stop robot if black is detected
            /*
            if ("BLACK".equals(detectedColor)) {
                turnBehavior.stopRobot();
            }*/

            if (Button.ESCAPE.isDown()) {
                System.out.println("Program Terminated");
                break;
            }
        }

        colorDetectionBehavior.close(); 
    }

    public static void main(String[] args) {
        driver driver = new driver();
        driver.driverLoop();
    }
}