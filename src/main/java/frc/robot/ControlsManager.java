package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;

public class ControlsManager {

    public enum ControlProfile {
        DEFUALT,
        TRISTEN,
        TEST
    }

    private ControlProfile currentProfile;

    private final CommandGenericHID driveController;
    private final CommandGenericHID manipulatorController;

    public ControlsManager(CommandGenericHID driveController, CommandGenericHID manipulatorController) {
        this.currentProfile = ControlProfile.DEFUALT;

        this.driveController = driveController;
        this.manipulatorController = manipulatorController;
    }
    
}
