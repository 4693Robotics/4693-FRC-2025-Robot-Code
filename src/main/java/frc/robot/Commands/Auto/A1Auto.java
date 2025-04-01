package frc.robot.Commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.AlgaeManipSubsystem;

public class A1Auto extends Command {

    private final AlgaeManipSubsystem algaeManipSubsystem;

    private final Timer timer;

    public A1Auto(AlgaeManipSubsystem algaeManipSubsystem) {

        this.algaeManipSubsystem =algaeManipSubsystem;
        timer = new Timer();

        addRequirements(algaeManipSubsystem);
    }
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.get() > 0.2 && timer.get() < 0.3) {
            algaeManipSubsystem.l1();
        }

    }

    @Override
    public void end(boolean interrupted) {
        
    }
}