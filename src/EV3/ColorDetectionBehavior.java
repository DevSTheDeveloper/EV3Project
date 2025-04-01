package EV3;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import java.util.HashMap;
//By Dev and Arshiya
public class ColorDetectionBehavior {
    private EV3ColorSensor colorSensor;
    private SampleProvider colorProvider;
    private float[] sample;
    private HashMap<String, float[]> colorMap;
    private String detectedColor = "UNKNOWN";  //store currently detected colour

    public ColorDetectionBehavior() {
        colorSensor = new EV3ColorSensor(SensorPort.S1);
        colorProvider = colorSensor.getRGBMode();
        sample = new float[colorProvider.sampleSize()];
        //hashmap we created from manually reading sensor data from console 
        colorMap = new HashMap<>();
        colorMap.put("GREEN", new float[]{0.03f, 0.15f, 0.04f});
        colorMap.put("ORANGE", new float[]{0.21f, 0.07f, 0.03f});
        colorMap.put("BLACK", new float[]{0.01f, 0.01f, 0.02f}); 
        //colorMap.put("BLUE", new float[]{0.0f, 0.0f, 1.0f});  //TEMPORARILY DISABLED - REFER TO SPEED CONTROL CLASS FOR MORE INFO
    }

    
    public void checkColor() { 
        colorProvider.fetchSample(sample, 0);
        float r = sample[0];
        float g = sample[1];
        float b = sample[2]; //this was used for testing - Read exact sensor data to add to hashmap

        detectedColor = detectColor(r, g, b);
    }
   

    private String detectColor(float r, float g, float b) {
        float tolerance = 0.05f; //tolerance is 0.05 to account for different lighting conditions 
        for (String color : colorMap.keySet()) {
            float[] target = colorMap.get(color);
            if (Math.abs(r - target[0]) <= tolerance &&
                Math.abs(g - target[1]) <= tolerance &&
                Math.abs(b - target[2]) <= tolerance) {
                return color;
            }
        }
        return "UNKNOWN"; //System ignores anything that is not in the hashmap -  else continue on
    }
    

    public String getDetectedColor() {
        return detectedColor;
    }

    public void close() {
        colorSensor.close();
    }
}