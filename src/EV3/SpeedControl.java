package EV3;
//By Faris

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class SpeedControl implements Runnable {
    private final MotorControlBehavior motorControlBehavior;
    private final ColorDetectionBehavior colorDetectionBehavior;
    private boolean suppressed = false;
    private int previousSpeed = null;

    public SpeedControl(MotorControlBehavior motorControlBehavior, ColorDetectionBehavior colorDetectionBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        this.colorDetectionBehavior = colorDetectionBehavior;
    }

    private synchronized boolean takeControl() {
        return colorDetectionBehavior.getDetectedColor().equals("BLUE");
    }

    private synchronized void action() {
        suppressed = false;
        int blueSpeed = 150;

        while (!suppressed) {
            String detectedColor = colorDetectionBehavior.getDetectedColor(); // Get current detected color

            if (detectedColor.equals("BLUE")) {
                if (previousSpeed == null) { // store previous speed only the first time
                    previousSpeed = motorControlBehavior.getSpeed();
                }
                motorControlBehavior.setSpeed(blueSpeed);
            } else if (previousSpeed != null) {
                motorControlBehavior.setSpeed(previousSpeed);
                previousSpeed = null; // reset previousSpeed when reverting
            }

            try {
                Thread.sleep(50); // cpu help
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void suppress() {
        suppressed = true;
    }

    public void run() {
        while (true) {
            if (takeControl()) {
                action();
            }
            try {
                Thread.sleep(50); // cooldown let other tasks run
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}package EV3;
//By Faris

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class SpeedControl implements Runnable {
    private final MotorControlBehavior motorControlBehavior;
    private final ColorDetectionBehavior colorDetectionBehavior;
    private boolean suppressed = false;
    private int previousSpeed = null;

    public SpeedControl(MotorControlBehavior motorControlBehavior, ColorDetectionBehavior colorDetectionBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        this.colorDetectionBehavior = colorDetectionBehavior;
    }

    private synchronized boolean takeControl() {
        return colorDetectionBehavior.getDetectedColor().equals("BLUE");
    }

    private synchronized void action() {
        suppressed = false;
        int blueSpeed = 150;

        while (!suppressed) {
            String detectedColor = colorDetectionBehavior.getDetectedColor(); // Get current detected color

            if (detectedColor.equals("BLUE")) {
                if (previousSpeed == null) { // store previous speed only the first time
                    previousSpeed = motorControlBehavior.getSpeed();
                }
                motorControlBehavior.setSpeed(blueSpeed);
            } else if (previousSpeed != null) {
                motorControlBehavior.setSpeed(previousSpeed);
                previousSpeed = null; // reset previousSpeed when reverting
            }

            try {
                Thread.sleep(50); // cpu help
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void suppress() {
        suppressed = true;
    }

    public void run() {
        while (true) {
            if (takeControl()) {
                action();
            }
            try {
                Thread.sleep(50); // cooldown let other tasks run
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}package EV3;
//By Faris

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class SpeedControl implements Runnable {
    private final MotorControlBehavior motorControlBehavior;
    private final ColorDetectionBehavior colorDetectionBehavior;
    private boolean suppressed = false;
    private int previousSpeed = null;

    public SpeedControl(MotorControlBehavior motorControlBehavior, ColorDetectionBehavior colorDetectionBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        this.colorDetectionBehavior = colorDetectionBehavior;
    }

    private synchronized boolean takeControl() {
        return colorDetectionBehavior.getDetectedColor().equals("BLUE");
    }

    private synchronized void action() {
        suppressed = false;
        int blueSpeed = 150;

        while (!suppressed) {
            String detectedColor = colorDetectionBehavior.getDetectedColor(); // Get current detected color

            if (detectedColor.equals("BLUE")) {
                if (previousSpeed == null) { // store previous speed only the first time
                    previousSpeed = motorControlBehavior.getSpeed();
                }
                motorControlBehavior.setSpeed(blueSpeed);
            } else if (previousSpeed != null) {
                motorControlBehavior.setSpeed(previousSpeed);
                previousSpeed = null; // reset previousSpeed when reverting
            }

            try {
                Thread.sleep(50); // cpu help
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void suppress() {
        suppressed = true;
    }

    public void run() {
        while (true) {
            if (takeControl()) {
                action();
            }
            try {
                Thread.sleep(50); // cooldown let other tasks run
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}