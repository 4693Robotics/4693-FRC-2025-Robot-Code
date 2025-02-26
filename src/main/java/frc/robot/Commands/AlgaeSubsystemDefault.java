package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.AlgaeSubsystem;

public class AlgaeSubsystemDefault extends Command {

    private final AlgaeSubsystem algaeSubsystem;
    private final CommandGenericHID controller;

    private Trigger leftBumper;
    private Trigger rightBumper;

    public AlgaeSubsystemDefault(AlgaeSubsystem algaeSubsystem, CommandGenericHID controller) {
        this.algaeSubsystem = algaeSubsystem;
        this.controller = controller;

        addRequirements(algaeSubsystem);
    }

    @Override
    public void initialize() {
        leftBumper = controller.button(6);
        rightBumper = controller.button(5);
    }

    @Override
    public void execute() {
        double intakeSpeed = controller.getRawAxis(3);
        double armSpeed = controller.getRawAxis(2);

        algaeSubsystem.setAlgaeIntakeSpeed(leftBumper.getAsBoolean() ? intakeSpeed : -intakeSpeed);
        algaeSubsystem.setAlgaeArmSpeed(rightBumper.getAsBoolean() ? -armSpeed : armSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        algaeSubsystem.setAlgaeIntakeSpeed(0);
    }
}
