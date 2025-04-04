package frc.robot.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.DriveSubsystem;

public class DriveSubsytemDefault extends Command{

    private DriveSubsystem driveSubsystem;

    private CommandGenericHID controller;

    private Trigger a;

    private boolean fieldRelative;
    

    public DriveSubsytemDefault(DriveSubsystem driveSubsystem, CommandGenericHID controller) {

        this.driveSubsystem = driveSubsystem;
        
        this.controller = controller;

        fieldRelative = true;

        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        a = controller.button(1);//.onTrue(new InstantCommand(() -> fieldRelative=fieldRelative!));
    }

    @Override
    public void execute() {

        if (a.getAsBoolean()) {
            fieldRelative =! fieldRelative;
        }

            driveSubsystem.drive(
                (1 - controller.getRawAxis(3) * 0.5) * -MathUtil.applyDeadband(controller.getRawAxis(1), OIConstants.kDriveDeadband),
                (1 - controller.getRawAxis(3) * 0.5) * -MathUtil.applyDeadband(controller.getRawAxis(0), OIConstants.kDriveDeadband),
                (1 - controller.getRawAxis(3) * 0.5) * -MathUtil.applyDeadband(controller.getRawAxis(4), OIConstants.kDriveDeadband),
                fieldRelative,
                true);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.drive(
            0,
            0,
            0,
            true,
            true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
