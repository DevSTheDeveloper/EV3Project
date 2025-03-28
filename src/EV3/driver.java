package EV3;

import lejos.hardware.Button;

public class driver {
    private MotorControlBehavior motorControlBehavior;
    private SoundDetectionBehavior soundDetectionBehavior;
    private ObstacleDetectionBehavior obstacleDetectionBehavior;
    private ColorDetectionBehavior colorDetectionBehavior;
    private TurnBehavior turnBehavior;

    public driver() {
        motorControlBehavior = new MotorControlBehavior();
        soundDetectionBehavior = new SoundDetectionBehavior(motorControlBehavior);
        obstacleDetectionBehavior = new ObstacleDetectionBehavior(motorControlBehavior);
        colorDetectionBehavior = new ColorDetectionBehavior();

        // Initialize TurnBehavior with motorControlBehavior and colorDetectionBehavior
        turnBehavior = new TurnBehavior(motorControlBehavior, colorDetectionBehavior);
    }

    public void driverLoop() {
        motorControlBehavior.startMotors();

        while (true) {
            // Check for sound, obstacle, and color detection
            soundDetectionBehavior.checkSound();
            obstacleDetectionBehavior.checkObstacle();
            colorDetectionBehavior.checkColor();
            String detectedColor = colorDetectionBehavior.getDetectedColor();

            // Turn right if green is detected
            if ("GREEN".equals(detectedColor)) {
                turnBehavior.turnRight();
            }

            // Turn left if orange is detected
            if ("ORANGE".equals(detectedColor)) {
                turnBehavior.turnLeft();
            }

            // Stop robot if black is detected
            /*
            if ("BLACK".equals(detectedColor)) {
                turnBehavior.stopRobot();
            }*/

            // If ESCAPE is pressed, stop the program
            if (Button.ESCAPE.isDown()) {
                System.out.println("Program Terminated");
                break;
            }
            
        }

        colorDetectionBehavior.close(); // Clean up sensor properly
    }

    public static void main(String[] args) {
        driver driver = new driver();
        driver.driverLoop();
    }
}