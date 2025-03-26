package EV3;
//By Devannsh Sehgal - All the behaviours can use this class to read real time sensor data
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.SensorPort;

public class ColorSensorReader {
    private EV3ColorSensor colorSensor;
    private SampleProvider colorSampleProvider;
    private float[] colorSample;

    public ColorSensorReader() {
        colorSensor = new EV3ColorSensor(SensorPort.S1); 
        colorSampleProvider = colorSensor.getRGBMode(); 
        colorSample = new float[colorSampleProvider.sampleSize()];
    }

    public float[] getColorValues() {
        colorSampleProvider.fetchSample(colorSample, 0);
        return colorSample; 
    }
}