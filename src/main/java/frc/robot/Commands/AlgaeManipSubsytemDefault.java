package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.AlgaeManipSubsystem;
import frc.robot.Subsystems.AlgaeSubsystem;

public class AlgaeManipSubsytemDefault extends Command {

    private final AlgaeManipSubsystem algaeManipSubsystem;

    private final CommandGenericHID controller;

    private double elevatorSetpoint;
    private double manipulatorSetpoint;

    private final Trigger l1Trigger;
    
    public AlgaeManipSubsytemDefault(AlgaeManipSubsystem algaeManipSubsystem, CommandGenericHID controller) {

        this.algaeManipSubsystem = algaeManipSubsystem;

        this.controller = controller;

        elevatorSetpoint = 0;
        manipulatorSetpoint = 185;

        //Links A button with l1 function
        l1Trigger = new Trigger(controller.button(1)).onTrue(new InstantCommand(() -> algaeManipSubsystem.l1(), algaeManipSubsystem));
    
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
