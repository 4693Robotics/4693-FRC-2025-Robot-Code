package frc.robot.Subsystems.Manipulator;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public final class ManipulatorConstants {
    /*
     * CAN Ids for corresponding motor
     */
    public static final int kElevatorCANId = 20;
    public static final int kArmCANId = 21;
    public static final int kIntakeLeftCANId = 22;
    public static final int kIntakeRightCANId = 23;

    /*
     * Please don't change these without triple checking what type of motor is connected.
     * Magic Smoke will appear if you have a brushless motor on brushed config.
     */
    public static final MotorType kArmMotorType = MotorType.kBrushless;
    public static final MotorType kElevatorMotorType = MotorType.kBrushless;
    public static final MotorType kIntakeLeftMotorType = MotorType.kBrushless;
    public static final MotorType kIntakeRightMotorType = MotorType.kBrushless;

    /*
     * Please don't change these unless changing the motor type.
     * If current limit is set too high for motor, motor may produce Magic Smoke.
     * Use 50 amps for NEO 1.1.
     * Use 20 amps for NEO 550.
     */
    public static final int kElevatorCurrentLimit = 50;
    public static final int kArmCurrentLimit = 50;
    public static final int kIntakeLeftCurrentLimit = 20;
    public static final int kIntakeRightCurrentLimit = 20;

    /*
     * Used to put motors into brake or coast mode.
     * Brake mode allows the motor to maintain position even while not in use.
     * Coast mode allows the motor to maintain momentum even while not in use.
     */
    public static final IdleMode kElevatorIdleMode = IdleMode.kBrake;
    public static final IdleMode kArmIdleMode = IdleMode.kBrake;
    public static final IdleMode kIntakeLeftIdleMode = IdleMode.kBrake;
    public static final IdleMode kIntakeRightIdleMode = IdleMode.kBrake;

    /*
     * Sets the corresponding motor to invert its direction.
     */
    public static final boolean kElevatorInverted = false;
    public static final boolean kArmInverted = true;
    public static final boolean kIntakeLeftInverted = false;
    public static final boolean kIntakeRightInverted = true;

    public static final double kElevatorConversionFactor = 100;
    public static final double kArmConversionFactor = 1000;

    /* 
     * Sets the feedback sensor to the corresponding motor.
    */
    public static final FeedbackSensor kElevatorFeedbackSensor = FeedbackSensor.kAlternateOrExternalEncoder;
    public static final FeedbackSensor kArmFeedbackSensor = FeedbackSensor.kAbsoluteEncoder;

    /*
     * Sets the PID values for each PID controller for its corresponding motor.
     */
    public static final double kElevatorP = 0.01;
    public static final double kElevatorI = 0;
    public static final double kElevatorD = 0;
    public static final double kArmP = 0.003;
    public static final double kArmI = 0;
    public static final double kArmD = 0;

    /*
     * Sets the output values for each PID controlled motor controller.
     */
    public static final double kElevatorMaxOutput = 0.45;
    public static final double kElevatorMinOutput = -0.2;
    public static final double kArmMaxOutput = 0.65;
    public static final double kArmMinOutput = -0.2;

    /*
     * Set the corresponding PID loop to wrap around the 0 mark
     */
    public static final boolean kElevatorPositionWrapping = false;
    public static final boolean kArmPositionWrapping = false;

    // Elevator Setpoints
    public static final double kBargeElevatorPoint = 840;
    public static final double kA2ElevatorPoint = 420;
    public static final double kL3ElevatorPoint = 875;
    public static final double kA1ElevatorPoint = 105;
    public static final double kL2ElevatorPoint = 390;
    public static final double kL1ElevatorPoint = 100;
    public static final double kCoralInElevatorPoint = 40;
    public static final double kRestElevatorPoint = 0;

    // Arm Setpoints
    public static final double kBargeArmPoint = 640;
    public static final double kA2ArmPoint = 420;
    public static final double kL3ArmPoint = 500;
    public static final double kA1ArmPoint = 500;
    public static final double kL2ArmPoint = 480;
    public static final double kL1ArmPoint = 550;
    public static final double kCoralInArmPoint = 605;
    public static final double kRestArmPoint = 225;

}