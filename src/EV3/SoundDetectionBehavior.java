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
    private boolean isStopped = false;  // Track whether the robot is stopped or moving
    private boolean isClapDetected = false;  // Ensure clap is not repeatedly detected

    public SoundDetectionBehavior(MotorControlBehavior motorControlBehavior) {
        this.motorControlBehavior = motorControlBehavior;
        soundSensor = new NXTSoundSensor(lejos.hardware.port.SensorPort.S4);
        soundSampleProvider = soundSensor.getDBAMode();
        soundLevel = new float[soundSampleProvider.sampleSize()];
    }

    public void checkSound() {
        soundSampleProvider.fetchSample(soundLevel, 0);
        
        // If clap is detected and not already in the previous detection state
        if (soundLevel[0] > SOUND_THRESHOLD && !isClapDetected) {
            isClapDetected = true;  // Mark that clap was detected
            
            if (!isStopped) {
                // If not stopped, stop the motors
                System.out.println("Clap detected! Stopping motors.");
                motorControlBehavior.stopMotors();
                isStopped = true;  // Mark the robot as stopped
            } else {
                // If stopped, start the motors
                System.out.println("Clap detected! Resuming movement.");
                motorControlBehavior.startMotors();
                isStopped = false;  // Mark the robot as moving
            }

            // Add a small delay before allowing another clap to be detected
            try {
                Thread.sleep(1000);  // 1 second delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset clap detection if the sound level goes below the threshold
        if (soundLevel[0] <= SOUND_THRESHOLD) {
            isClapDetected = false;  // Allow the next clap to be detected
        }
    }
}