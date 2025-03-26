package EV3;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;

public class SoundDetectionBehavior {
    private NXTSoundSensor soundSensor;
    private SampleProvider soundSampleProvider;
    private float[] soundLevel;
    private final float SOUND_THRESHOLD = 0.5f;
    private MotorControlBehavior motorControlBehavior;

    public SoundDetectionBehavior(MotorControlBehavior motorControlBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        soundSensor = new NXTSoundSensor(lejos.hardware.port.SensorPort.S4);
        soundSampleProvider = soundSensor.getDBAMode();
        soundLevel = new float[soundSampleProvider.sampleSize()];
    }

    public void checkSound() {
        soundSampleProvider.fetchSample(soundLevel, 0);
        if (soundLevel[0] > SOUND_THRESHOLD) {
            System.out.println("Clap detected! Stopping motors.");
            motorControlBehavior.stopMotors();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Rotating slowly...");
            motorControlBehavior.rotateSlowly();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Resuming movement.");
            motorControlBehavior.startMotors();
        }
    }
}