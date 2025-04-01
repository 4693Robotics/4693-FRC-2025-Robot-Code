package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.CoralIntakeSubsystem;

public class CoralIntakeSubsystemDefault extends Command {

    private final CoralIntakeSubsystem coralIntakeSubsystem;
    private final CommandGenericHID controller;
    private Trigger b;
    private Trigger a;
    private double controllerSetpoint;

    private int elevatorStage;

    private boolean bPressed;
    private boolean aPressed;

    private CoralIntakeSubsystem nuckleSubsystem;;

    public CoralIntakeSubsystemDefault(CoralIntakeSubsystem coralIntakeSubsystem, CommandGenericHID controller) {
        this.coralIntakeSubsystem = coralIntakeSubsystem;
        this.controller = controller;
        this.controllerSetpoint = 420;
        addRequirements(coralIntakeSubsystem);
    }
    

    @Override
    public void initialize() {
        b = controller.button(2);
        a = controller.button(1);

    }
    @Override
    public void execute() {
        //a = controller.button(1);
        //x = controller.button(4);

       /* controller.button(1).toggleOnTrue(new InstantCommand(() -> controllerSetpoint = 420));
       controller.button(1).toggleOnFalse(new InstantCommand(() -> controllerSetpoint = 175)); */

    if (b.getAsBoolean() && elevatorStage < 4 && !bPressed) {
        bPressed = true;
        elevatorStage++;
    } else if (a.getAsBoolean() && elevatorStage > 0 && !aPressed) {
        aPressed = true;
        elevatorStage--;
    }

    if (!b.getAsBoolean()) {
        bPressed = false;
    }

    if (!a.getAsBoolean()) {
        aPressed = false;
    }

    switch (elevatorStage) {
        case 0:
            controllerSetpoint = 185;
            break;
        case 1:
            controllerSetpoint = 355;
            break;
        case 2:
            controllerSetpoint = 475;
            break;
        case 3:
            controllerSetpoint = 530;
            break;
        case 4:
            controllerSetpoint = 580;
            break;
    }
    
        coralIntakeSubsystem.setCoralIntakeSpeed(controller.getRawAxis(1));
        
       /*  controllerSetpoint = x.getAsBoolean() 
            ? JoystickUtils.joystickSlider(5, controllerSetpoint, 1000, 0)
            : controllerSetpoint;

        controllerSetpoint = a.getAsBoolean() 
            ? JoystickUtils.joystickSlider(-5, controllerSetpoint, 1000, 0) 
            : controllerSetpoint; */
        
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