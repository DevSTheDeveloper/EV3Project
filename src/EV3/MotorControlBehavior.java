package EV3;
//By Dev - This class acts like the pilot class 
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MotorControlBehavior {
    private EV3LargeRegulatedMotor leftMotor;
    private EV3LargeRegulatedMotor rightMotor;

    public MotorControlBehavior() {
        leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);
        
        leftMotor.setSpeed(300); //inital speeds - perfect speed to read colours
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

    //By Faris from the original SpeedControl Class (may not be used in final)
    public void rotateSlowly() {
        leftMotor.setSpeed(150);
        rightMotor.setSpeed(150);

        leftMotor.rotate(350, true);  
        rightMotor.rotate(-350);      
        leftMotor.setSpeed(300);
        rightMotor.setSpeed(300);
    }

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