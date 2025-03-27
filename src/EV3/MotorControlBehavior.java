package EV3;
// by devannsh

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MotorControlBehavior {
    private EV3LargeRegulatedMotor leftMotor;
    private EV3LargeRegulatedMotor rightMotor;

    public MotorControlBehavior() {
        leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);
        leftMotor.setSpeed(300);
        rightMotor.setSpeed(300);
    }

    public void startMotors() {
        leftMotor.forward();
        rightMotor.forward();
    }

    public void stopMotors() {
        leftMotor.stop(true);
        rightMotor.stop(true);
    }

    public void rotateSlowly() {
        leftMotor.setSpeed(150);  //reduced speed for a smooth, slow turn
        rightMotor.setSpeed(150);
        leftMotor.rotate(350, true);  //rotate 350 (approx 180-degree turn after testing)
        rightMotor.rotate(-350);      //opposite direction for right motor
        leftMotor.setSpeed(300);
        rightMotor.setSpeed(300);
    }
}