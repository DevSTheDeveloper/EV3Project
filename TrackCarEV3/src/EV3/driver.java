package EV3;

import lejos.hardware.port.SensorPort;
import lejos.robotics.chassis.*;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.hardware.port.MotorPort;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.SampleProvider;

public class driver {
    final static float ANGULAR_SPEED = 100;
    final static float AXLE_LENGTH = 50;
    final static float WHEEL_DIAMETER = 51;
    final static float LINEAR_SPEED = 120;

    public static void main(String[] args) {
        MovePilot pilot = createMovePilot();

        EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S4);
        SampleProvider distance = sensor.getMode("Distance");

        EV3ColorSensor lightSensor = new EV3ColorSensor(SensorPort.S1);

        float[] sample = new float[distance.sampleSize()];
        distance.fetchSample(sample, 0);
        System.out.println("Distance: " + sample[0]); //cfor Debugging and ensuring sensor read
    }

    private static MovePilot createMovePilot() {
        EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
        EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
        Wheel wheel1 = WheeledChassis.modelWheel(leftMotor, WHEEL_DIAMETER).offset(-6);
        Wheel wheel2 = WheeledChassis.modelWheel(rightMotor, WHEEL_DIAMETER).offset(6);
        return new MovePilot(new WheeledChassis(new Wheel[]{wheel1, wheel2}, WheeledChassis.TYPE_DIFFERENTIAL));
    }
}