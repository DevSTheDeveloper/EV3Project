package EV3;
//by Devannsh Sehgal

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
            //check for sound and obstacle detection
            soundDetectionBehavior.checkSound();
            obstacleDetectionBehavior.checkObstacle();

            if (Button.ESCAPE.isDown()) { //exit
                System.out.println("Program Terminated");
                break; 
            }
        }
    }

    public static void main(String[] args) {
        driver driver = new driver();
        driver.driverLoop();
    }
}