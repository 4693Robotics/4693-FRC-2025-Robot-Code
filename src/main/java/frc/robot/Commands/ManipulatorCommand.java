package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.Manipulator.ManipulatorSubsystem;
import frc.robot.Subsystems.Manipulator.ManipulatorSubsystem.ManipulatorPos;

public class ManipulatorCommand extends Command {

    private final ManipulatorSubsystem manipulator;

    private final CommandGenericHID controller;

    private final Trigger RestTrigger;
    private final Trigger L1Trigger;
    private final Trigger L2Trigger;
    private final Trigger L3Trigger;
    private final Trigger CoralInTrigger;
    private final Trigger A1Trigger;
    private final Trigger A2Trigger;
    private final Trigger BargeTrigger;

    public ManipulatorCommand(ManipulatorSubsystem manipulator, CommandGenericHID controller) {

        this.manipulator = manipulator;

        this.controller = controller;

        RestTrigger = new Trigger(controller.button(1)).onTrue(new InstantCommand(() -> manipulator.setPos(ManipulatorPos.REST), manipulator));
        L1Trigger = new Trigger(controller.povLeft()).onTrue(new InstantCommand(() -> manipulator.setPos(ManipulatorPos.L1), manipulator));
        L2Trigger = new Trigger(controller.povUp()).onTrue(new InstantCommand(() -> manipulator.setPos(ManipulatorPos.L2), manipulator));
        L3Trigger = new Trigger(controller.povRight()).onTrue(new InstantCommand(() -> manipulator.setPos(ManipulatorPos.L3), manipulator));
        CoralInTrigger = new Trigger(controller.povDown()).onTrue(new InstantCommand(() -> manipulator.setPos(ManipulatorPos.CORALIN), manipulator));
        A1Trigger = new Trigger(controller.button(3)).onTrue(new InstantCommand(() -> manipulator.setPos(ManipulatorPos.A1), manipulator));
        A2Trigger = new Trigger(controller.button(4)).onTrue(new InstantCommand(() -> manipulator.setPos(ManipulatorPos.A2), manipulator));
        BargeTrigger = new Trigger(controller.button(2)).onTrue(new InstantCommand(() -> manipulator.setPos(ManipulatorPos.BARGE), manipulator));

        addRequirements(manipulator);
    }

    @Override
    public void execute() {
        manipulator.setIntakeSpeed(controller.getRawAxis(1));
    }

    @Override
    public void end(boolean interrupted) {
        manipulator.setIntakeSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
