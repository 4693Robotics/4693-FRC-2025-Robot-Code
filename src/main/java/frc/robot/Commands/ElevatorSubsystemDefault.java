package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Utils.NetworkTableManager;
import frc.robot.Utils.RobotUtils.JoystickUtils;

public class ElevatorSubsystemDefault extends Command {

    private ElevatorSubsystem elevatorSubsystem;

    private CommandGenericHID controller;

    private double controllerSetpoint;

    private Trigger povUp;
    private Trigger povDown;

    public ElevatorSubsystemDefault(ElevatorSubsystem elevatorSubsystem, CommandGenericHID controller) {

        this.elevatorSubsystem = elevatorSubsystem;

        this.controller = controller;

        addRequirements(elevatorSubsystem);

        controllerSetpoint = 0;
    }

    @Override
    public void initialize() {
        povUp = controller.povUp();
        povDown = controller.povDown();
    }

    @Override
    public void execute() {
        if (povUp.getAsBoolean()) {
            controllerSetpoint = JoystickUtils.joystickSlider( 40, controllerSetpoint, 1050, 0);
        }

        if (povDown.getAsBoolean()) {
            controllerSetpoint = JoystickUtils.joystickSlider( -20, controllerSetpoint, 1050, 0);
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
