package EV3;

public class TurnBehavior {
    private static final int ROTATION_ANGLE = 180; // Desired turn angle (in degrees)
    private MotorControlBehavior motorControlBehavior;
    private ColorDetectionBehavior colorDetectionBehavior;

    public TurnBehavior(MotorControlBehavior motorControlBehavior, ColorDetectionBehavior colorDetectionBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        this.colorDetectionBehavior = colorDetectionBehavior;
    }

    private void turn(int angle) {
        int fullRotationSteps = 360; // Number of motor steps for a full 360-degree turn
        int rotationCount = (fullRotationSteps * angle) / 360;

        // Rotate the left motor forward and right motor backward for a right turn
        motorControlBehavior.getLeftMotor().rotate(rotationCount, true);
        motorControlBehavior.getRightMotor().rotate(-rotationCount);
    }

    private void stabilize() {
        try {
            Thread.sleep(500); // Stabilization delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stopMotorsAndWait() {
        // Stop motors before turning
        motorControlBehavior.stopMotors();
        stabilize(); // Ensure stabilization after stop
    }

    private void startMotors() {
        motorControlBehavior.startMotors(); // Resume forward motion
    }

    public void turnRight() {
        System.out.println("Turning right...");

        stopMotorsAndWait(); // Stop and wait

        // Turn 90 degrees right
        turn(ROTATION_ANGLE);

        stopMotorsAndWait(); // Stop motors after turn is complete

        // Resume forward motion
        startMotors();
    }

    public void turnLeft() {
        System.out.println("Turning left...");

        stopMotorsAndWait(); // Stop and wait

        // Turn 90 degrees left (using negative rotation for left)
        turn(-ROTATION_ANGLE);

        stopMotorsAndWait(); // Stop motors after turn is complete

        // Resume forward motion
        startMotors();
    }

    // Placeholder method for stopping the robot if necessary in the future
    /*
    public void stopRobot() {
        System.out.println("Halting Robot");
        motorControlBehavior.stopMotors();

        // Stay stopped while black is still detected
        while (true) {
            String detectedColor = colorDetectionBehavior.getDetectedColor();
            System.out.println("Detected color: " + detectedColor);  // Debugging line

            if ("BLACK".equals(detectedColor)) {
                // Only stop if black is detected
                try {
                    Thread.sleep(100); // Small delay to avoid CPU overload
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }

        System.out.println("No longer detecting BLACK. Resuming movement...");
        motorControlBehavior.startMotors(); // Resume forward motion after black is gone
    }
    */
}