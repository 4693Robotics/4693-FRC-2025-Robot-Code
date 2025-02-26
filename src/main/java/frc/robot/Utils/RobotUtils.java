package frc.robot.Utils;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.LimelightConstants;

public class RobotUtils {

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

    public class limelightUtils {

        public static double aimLimelightRot() {

            double targetingAngularVelocity = LimelightHelpers.getTX("") * LimelightConstants.kP;
            
            targetingAngularVelocity *= DriveConstants.kMaxAngularSpeed;

            targetingAngularVelocity *= -1;

            return targetingAngularVelocity;
        }

        public static double aimLimelightDistance() {

            double targetingForwardVelocity = LimelightHelpers.getTY("") * LimelightConstants.kP;

            targetingForwardVelocity *= DriveConstants.kMaxSpeedMetersPerSecond;

            targetingForwardVelocity *= -1;

            return targetingForwardVelocity;
        }
    }
}
