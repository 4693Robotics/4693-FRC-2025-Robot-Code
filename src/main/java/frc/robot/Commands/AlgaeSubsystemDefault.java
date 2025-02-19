package frc.robot.Commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.AlgaeSubsystem;

public class AlgaeSubsystemDefault extends Command {
    
    private AlgaeSubsystem algaeSubsystem;

    private GenericHID subsystemController;

    public AlgaeSubsystemDefault(AlgaeSubsystem algaeSubsystem, GenericHID subsystemController) {

        this.algaeSubsystem = algaeSubsystem;

        this.subsystemController = subsystemController;

        addRequirements(algaeSubsystem);
    }

    @Override
    public void execute() {
        if (subsystemController.getRawButton(6)) {
            algaeSubsystem.setAlgaeIntakeSpeed(subsystemController.getRawAxis(3));
        } else {
            algaeSubsystem.setAlgaeIntakeSpeed(-subsystemController.getRawAxis(3));
        }

        if (subsystemController.getRawButton(5)) {
            algaeSubsystem.setAlgaeArmSpeed(-subsystemController.getRawAxis(2));
        } else {
            algaeSubsystem.setAlgaeArmSpeed(subsystemController.getRawAxis(2));
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
