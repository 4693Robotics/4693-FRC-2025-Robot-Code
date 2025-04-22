package frc.robot.Control;

import java.util.EnumMap;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import frc.robot.Control.ControlsManager.ControlProfile.BooleanFunction;
import frc.robot.Control.ControlsManager.ControlProfile.DoubleFunction;

public class ControlsManager {

    public static enum ControlProfile {
        DEFUALT,
        TRISTEN,
        TEST;
        
        public static enum BooleanFunction {
            FIELD_RELATIVE,
            XSTANCE,
            INCREMENT_MANIPULATOR,
            DECREMENT_MANIPULATOR
        }

        public static enum DoubleFunction {
            DRIVE_X,
            DRIVE_Y,
            DRIVE_ROT,
            DRIVE_BRAKE,
            MANIPULATOR_INTAKE,
            INTAKE_ARM,
            INTAKE_INTAKE
        }

        final EnumMap<BooleanFunction, BooleanSupplier> booleanBindings = new EnumMap<>(BooleanFunction.class);
        final EnumMap<DoubleFunction, DoubleSupplier> doubleBindings = new EnumMap<>(DoubleFunction.class);
        
    }

    private ControlProfile currentProfile;

    final CommandGenericHID driveController;
    final CommandGenericHID manipulatorController;

    public ControlsManager(CommandGenericHID driveController, CommandGenericHID manipulatorController) {
        this.currentProfile = ControlProfile.DEFUALT;

        this.driveController = driveController;
        this.manipulatorController = manipulatorController;
    }

    private void updateProfile() {
        ControlBindings.configureBindings(currentProfile, driveController, manipulatorController);
    }

    public void setProfile(ControlProfile profile) {
        currentProfile = profile;
        updateProfile();
    }

    public BooleanSupplier getBooleanFunction(BooleanFunction function) {
        return currentProfile.booleanBindings.get(function);
    }

    public DoubleSupplier getDoubleFunction(DoubleFunction function) {
        return currentProfile.doubleBindings.get(function);
    }

}
