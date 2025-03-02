package frc.robot.Commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.CoralIntakeSubsystem;

public class L1Auto extends Command {

    private final CoralIntakeSubsystem coralIntakeSubsystem;

    private final Timer timer;

    public L1Auto(frc.robot.Subsystems.CoralIntakeSubsystem coralIntakeSubsystem) {

        this.coralIntakeSubsystem = coralIntakeSubsystem;
        timer = new Timer();

        addRequirements(coralIntakeSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        
    }

    @Override
    public void execute() {
        if (timer.get() > 0.2) {
            coralIntakeSubsystem.setCoralIntakeSpeed(0.2);
        }
    }

    @Override
    public void end(boolean interrupted) {
        coralIntakeSubsystem.setCoralIntakeSpeed(0);
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return timer.get() > 1;
    }
}
