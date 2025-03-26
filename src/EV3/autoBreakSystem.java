package EV3;
//By Devannsh and Arshiya 

/*
public class autoBreakSystem {

    private driver driver;
    private boolean objectDetected = false;

    public autoBreakSystem(driver driver) {
        this.driver = driver;
    }

    //monitors the distance and determines if an object is detected
    public void monitorDistance() {
        // Fetch the distance from the ultrasonic sensor
        float[] sample = driver.getSample();  // Use getter method for sample
        driver.getDistanceMode().fetchSample(sample, 0);
        float distance = sample[0] * 100;  // Convert to cm

        System.out.println("Distance: " + distance + " cm");

        // Check if an object is detected within 10 cm
        if (distance < 10) {
            if (!objectDetected) {
                System.out.println("Object detected! Stopping motors...");
                driver.stopMotors();  // Stop motors if object is close
                objectDetected = true;  // Set objectDetected flag to true
            }
        } else {
            if (objectDetected) {
                objectDetected = false;  // Reset the flag when no object is detected
                driver.restartMotors();  // Restart motors when object is no longer detected
            }
        }
    }

    // Getter method for objectDetected flag
    public boolean isObjectDetected() {
        return objectDetected;
    }
}

*/