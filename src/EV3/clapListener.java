package EV3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class clapListener implements Behavior, Runnable {
    private NXTSoundSensor mic;
    private MovePilot pilot;

    public clapListener(NXTSoundSensor mic, MovePilot pilot) {
        this.mic = mic;
        this.pilot = pilot;
    }

    @Override
    public void run() {
        float[] sample = new float[1];
        while (true) {
            mic.fetchSample(sample, 0);
            if (sample[0] > 0.35f) {
                LCD.clear();
                LCD.drawString("CLAP DETECTED!", 0, 2);
                try {
                    Thread.sleep(500); // Wait for half a second to avoid flooding
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);  // Add a slight delay before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LCD.clear();
        }
    }

    @Override
    public boolean takeControl() {
        return false;  // Modify if you need to control the behavior in subsumption
    }

    @Override
    public void action() {
        // Perform the action when the behavior is activated (if needed)
    }

    @Override
    public void suppress() {
        // Handle suppression of the behavior
    }
}