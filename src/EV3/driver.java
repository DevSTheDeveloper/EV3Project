package EV3;
//By Devannsh - This is the main class which calls the other behaviours
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;

public class driver {

    private MotorControlBehavior motorControlBehavior; //used instead of pilot
    private SoundDetectionBehavior soundDetectionBehavior;
    private ObstacleDetectionBehavior obstacleDetectionBehavior;
    private ColorDetectionBehavior colorDetectionBehavior;
    private TurnBehavior turnBehavior;

    public driver() { //initialise behaviours 
        motorControlBehavior = new MotorControlBehavior();
        soundDetectionBehavior = new SoundDetectionBehavior(motorControlBehavior);
        obstacleDetectionBehavior = new ObstacleDetectionBehavior(motorControlBehavior);
        colorDetectionBehavior = new ColorDetectionBehavior();
        turnBehavior = new TurnBehavior(motorControlBehavior, colorDetectionBehavior);
    }

    public void driverLoop() {
        displayStartScreen();
        //wait for the Enter button to be pressed
        waitForEnterPress();
        //proceed to the main loop after enter is pressed
        LCD.clear();
        motorControlBehavior.startMotors();

        //Main driving loop
        while (true) {
            soundDetectionBehavior.checkSound();
            obstacleDetectionBehavior.checkObstacle();
            colorDetectionBehavior.checkColor();
            
            //retrieves the currently detected colour and then calls the function it is linked to
            //IE - Green --> TurnRight();
            String detectedColor = colorDetectionBehavior.getDetectedColor();
            handleDetectedColor(detectedColor);

            if (Button.ESCAPE.isDown()) {
                System.out.println("Ending Processes");
                break;
            }
        }

        colorDetectionBehavior.close();
    }

    private void displayStartScreen() { //show at start
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
            turnBehavior.turnRight();
        } else if (detectedColor.equals("ORANGE")) {
            turnBehavior.turnLeft();
        } else {
            //do nothing for any other colours
        }
    }

    public static void main(String[] args) { 
        driver driver = new driver();
        driver.driverLoop();
    }
}