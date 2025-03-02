package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Utils.NetworkTableManager;

public class ElevatorSubsystemDefault extends Command {

    private ElevatorSubsystem elevatorSubsystem;

    private CommandGenericHID controller;

    private double controllerSetpoint;

    private int elevatorStage;

    private boolean povUpPressed;
    private boolean povDownPressed;

    private Trigger povUp;
    private Trigger povDown;

    public ElevatorSubsystemDefault(ElevatorSubsystem elevatorSubsystem, CommandGenericHID controller) {

        this.elevatorSubsystem = elevatorSubsystem;

        this.controller = controller;

        addRequirements(elevatorSubsystem);

        controllerSetpoint = 0;

        elevatorStage = 0;

        povUpPressed = false;
        povDownPressed = false;
    }

    @Override
    public void initialize() {
        povUp = controller.povUp();
        povDown = controller.povDown();
    }

    @Override
    public void execute() {
        /* 
        if (povUp.getAsBoolean()) {
            controllerSetpoint = JoystickUtils.joystickSlider( 40, controllerSetpoint, 1050, 0);
        }

        if (povDown.getAsBoolean()) {
            controllerSetpoint = JoystickUtils.joystickSlider( -30, controllerSetpoint, 1050, 0);
        }
*/
        if (povUp.getAsBoolean() && elevatorStage < 4 && !povUpPressed) {
            povUpPressed = true;
            elevatorStage++;
        } else if (povDown.getAsBoolean() && elevatorStage > 0 && !povDownPressed) {
            povDownPressed = true;
            elevatorStage--;
        }

        if (!povUp.getAsBoolean()) {
            povUpPressed = false;
        }

        if (!povDown.getAsBoolean()) {
            povDownPressed = false;
        }

        switch (elevatorStage) {
            case 0:
                controllerSetpoint = 0;
                break;
            case 1:
                controllerSetpoint = 80;
                break;
            case 2:
                controllerSetpoint = 115;
                break;
            case 3:
                controllerSetpoint = 465;
                break;
            case 4:
                controllerSetpoint = 950;
                break;
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
