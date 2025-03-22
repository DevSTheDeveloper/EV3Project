package EV3;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.navigation.MovePilot;

public class SpeedControl implements Runnable {
    private final MovePilot pilot;
    private final EV3ColorSensor colorSensor;
    private boolean suppressed = false;

    public SpeedControl(MovePilot pilot, EV3ColorSensor colorSensor) {
        this.pilot = pilot;
        this.colorSensor = colorSensor;
    }

    private synchronized boolean takeControl() {
        int color = colorSensor.getColorID(); // Use consistent method
        System.out.println("Detected color: " + color); // Debugging output
        return (color == Color.GREEN || color == Color.ORANGE);
    }

    private synchronized void action() {
        suppressed = false;
        double fastSpeed = 20.0;
        double slowSpeed = 5.0;
        double normalSpeed = 10.0;

        while (!suppressed) {
            int color = colorSensor.getColorID(); // Use consistent method
            if (color == Color.GREEN) {
                pilot.setLinearSpeed(fastSpeed);
            } else if (color == Color.ORANGE) {
                pilot.setLinearSpeed(slowSpeed);
            } else {
                pilot.setLinearSpeed(normalSpeed);
            }

            pilot.forward();

            try {
                Thread.sleep(50); // Faster feedback loop
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        pilot.stop();
    }

    public void suppress() {
        suppressed = true;
    }

    @Override
    public void run() {
        while (true) {
            if (takeControl()) {
                action();
            }
            try {
                Thread.sleep(100); // Slightly shorter interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}