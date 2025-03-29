package EV3;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;
//by devannsh 
public class SoundDetectionBehavior {
    private NXTSoundSensor soundSensor;
    private SampleProvider soundSampleProvider;
    private float[] soundLevel;
    private final float SOUND_THRESHOLD = 0.5f;
    private MotorControlBehavior motorControlBehavior;
    private boolean isStopped = false;  //flag to track whether the robot is stopped or moving
    private boolean isClapDetected = false;  //flag to ensure clap is not repeatedly detected

    public SoundDetectionBehavior(MotorControlBehavior motorControlBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        soundSensor = new NXTSoundSensor(lejos.hardware.port.SensorPort.S4);
        soundSampleProvider = soundSensor.getDBAMode();
        soundLevel = new float[soundSampleProvider.sampleSize()];
    }

    public void checkSound() {
        soundSampleProvider.fetchSample(soundLevel, 0);
        
        if (soundLevel[0] > SOUND_THRESHOLD && !isClapDetected) {
            isClapDetected = true;  //flag that clap was detected
            
            if (!isStopped) {
                System.out.println("Clap detected! Stopping motors.");
                motorControlBehavior.stopMotors();
                isStopped = true;  //mark the robot as stopped
            } else {
                System.out.println("Clap detected! Resuming movement.");
                motorControlBehavior.startMotors();
                isStopped = false; //mark the robot as moving
            }
        }

        //reset clap flag if the sound level goes below the threshold
        if (soundLevel[0] <= SOUND_THRESHOLD) {
            isClapDetected = false;  //allow the next clap to be detected
        }
    }
}