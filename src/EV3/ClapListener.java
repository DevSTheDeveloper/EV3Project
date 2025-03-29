package EV3;
//Uses the ClapFilter Class by Dave Cohen (cyclingProfessor on github 

import lejos.robotics.SampleProvider;

public class ClapListener implements SampleProvider {
    private final float threshold;
    private final int timeGap;
    private final SampleProvider ss; //the sound sensor's SampleProvider to fetch the sound level
    private long lastHeard; //stores the time (in milliseconds) when the last clap was detected

    public ClapListener(SampleProvider ss, float level, int gap) {
        this.timeGap = gap;
        this.ss = ss;
        this.threshold = level;
        lastHeard = -2 * timeGap;
    }

    public void fetchSample(float[] level, int index) {
        level[index] = 0.0f; //resets the sound level
        long now = System.currentTimeMillis();
        if (now - lastHeard > timeGap) {
            ss.fetchSample(level, index);
            if (level[index] >= threshold) {
                level[index] = 1.0f;
                lastHeard = now;
            }
        }
    }

    public int sampleSize() {
        return 1;
    }
}