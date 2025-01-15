package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Utils.LimelightHelpers;

public class VisionSubsystem extends SubsystemBase{

    public VisionSubsystem() {
        
        LimelightHelpers.getFiducialID("");
    }


}