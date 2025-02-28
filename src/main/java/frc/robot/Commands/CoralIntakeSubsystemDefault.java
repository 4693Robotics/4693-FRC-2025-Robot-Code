package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.CoralIntakeSubsystem;
import frc.robot.Utils.RobotUtils.JoystickUtils;

public class CoralIntakeSubsystemDefault extends Command {

    private final CoralIntakeSubsystem coralIntakeSubsystem;
    private final CommandGenericHID controller;
    private Trigger a;
    private Trigger x;
    private double controllerSetpoint;

    public CoralIntakeSubsystemDefault(CoralIntakeSubsystem coralIntakeSubsystem, CommandGenericHID controller) {
        this.coralIntakeSubsystem = coralIntakeSubsystem;
        this.controller = controller;
        this.controllerSetpoint = 75;
        addRequirements(coralIntakeSubsystem);
    }

    @Override
    public void initialize() {
        //a = controller.button(1);
        //x = controller.button(4);

        controller.button(1).toggleOnTrue(new InstantCommand(() -> controllerSetpoint = 375));
        controller.button(1).toggleOnFalse(new InstantCommand(() -> controllerSetpoint = 75));
    }

    @Override
    public void execute() {
        coralIntakeSubsystem.setCoralIntakeSpeed(controller.getRawAxis(1));

        /* 
        controllerSetpoint = x.getAsBoolean() 
            ? JoystickUtils.joystickSlider(5, controllerSetpoint, 1000, 0)
            : controllerSetpoint;

        controllerSetpoint = a.getAsBoolean() 
            ? JoystickUtils.joystickSlider(-5, controllerSetpoint, 1000, 0) 
            : controllerSetpoint;
*/
        coralIntakeSubsystem.setNucklePoint(controllerSetpoint);
    }

    @Override
    public void end(boolean interrupted) {
        coralIntakeSubsystem.setCoralIntakeSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}