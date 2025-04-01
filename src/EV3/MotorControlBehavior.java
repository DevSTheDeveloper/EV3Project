package EV3;
//By Dev - This class acts like the pilot class 
//While some functions are associated with different behaviours (ie turning or speed control), 
//they have been made independent for easier debugging 
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MotorControlBehavior {
    private EV3LargeRegulatedMotor leftMotor;
    private EV3LargeRegulatedMotor rightMotor;

    public MotorControlBehavior() {
        leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);
//initialise speeds - if modified do not exceed 300 due to readability issues with the sensor and the colour
        leftMotor.setSpeed(300); 
        rightMotor.setSpeed(300);
    }
 
//Used in multiple classes such as SoundDetectionBehaviour or turning

    public void startMotors() {
        leftMotor.forward();
        rightMotor.forward();
    }

    public void stopMotors() {
        leftMotor.stop(true); 
        rightMotor.stop(true); 
    }
    
    //functions used below can be used for any motor functions as required for other classes or testing

    
    public void setSpeed(int speed) {
        leftMotor.setSpeed(speed);
        rightMotor.setSpeed(speed);
    }
    
    

    public EV3LargeRegulatedMotor getLeftMotor() {
        return leftMotor;
    }
    
    public int getSpeed() {
        return leftMotor.getSpeed(); //assuming both motors have the same speed - only 
    }

    public EV3LargeRegulatedMotor getRightMotor() {
        return rightMotor;
    }
}