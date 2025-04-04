package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.AlgaeManipSubsystem;

public class AlgaeManipSubsytemDefault extends Command {

    private final AlgaeManipSubsystem algaeManipSubsystem;

    private final CommandGenericHID controller;

    private final Trigger restingSpoTrigger;
    private final Trigger l1Trigger;
    private final Trigger l2Trigger;
    private final Trigger l3Trigger;
    private final Trigger coralinTrigger;
    private final Trigger a1Trigger;
    private final Trigger a2Trigger;
    private final Trigger bTrigger;

    public AlgaeManipSubsytemDefault(AlgaeManipSubsystem algaeManipSubsystem, CommandGenericHID controller) {

        this.algaeManipSubsystem = algaeManipSubsystem;

        this.controller = controller;

        restingSpoTrigger = new Trigger(controller.button(1)).onTrue(new InstantCommand(() -> algaeManipSubsystem.restingSpot(), algaeManipSubsystem));
        l1Trigger = new Trigger(controller.povLeft()).onTrue(new InstantCommand(() -> algaeManipSubsystem.l1(), algaeManipSubsystem));
        l2Trigger = new Trigger(controller.povUp()).onTrue(new InstantCommand(() -> algaeManipSubsystem.l2(), algaeManipSubsystem));
        l3Trigger = new Trigger(controller.povRight()).onTrue(new InstantCommand(() -> algaeManipSubsystem.l3(), algaeManipSubsystem));
        coralinTrigger = new Trigger(controller.povDown()).onTrue(new InstantCommand(() -> algaeManipSubsystem.coralin(), algaeManipSubsystem));
        a1Trigger = new Trigger(controller.button(3)).onTrue(new InstantCommand(() -> algaeManipSubsystem.a1(), algaeManipSubsystem));
        a2Trigger = new Trigger(controller.button(4)).onTrue(new InstantCommand(() -> algaeManipSubsystem.a2(), algaeManipSubsystem));
        bTrigger = new Trigger(controller.button(2)).onTrue(new InstantCommand(() -> algaeManipSubsystem.b(), algaeManipSubsystem));

        addRequirements(algaeManipSubsystem);
    }

    @Override   
    public void initialize() {
        
    }

    @Override
    public void execute() {
        
        algaeManipSubsystem.setIntakeSpeed(controller.getRawAxis(1));
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
