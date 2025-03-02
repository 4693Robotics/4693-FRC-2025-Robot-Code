package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import frc.robot.Subsystems.AlgaeSubsystem;

public class AlgaeSubsystemDefault extends Command {

    private final AlgaeSubsystem algaeSubsystem;
    private final CommandGenericHID controller;

    public AlgaeSubsystemDefault(AlgaeSubsystem algaeSubsystem, CommandGenericHID controller) {
        this.algaeSubsystem = algaeSubsystem;
        this.controller = controller;

        addRequirements(algaeSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double intakeSpeed = controller.getRawAxis(3) + -controller.getRawAxis(2);
        double armSpeed = -controller.getRawAxis(5);

        algaeSubsystem.setAlgaeIntakeSpeed(intakeSpeed);
        algaeSubsystem.setAlgaeArmSpeed(armSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        algaeSubsystem.setAlgaeIntakeSpeed(0);
    }
}
