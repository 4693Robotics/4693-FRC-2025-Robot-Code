package frc.robot.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveSubsystem;

public class DriveSubsytemDefault extends Command{

    private DriveSubsystem driveSubsystem;

    private DoubleSupplier xSpeed;
    private DoubleSupplier ySpeed;
    private DoubleSupplier rot;
    private DoubleSupplier damp;

    private BooleanSupplier fieldRelative;
    

    public DriveSubsytemDefault(DriveSubsystem driveSubsystem, DoubleSupplier xSpeed, DoubleSupplier ySpeed, DoubleSupplier rot, DoubleSupplier damp, BooleanSupplier fieldRelative) {

        this.driveSubsystem = driveSubsystem;
        
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.rot = rot;
        this.damp = damp;

        this.fieldRelative = fieldRelative;
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
