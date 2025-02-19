package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Utils.NetworkTableManager;
import frc.robot.Utils.RobotUtils.JoystickUtils;

public class ElevatorSubsystemDefault extends Command {

    private ElevatorSubsystem elevatorSubsystem;

    private CommandGenericHID controller;

    private double controllerSetpoint;

    public ElevatorSubsystemDefault(ElevatorSubsystem elevatorSubsystem, CommandGenericHID controller) {

        this.elevatorSubsystem = elevatorSubsystem;

        this.controller = controller;

        addRequirements(elevatorSubsystem);

        controllerSetpoint = 0;
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        if (controller.povUp().getAsBoolean()) {
            controllerSetpoint = JoystickUtils.joystickSlider( 10, controllerSetpoint, 337, 0);
        }

        if (controller.povDown().getAsBoolean()) {
            controllerSetpoint = JoystickUtils.joystickSlider( -10, controllerSetpoint, 337, 0);
        }

        NetworkTableManager.getInstance().putNumber("ElevatorSubsystem/ElevatorSetpoint", controllerSetpoint);

        elevatorSubsystem.setElevatorSetpoint(controllerSetpoint);
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    
}
