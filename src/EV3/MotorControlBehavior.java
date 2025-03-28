package EV3;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MotorControlBehavior {
    private EV3LargeRegulatedMotor leftMotor;
    private EV3LargeRegulatedMotor rightMotor;

    public MotorControlBehavior() {
        // Initialize motors
        leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);
        
        // Set initial speed for both motors
        leftMotor.setSpeed(300);
        rightMotor.setSpeed(300);
    }

    // Start both motors
    public void startMotors() {
        leftMotor.forward();
        rightMotor.forward();
    }

    // Stop both motors
    public void stopMotors() {
        leftMotor.stop(true); // Stop the left motor, allowing for a smooth halt
        rightMotor.stop(true); // Stop the right motor
    }

    // Rotate motors slowly for a smooth turn (approx 180 degrees)
    public void rotateSlowly() {
        // Set a reduced speed for slow movement
        leftMotor.setSpeed(150);
        rightMotor.setSpeed(150);

        // Rotate the motors for a slow turn
        leftMotor.rotate(350, true);  // Rotate 350 degrees (adjust as necessary for a 180-degree turn)
        rightMotor.rotate(-350);      // Opposite direction for right motor

        // Restore speed after turn
        leftMotor.setSpeed(300);
        rightMotor.setSpeed(300);
    }

    // Turn the robot left by rotating the motors
    public void turnLeft() {
        leftMotor.rotate(-175);  // Rotate left motor backwards
        rightMotor.rotate(175);  // Rotate right motor forward
    }

    // Turn the robot right by rotating the motors
    public void turnRight() {
        leftMotor.rotate(175);   // Rotate left motor forward
        rightMotor.rotate(-175); // Rotate right motor backwards
    }

    // Additional methods for more control:
    
    // Move forward for a specified distance
    public void moveForward(int degrees) {
        leftMotor.rotate(degrees, true);
        rightMotor.rotate(degrees);
    }

    // Move backward for a specified distance
    public void moveBackward(int degrees) {
        leftMotor.rotate(-degrees, true);
        rightMotor.rotate(-degrees);
    }
    
    // Set the speed of both motors
    public void setSpeed(int speed) {
        leftMotor.setSpeed(speed);
        rightMotor.setSpeed(speed);
    }

    // Get the left motor
    public EV3LargeRegulatedMotor getLeftMotor() {
        return leftMotor;
    }

    // Get the right motor
    public EV3LargeRegulatedMotor getRightMotor() {
        return rightMotor;
    }
}