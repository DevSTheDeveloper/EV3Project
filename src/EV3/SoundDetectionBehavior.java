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

            //NOTE: THE CODE WILL NOT WORK WITHOUT THIS  
            //IF THERE IS NO GAP THE FLAG WILL NOT SWITCH AND WILL REMAIN IN THE SAME STATE
            try {
                Thread.sleep(1000);  //ensure a 1 second delay between claps
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        //reset clap detection if the sound level goes below the threshold
        if (soundLevel[0] <= SOUND_THRESHOLD) {
            isClapDetected = false;  //reset clap flag
        }
    }
}