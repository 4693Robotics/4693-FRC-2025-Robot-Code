package frc.robot.Subsystems.Drive.TestDrive;

import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Subsystems.Drive.TestDrive.DriveConstants.*;

public class DriveConfigs {
    private static final SparkMaxConfig driveConfig = new SparkMaxConfig();
    private static final SparkMaxConfig turnConfig = new SparkMaxConfig();

    static {
        driveConfig
            .idleMode(null)
            .smartCurrentLimit(0)
            .inverted(false);
        driveConfig.absoluteEncoder
            .
        
    }
}
