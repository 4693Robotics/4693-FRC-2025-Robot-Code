package frc.robot.Subsystems.Drive.TestDrive;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class DriveConstants {
    /*
     * CAN Ids for corresponding motor
     */
    public static final int kFLDriveCANId = 1;
    public static final int kFLTurnCANId = 2;
    public static final int kFRDriveCANId = 3; 
    public static final int kFRTurnCANId = 4;
    public static final int kRRDriveCANId = 5;
    public static final int kRRTurnCANId = 6;
    public static final int kRLDriveCANId = 7;
    public static final int kRLTurnCANId = 8;

    /*
     * Please don't change these without triple checking what type of motor is connected.
     * Magic Smoke will appear if you have a brushless motor on brushed config.
     */
    public static final MotorType kDriveMotorType = MotorType.kBrushless;
    public static final MotorType kTurnMotorType = MotorType.kBrushless;

    /*
     * Used to put motors into brake or coast mode.
     * Brake mode allows the motor to maintain position even while not in use.
     * Coast mode allows the motor to maintain momentum even while not in use.
     */
    public static final IdleMode kDriveIdleMode = IdleMode.kBrake;
    public static final IdleMode kTurnIdleMode = IdleMode.kBrake;

    /*
     * Please don't change these unless changing the motor type.
     * If current limit is set too high for motor, motor may produce Magic Smoke.
     * Use 50 amps for NEO 1.1.
     * Use 20 amps for NEO 550.
     */
    public static final int kDriveCurrentLimit = 50;
    public static final int kTurnCurrentLimit = 20;

    /*
     * Sets the corresponding motor to invert its direction.
     */
    public static final boolean kDriveInverted = null;
    public static final boolean kTurnInverted = null;

    /* 
     * Sets the feedback sensor to the corresponding motor.
     */
    public static final FeedbackSensor kDriveFeedBackSensor = FeedbackSensor.kPrimaryEncoder;

}

