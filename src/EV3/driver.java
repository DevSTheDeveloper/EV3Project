package EV3;
//by devannsh
import lejos.hardware.Button;

public class driver {
    private MotorControlBehavior motorControlBehavior;
    private SoundDetectionBehavior soundDetectionBehavior;
    private ObstacleDetectionBehavior obstacleDetectionBehavior;

    public driver() {
        motorControlBehavior = new MotorControlBehavior();
        soundDetectionBehavior = new SoundDetectionBehavior(motorControlBehavior);
        obstacleDetectionBehavior = new ObstacleDetectionBehavior(motorControlBehavior);
    }

    public void driverLoop() {
        motorControlBehavior.startMotors();

        while (true) {
            soundDetectionBehavior.checkSound();
            obstacleDetectionBehavior.checkObstacle();
            if (Button.ESCAPE.isDown()) { //keep checking if exit button has been pressed
                System.out.println("Escape pressed. Exiting program...");
                break; 
            }
        } //I have closed the motors in the individual behaviour classes
    }

    public static void main(String[] args) {
        driver driver = new driver();
        driver.driverLoop();
    }
}