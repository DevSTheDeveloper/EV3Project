package EV3;

import lejos.robotics.SampleProvider;

public class ClapListener implements SampleProvider {
    private final float threshold;
    private final int timeGap;
    private final SampleProvider ss;
    private long lastHeard;

    public ClapListener(SampleProvider ss, float level, int gap) {
        this.timeGap = gap;
        this.ss = ss;
        this.threshold = level;
        lastHeard = -2 * timeGap;
    }

    @Override
    public void fetchSample(float[] level, int index) {
        level[index] = 0.0f;
        long now = System.currentTimeMillis();
        if (now - lastHeard > timeGap) {
            ss.fetchSample(level, index);
            if (level[index] >= threshold) {
                level[index] = 1.0f;
                lastHeard = now;
            }
        }
    }

    @Override
    public int sampleSize() {
        return 1;
    }
}