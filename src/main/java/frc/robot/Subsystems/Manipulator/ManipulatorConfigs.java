package frc.robot.Subsystems.Manipulator;

import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Subsystems.Manipulator.ManipulatorConstants.*;

public final class ManipulatorConfigs {
    /*
     * Configurations for all spark max motor controllers in Manipulator Subsystem.
     */
    public static final SparkMaxConfig elevatorConfig = new SparkMaxConfig();
    public static final SparkMaxConfig armConfig = new SparkMaxConfig();
    public static final SparkMaxConfig intakeLeftConfig = new SparkMaxConfig();
    public static final SparkMaxConfig intakeRightConfig = new SparkMaxConfig();

    /*
     * The variables in the class ManipulatorConstants have been statically imported to
     * reduce the verbosity of the code.
     */
    static {
        elevatorConfig
            .idleMode(kElevatorIdleMode)
            .smartCurrentLimit(kElevatorCurrentLimit)
            .inverted(kElevatorInverted);
        elevatorConfig.alternateEncoder
            .positionConversionFactor(kElevatorConversionFactor);
        elevatorConfig.closedLoop
            .feedbackSensor(kElevatorFeedbackSensor)
            .pid(kElevatorP, kElevatorI, kElevatorD)
            .outputRange(kElevatorMinOutput, kElevatorMaxOutput)
            .positionWrappingEnabled(kElevatorPositionWrapping);

        armConfig
            .idleMode(kArmIdleMode)
            .smartCurrentLimit(kArmCurrentLimit)
            .inverted(kArmInverted);
        armConfig.absoluteEncoder
            .positionConversionFactor(kArmConversionFactor);
        armConfig.closedLoop
            .feedbackSensor(kArmFeedbackSensor)
            .pid(kArmP, kArmI, kArmD)
            .outputRange(kArmMinOutput, kArmMaxOutput)
            .positionWrappingEnabled(kArmPositionWrapping);

        intakeLeftConfig
            .idleMode(kIntakeLeftIdleMode)
            .smartCurrentLimit(kIntakeLeftCurrentLimit)
            .inverted(false);

        intakeRightConfig
            .idleMode(kIntakeRightIdleMode)
            .smartCurrentLimit(kIntakeRightCurrentLimit)
            .inverted(kIntakeRightInverted);
    }
}
