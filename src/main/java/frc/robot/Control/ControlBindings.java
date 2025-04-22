package frc.robot.Control;

import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import frc.robot.Control.ControlsManager.ControlProfile;
import static frc.robot.Control.ControlsManager.ControlProfile.BooleanFunction.*;
import static frc.robot.Control.ControlsManager.ControlProfile.DoubleFunction.*;


public class ControlBindings {
    
    public static void configureBindings(ControlProfile profile, CommandGenericHID driveController, CommandGenericHID manipulatorController) {
        switch (profile) {
            case DEFUALT -> {
                profile.booleanBindings.put(FIELD_RELATIVE, driveController.button(1));
                profile.booleanBindings.put(XSTANCE, driveController.button(5));
                profile.booleanBindings.put(INCREMENT_MANIPULATOR, manipulatorController.povUp());
                profile.booleanBindings.put(DECREMENT_MANIPULATOR, manipulatorController.povDown()); 

                profile.doubleBindings.put(DRIVE_X, () -> driveController.getRawAxis(0));
                profile.doubleBindings.put(DRIVE_Y, () -> driveController.getRawAxis(1));
                profile.doubleBindings.put(DRIVE_ROT, () -> driveController.getRawAxis(4));
                profile.doubleBindings.put(DRIVE_BRAKE, () -> driveController.getRawAxis(3));
                profile.doubleBindings.put(MANIPULATOR_INTAKE, () -> manipulatorController.getRawAxis(1));
                profile.doubleBindings.put(INTAKE_ARM, () -> manipulatorController.getRawAxis(5));
            }
            case TRISTEN -> {

            }
            case TEST -> {
                
            }
        }
    }
}
