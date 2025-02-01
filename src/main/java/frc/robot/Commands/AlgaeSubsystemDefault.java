package frc.robot.Commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.AlgaeSubsystem;

public class AlgaeSubsystemDefault extends Command {
    
    private AlgaeSubsystem algaeSubsystem;

    private GenericHID controller;

    public AlgaeSubsystemDefault(AlgaeSubsystem algaeSubsystem, GenericHID controller) {

        this.algaeSubsystem = algaeSubsystem;

        this.controller = controller;

        addRequirements(algaeSubsystem);
    }

    @Override
    public void execute() {
        if (controller.getRawButton(6)) {
            algaeSubsystem.setAlgaeIntakeSpeed(controller.getRawAxis(3));
        } else {
            algaeSubsystem.setAlgaeIntakeSpeed(-controller.getRawAxis(3));
        }
    }

    public void end(boolean interrupted) {
        algaeSubsystem.setAlgaeIntakeSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
