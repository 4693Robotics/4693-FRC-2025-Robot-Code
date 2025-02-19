package frc.robot.Commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.Utils.RobotUtils.JoystickUtils;
import frc.robot.Subsystems.CoralIntakeSubsystem;

public class CoralIntakeSubsystemDefault extends Command {
    
    private CoralIntakeSubsystem coralIntakeSubsystem;

    private GenericHID controller;

    private double controllerSetpoint;

    public CoralIntakeSubsystemDefault(CoralIntakeSubsystem coralIntakeSubsystem, GenericHID controller) {

        this.coralIntakeSubsystem = coralIntakeSubsystem;

        this.controller = controller;

        addRequirements(coralIntakeSubsystem);

        controllerSetpoint = 0;
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        coralIntakeSubsystem.setCoralIntakeSpeed(controller.getRawAxis(1));

        controllerSetpoint = JoystickUtils.joystickSlider(-controller.getRawAxis(5) * 10, controllerSetpoint, 100, 0);

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

