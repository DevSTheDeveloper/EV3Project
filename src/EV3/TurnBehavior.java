package EV3;
//By Faris
public class TurnBehavior {
    private static final int ROTATION_ANGLE = 180; //180 is 90* in real world with robot
    private MotorControlBehavior motorControlBehavior;
    private ColorDetectionBehavior colorDetectionBehavior;

    public TurnBehavior(MotorControlBehavior motorControlBehavior, ColorDetectionBehavior colorDetectionBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        this.colorDetectionBehavior = colorDetectionBehavior;
    }

    // Method to perform the turn
    private void turn(int angle) {
        // Steps per rotation for a full 360-degree turn
        int fullRotationSteps = 360;
        //int rotationCount = (fullRotationSteps * angle) / 360; - For debug


        if (angle > 0) {
            // Right turn
            motorControlBehavior.getLeftMotor().rotate(180, true);  
            motorControlBehavior.getRightMotor().rotate(-180);
        } else {
            // Left turn (negative angle)
            motorControlBehavior.getLeftMotor().rotate(-180, true); 
            motorControlBehavior.getRightMotor().rotate(180);
        }

        stabilize(); // Ensure stabilization after turn
    }

    // Stabilization after turn
    private void stabilize() {
        try {
            Thread.sleep(500); // Allow time for the robot to settle
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Stop motors and wait before turning
    private void stopMotorsAndWait() {
        motorControlBehavior.stopMotors(); // Stop motors before turning
        System.out.println("Motors stopped."); // Debugging output
        stabilize(); // Ensure stabilization after stop
    }

    // Start motors to continue forward
    private void startMotors() {
        motorControlBehavior.startMotors(); // Resume forward motion
        System.out.println("Motors started."); // Debugging output
    }

    // Method to turn right by 180 degrees
    public void turnRight() {
        System.out.println("Turning right...");

        stopMotorsAndWait(); // Stop before turning

        turn(ROTATION_ANGLE); // Turn right (180 degrees)

        stopMotorsAndWait(); // Stop motors after turn

        startMotors(); // Resume forward motion
    }

    // Method to turn left by 180 degrees
    public void turnLeft() {
        System.out.println("Turning left...");

        stopMotorsAndWait(); // Stop before turning

        turn(-ROTATION_ANGLE); // Turn left (negative 180 degrees)

        stopMotorsAndWait(); // Stop motors after turn

        startMotors(); // Resume forward motion
    }
}