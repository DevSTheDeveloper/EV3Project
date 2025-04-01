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
    
  //deprecitated - was used to perform a 180 degree turn
    public void rotateSlowlyOneEighty() { 
    	//says rotate 350 as this actually translates to 180* turn - we do not use pilot so it needs to be 
    	//manually modified till it rotates exactly 180* - We found 350 deg translates exactly to 180
        leftMotor.setSpeed(150);
        rightMotor.setSpeed(150);

        leftMotor.rotate(350, true);  
        rightMotor.rotate(-350);      
        leftMotor.setSpeed(300);
        rightMotor.setSpeed(300);
    }
    
//Used by Turning behaviour - By Patrick
    public void turnLeft() {
        leftMotor.rotate(-175);  
        rightMotor.rotate(175);  
    }

    public void turnRight() {
        leftMotor.rotate(175);   
        rightMotor.rotate(-175); 
    }

    public void moveForward(int degrees) {
        leftMotor.rotate(degrees, true);
        rightMotor.rotate(degrees);
    }

    public void moveBackward(int degrees) {
        leftMotor.rotate(-degrees, true);
        rightMotor.rotate(-degrees);
    }
    
    //By Faris
    public void setSpeed(int speed) {
        leftMotor.setSpeed(speed);
        rightMotor.setSpeed(speed);
    }

    public EV3LargeRegulatedMotor getLeftMotor() {
        return leftMotor;
    }

    public EV3LargeRegulatedMotor getRightMotor() {
        return rightMotor;
    }
}