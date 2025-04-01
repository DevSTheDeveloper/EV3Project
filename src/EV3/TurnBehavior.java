package EV3;
//By Patrick
public class TurnBehavior {
    private static final int ROTATION_ANGLE = 173; //as close as i can get to a 90 deg turn
    private MotorControlBehavior motorControlBehavior;
    private ColorDetectionBehavior colorDetectionBehavior;

    public TurnBehavior(MotorControlBehavior motorControlBehavior, ColorDetectionBehavior colorDetectionBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        this.colorDetectionBehavior = colorDetectionBehavior;
    }

    private void turn(int angle) {
        if (angle > 0) {
            // Right turn
            motorControlBehavior.getLeftMotor().rotate(173, true);  
            motorControlBehavior.getRightMotor().rotate(-173);
        } else {
            // Left turn (negative angle)
            motorControlBehavior.getLeftMotor().rotate(-173, true); 
            motorControlBehavior.getRightMotor().rotate(173);
        }

        stabilize();
    }

    private void stabilize() {
        try {
            Thread.sleep(500); //allows system to settle - testing showed without it would get innaccurate turns
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stopMotorsAndWait() {
        motorControlBehavior.stopMotors(); 
        System.out.println("Motors stopped"); 
        stabilize(); 
    }

    private void startMotors() {
        motorControlBehavior.startMotors(); 
        System.out.println("Motors started"); 
    }

    public void turnRight() {
        System.out.println("Turning right");

        stopMotorsAndWait(); 

        turn(ROTATION_ANGLE); //turn right 

        stopMotorsAndWait(); //stop motors after turn

        startMotors(); 
    }

    public void turnLeft() {
        System.out.println("Turning left");

        stopMotorsAndWait(); 

        turn(-ROTATION_ANGLE); 

        stopMotorsAndWait(); 
        startMotors(); 
    }
}