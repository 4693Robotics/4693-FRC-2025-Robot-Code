package frc.Utils;

public class RobotUtils {

    public class BooleanStateChange {
        private boolean currentState = false; // current state of the boolean
        private boolean previousState = false; // previous state of the boolean

        private Runnable onTrue;
        private Runnable onFalse;

        public BooleanStateChange(Runnable onTrue, Runnable onFalse) {
            this.onTrue = onTrue;
            this.onFalse = onFalse;
        }
    
        // Method to check state change and run the passed commands (Runnables)
        public void checkStateChange(boolean newState) {
            // Check if the state has changed
            if (currentState != newState) {
                if (newState) {
                    // Run the command when boolean changes to true
                    onTrue.run();
                } else {
                    // Run the command when boolean changes to false
                    onFalse.run();
                }
    
                // Update the state
                previousState = currentState;
                currentState = newState;
            }
        }
    }

    public class JoystickUtils {
    
        public static double joystickSlider(double joystickAxis, double currentSetPoint) {
            
            double newDouble = currentSetPoint + joystickAxis * 0.01;
    
            if (newDouble > 1) {
                newDouble = 1;
            } else if (newDouble < -1) {
                newDouble = -1;
            }
            return newDouble;
        }
    
        public static double joystickSlider(double joystickAxis, double currentSetPoint, double maxOutput, double minOutput) {
       
            double newDouble = currentSetPoint + joystickAxis * 0.1;
    
            if (newDouble > maxOutput) {
                newDouble = maxOutput;
            } else if (newDouble < minOutput) {
                newDouble = minOutput;
            }
            return newDouble;
        }
    }
}
