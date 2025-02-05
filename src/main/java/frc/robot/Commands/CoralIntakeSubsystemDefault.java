package frc.robot.Commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.CoralIntakeSubsystem;

public class CoralIntakeSubsystemDefault extends Command {
    
    private CoralIntakeSubsystem coralIntakeSubsystem;

    private GenericHID controller;

    public CoralIntakeSubsystemDefault(CoralIntakeSubsystem coralIntakeSubsystem, GenericHID controller) {

        this.coralIntakeSubsystem = coralIntakeSubsystem;

        this.controller = controller;

        addRequirements(coralIntakeSubsystem);
    }

    @Override
    public void execute() {
        coralIntakeSubsystem.setCoralIntakeSpeed(controller.getRawAxis(1));
        coralIntakeSubsystem.setNuckleSpeed(controller.getRawAxis(5));
    }

    @Override
    public void end(boolean interrupted) {
        coralIntakeSubsystem.setCoralIntakeSpeed(0);
        coralIntakeSubsystem.setNuckleSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

