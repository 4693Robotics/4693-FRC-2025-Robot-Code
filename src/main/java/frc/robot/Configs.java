package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants.ModuleConstants;

public final class Configs {

        public static final class MAXSwerveModule {
                public static final SparkMaxConfig drivingConfig = new SparkMaxConfig();
                public static final SparkMaxConfig turningConfig = new SparkMaxConfig();

                static{
                // Use module constants to calculate conversion factors and feed forward gain.
                double drivingFactor = ModuleConstants.kWheelDiameterMeters * Math.PI
                        / ModuleConstants.kDrivingMotorReduction;
                double turningFactor = 2 * Math.PI;
                double drivingVelocityFeedForward = 1 / ModuleConstants.kDriveWheelFreeSpeedRps;

                drivingConfig
                        .idleMode(IdleMode.kBrake)
                        .smartCurrentLimit(50);
                drivingConfig.encoder
                        .positionConversionFactor(drivingFactor) // meters
                        .velocityConversionFactor(drivingFactor / 60.0); // meters per second
                drivingConfig.closedLoop
                        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                        // These are example gains you may need to them for your own robot!
                        .pid(0.04, 0, 0)
                        .velocityFF(drivingVelocityFeedForward)
                        .outputRange(-1, 1);

                turningConfig
                        .idleMode(IdleMode.kBrake)
                        .smartCurrentLimit(20);
                turningConfig.absoluteEncoder
                        // Invert the turning encoder, since the output shaft rotates in the opposite
                        // direction of the steering motor in the MAXSwerve Module.
                        .inverted(true)
                        .positionConversionFactor(turningFactor) // radians
                        .velocityConversionFactor(turningFactor / 60.0); // radians per second
                turningConfig.closedLoop
                        .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                        // These are example gains you may need to them for your own robot!
                        .pid(1, 0, 0)
                        .outputRange(-1, 1)
                        // Enable PID wrap around for the turning motor. This will allow the PID
                        // controller to go through 0 to get to the setpoint i.e. going from 350 degrees
                        // to 10 degrees will go through 0 rather than the other direction which is a
                        // longer route.
                        .positionWrappingEnabled(true)
                        .positionWrappingInputRange(0, turningFactor);        
                }
        }

        public static final class AlgaeSubsystem {
                public static final SparkMaxConfig algaeArmConfig = new SparkMaxConfig();
                public static final SparkMaxConfig algaeIntakeConfig = new SparkMaxConfig();

                static {

                algaeArmConfig
                        .idleMode(IdleMode.kBrake)
                        .smartCurrentLimit(50)
                        .inverted(false);
                
                /*algaeArmConfig.alternateEncoder
                        .countsPerRevolution(1);
                algaeArmConfig.closedLoop
                        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                        .pid(
                                Constants.AlgaeSubsystem.kAlgaeArmP,
                                Constants.AlgaeSubsystem.kAlgaeArmI,
                                Constants.AlgaeSubsystem.kAlgaeArmD)
                        .outputRange(-0.2, 0.2);
                        */
                algaeIntakeConfig
                        .idleMode(IdleMode.kBrake)
                        .smartCurrentLimit(20);
                }
        } 

        public static final class CoralIntakeSubsystem {
                public static final SparkMaxConfig nuckleMotorConfig = new SparkMaxConfig();

                static {
                        nuckleMotorConfig
                                .idleMode(IdleMode.kBrake)
                                .smartCurrentLimit(20)
                                .inverted(true);
                        
                        nuckleMotorConfig.absoluteEncoder
                                .positionConversionFactor(1000)
                                .inverted(true);

                        nuckleMotorConfig.closedLoop
                                .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                                .pid(0.2, 0, 0)
                                .outputRange( 0, 0.3)
                                .positionWrappingEnabled(true);
                }
        }

        public static final class ElevatorSubsystem {
                public static final SparkMaxConfig elevatorMotorConfig = new SparkMaxConfig();

                static {
                        elevatorMotorConfig
                                .idleMode(IdleMode.kBrake)
                                .smartCurrentLimit(50)
                                .inverted(true);
                        
                        elevatorMotorConfig.closedLoop
                                .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                                .pid(0.1, 0, 0);
                }
        }
    
}