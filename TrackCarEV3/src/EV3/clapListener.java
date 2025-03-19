package EV3;
//TO DO: CALL THE TO BE MADE MOTORSTATUS AND ENABLE/DISABLE THE MOTORS THIS WAY

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;

public class clapListener {
//Use InterruptedExceptions here as we deal with the threads (listening for clap)
    public static void main(String[] args) throws InterruptedException {
        NXTSoundSensor soundSensor = new NXTSoundSensor(SensorPort.S1);
        float clapThreshold = 0.5f;
        int clapTimeGap = 500; //in milliseconds 

        SampleProvider clapFilter = new ClapFilter(soundSensor, clapThreshold, clapTimeGap);
        float[] sample = new float[clapFilter.sampleSize()];

        LCD.clear();
        LCD.drawString("Listening for claps...", 0, 0);

        while (true) { //listening for the clap
            clapFilter.fetchSample(sample, 0);
            if (sample[0] == 1.0f) { 
                LCD.clear();
                LCD.drawString("CLAP DETECTED!", 0, 2);
                Thread.sleep(500); //prevent system overload for half a second 
                LCD.clear();
                LCD.drawString("Listening for claps...", 0, 0);
            }

            Thread.sleep(100);
        }
    }
}
