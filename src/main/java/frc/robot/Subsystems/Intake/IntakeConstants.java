package frc.robot.Subsystems.Intake;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class IntakeConstants {
    /*
     * CAN Ids for corresponding motor
     */
    public static final int kArmCANId = 10;
    public static final int kIntakeCANId = 11;

    /*
     * Please don't change these without triple checking what type of motor is connected.
     * Magic Smoke will appear if you have a brushless motor on brushed config.
     */
    public static final MotorType kArmMotorType = MotorType.kBrushless;
    public static final MotorType kIntakeMotorType = MotorType.kBrushless;

    /*
     * Used to put motors into brake or coast mode.
     * Brake mode allows the motor to maintain position even while not in use.
     * Coast mode allows the motor to maintain momentum even while not in use.
     */
    public static final IdleMode kArmIdleMode = IdleMode.kBrake;
    public static final IdleMode kIntakeIdleMode = IdleMode.kBrake;

    /*
     * Please don't change these unless changing the motor type.
     * If current limit is set too high for motor, motor may produce Magic Smoke.
     * Use 50 amps for NEO 1.1.
     * Use 20 amps for NEO 550.
     */
    public static final int kArmCurrentLimit = 50;
    public static final int kIntakeCurrentLimit = 20;

    /*
     * Sets the corresponding motor to invert its direction.
     */
    public static final boolean kArmInverted = false;
    public static final boolean kIntakeInverted = false;
}
