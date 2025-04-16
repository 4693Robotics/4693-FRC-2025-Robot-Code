package frc.robot.Subsystems.Intake;

import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Subsystems.Intake.IntakeConstants.*;
import static frc.robot.Subsystems.Manipulator.ManipulatorConstants.kElevatorInverted;

public class IntakeConfigs {
    /*
     * Configurations for all spark max motor controllers in Intake Subsystem.
     */
    public static final SparkMaxConfig armConfig = new SparkMaxConfig();
    public static final SparkMaxConfig intakeConfig = new SparkMaxConfig();

    /*
     * The variables in the class IntakeConstants have been statically imported to
     * reduce the verbosity of the code.
     */
    static {
        armConfig
            .idleMode(kArmIdleMode)
            .smartCurrentLimit(kArmCurrentLimit)
            .inverted(kArmInverted);
        
        intakeConfig
            .idleMode(kIntakeIdleMode)
            .smartCurrentLimit(kIntakeCurrentLimit)
            .inverted(kElevatorInverted);
    }
}
