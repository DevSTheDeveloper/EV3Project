package EV3;
// by devannsh and Arshiya tested by Patrick

import lejos.hardware.sensor.EV3UltrasonicSensor;

public class ObstacleDetectionBehavior {
    private EV3UltrasonicSensor ultraSensor;
    private MotorControlBehavior motorControlBehavior;

    public ObstacleDetectionBehavior(MotorControlBehavior motorControlBehavior) {
        ultraSensor = new EV3UltrasonicSensor(lejos.hardware.port.SensorPort.S2);
        this.motorControlBehavior = motorControlBehavior;
    }

    public void checkObstacle() {
        float[] distance = new float[ultraSensor.getDistanceMode().sampleSize()];
        ultraSensor.getDistanceMode().fetchSample(distance, 0);
        if (distance[0] < 0.2) {
            motorControlBehavior.stopMotors();
            //System.out.println("Obstacle detected. Stopping.");
            try {
                Thread.sleep(1000); //wait before checking again - testing without this caused robot to creep forward 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            motorControlBehavior.startMotors();
        }
    }
}