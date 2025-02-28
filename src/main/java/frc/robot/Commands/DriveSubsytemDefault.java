package frc.robot.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Utils.NetworkTableManager;
import frc.robot.Utils.RobotUtils.limelightUtils;

public class DriveSubsytemDefault extends Command{

    private DriveSubsystem driveSubsystem;

    private DoubleSupplier xSpeed;
    private DoubleSupplier ySpeed;
    private DoubleSupplier rot;
    private DoubleSupplier damp;

    private BooleanSupplier fieldRelative;

    private CommandGenericHID controller;

    private Trigger x;
    

    public DriveSubsytemDefault(DriveSubsystem driveSubsystem, DoubleSupplier xSpeed, DoubleSupplier ySpeed, DoubleSupplier rot, DoubleSupplier damp, BooleanSupplier fieldRelative, CommandGenericHID controller) {

        this.driveSubsystem = driveSubsystem;
        
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.rot = rot;
        this.damp = damp;

        this.fieldRelative = fieldRelative;

        this.controller = controller;

        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        x = controller.button(4);
    }

    @Override
    public void execute() {
        if (x.getAsBoolean()) {
            driveSubsystem.drive(
                limelightUtils.aimLimelightDistance(),
                0,
                limelightUtils.aimLimelightRot(),
                false,
                true);
        } else {
            driveSubsystem.drive(
                xSpeed.getAsDouble(),
                ySpeed.getAsDouble(),
                rot.getAsDouble(),
                fieldRelative.getAsBoolean(),
                true);
        }

    NetworkTableManager.getInstance().putBoolean("OI/XPressed", x.getAsBoolean());
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
