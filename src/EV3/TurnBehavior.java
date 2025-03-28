package EV3;

public class TurnBehavior {
    private static final int ROTATION_ANGLE = 90;  // Desired turn angle (in degrees)
    private boolean hasTurned = false;
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

    public void turnRight() {
        if (!hasTurned) {
            System.out.println("Turning right...");

            // Stop motors before turning
            motorControlBehavior.stopMotors();

            // Turn 90 degrees right
            turn(ROTATION_ANGLE);

            // Stop motors after turn is complete
            motorControlBehavior.stopMotors();

            try {
                Thread.sleep(500); // Small delay to stabilize
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Resume forward motion
            motorControlBehavior.startMotors();

            hasTurned = true;
        }
    }

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
                // If it's not black, break out of the loop
                break;
            }
        }

        System.out.println("No longer detecting BLACK. Resuming movement...");
        motorControlBehavior.startMotors(); // Resume forward motion after black is gone
    }

    // Reset the turn flag to allow future turning
    public void resetTurn() {
        hasTurned = false;
    }
}