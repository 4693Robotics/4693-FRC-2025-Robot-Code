package frc.Utils;

public class RobotUtils {

    public static class BooleanStateChange {
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
}
