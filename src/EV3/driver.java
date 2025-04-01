package EV3;
//By Devannsh - This is the main class which calls the other behaviours

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class driver {

    private MotorControlBehavior motorControlBehavior; // Used instead of pilot
    private SoundDetectionBehavior soundDetectionBehavior;
    private ObstacleDetectionBehavior obstacleDetectionBehavior;
    private ColorDetectionBehavior colorDetectionBehavior;
    private TurnBehavior turnBehavior;
    private SpeedControl speedControl; //added SpeedControl behavior

    public driver() {
        motorControlBehavior = new MotorControlBehavior();
        soundDetectionBehavior = new SoundDetectionBehavior(motorControlBehavior);
        obstacleDetectionBehavior = new ObstacleDetectionBehavior(motorControlBehavior);
        colorDetectionBehavior = new ColorDetectionBehavior();
        turnBehavior = new TurnBehavior(motorControlBehavior, colorDetectionBehavior);

        speedControl = new SpeedControl(motorControlBehavior, colorDetectionBehavior);
    }

    public void driverLoop() {
        displayStartScreen();
        waitForEnterPress();
        LCD.clear();
        motorControlBehavior.startMotors();

        while (true) {
            soundDetectionBehavior.checkSound();
            obstacleDetectionBehavior.checkObstacle();
            colorDetectionBehavior.checkColor();
            
            String detectedColor = colorDetectionBehavior.getDetectedColor();
            handleDetectedColor(detectedColor);

            if (Button.ESCAPE.isDown()) {
                System.out.println("Ending Processes");
                break;
            }
        }

        colorDetectionBehavior.close();
    }

    private void displayStartScreen() {
        LCD.clear();
        LCD.drawString("Track Car Project", 0, 2);  
        LCD.drawString("By Dev, Arshiya", 0, 3);
        LCD.drawString("Patrick & Faris", 0, 4);
        LCD.drawString("Press Enter", 0, 5);  
    }

    private void waitForEnterPress() {
        while (!Button.ENTER.isDown()) {
            //wait here until the Enter button is pressed
        }
    }

    private void handleDetectedColor(String detectedColor) {
        if (detectedColor.equals("GREEN")) {
            turnBehavior.turnRight();  //from turn behaviour for L and R
        } else if (detectedColor.equals("ORANGE")) {
            turnBehavior.turnLeft(); 
        } else if (detectedColor.equals("BLUE")) {
            //if Blue is detected, speed up (handled by SpeedControl)
            System.out.println("Blue detected - Speeding up to 150");
            speedControl.run(); //call the run method from SpeedControl 
        }
    }

    public static void main(String[] args) { 
        driver driver = new driver();
        driver.driverLoop(); //init new driver obj and call main loop
    }
}