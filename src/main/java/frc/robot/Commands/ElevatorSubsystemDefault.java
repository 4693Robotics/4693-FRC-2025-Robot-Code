package frc.robot.Commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Utils.RobotUtils.JoystickUtils;

public class ElevatorSubsystemDefault extends Command {

    private ElevatorSubsystem elevatorSubsystem;

    private GenericHID controller;

    private double controllerSetpoint;

    public ElevatorSubsystemDefault(ElevatorSubsystem elevatorSubsystem, GenericHID controller) {

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
        if (controller.getPOV() == 0) {
            controllerSetpoint = JoystickUtils.joystickSlider( 10, controllerSetpoint, 337, 0);
        }

        if (controller.getPOV() == 180) {
            controllerSetpoint = JoystickUtils.joystickSlider( -10, controllerSetpoint, 337, 0);
        }

        NetworkTableInstance ntInstance = NetworkTableInstance.getDefault();
        NetworkTable table = ntInstance.getTable("ElevatorSusbsytem");

        table.getEntry("Elevator Setpoint").setDouble(controllerSetpoint);

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
